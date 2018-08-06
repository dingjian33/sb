package nc.bs.hzyb.sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.naming.NamingException;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.hzyb.sql.MidTableExcute;
import nc.bs.hzyb.plugins.GetPk;
import nc.bs.hzyb.plugins.DataSwitch;
import nc.bs.logging.Logger;
import nc.bs.pub.SystemException;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.hzyb.pub.MapList;
import nc.vo.hzyb.ybvo.AggYBNCBillHVO;
import nc.vo.hzyb.ybvo.YBNCBillBVO;
import nc.vo.hzyb.ybvo.YBNCBillHVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.hzyb.pub.Log;

public class MidTableFactory {
	private MidTableExcute excute;
	private String dbName;
	private ArrayList<String> keyList; // 将原流水号保存，用于回写东软中间表

	public MidTableFactory(String midDBname) {
		this.setDbName(midDBname);
	}

	public MidTableFactory() {
	}

	/**
	 * 需要自己组织 查询表头的SQL，通过表头VO里的主键，查询表体VO，key以dr_开头 返回医保表单vo
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public AggYBNCBillHVO[] queryYBBillVO(String datasource,
			String tableName_H, String tableName_B, String id) throws Exception {

		AggYBNCBillHVO[] bills = null;
		YBNCBillHVO[] hvos = null;
		YBNCBillBVO[] bvos = null;
		String queryHeadSql = null;

		/*
		 * queryHeadSql = "select * from hzsi." + tableName_H +
		 * " where nc_voucher = '" + id + "'";
		 */

		queryHeadSql = "select * from hzsi." + tableName_H
				+ " where nc_voucher = '" + id + "'";

		Map<String, Object> sql = this.sql(tableName_H, queryHeadSql);
		Logger.info("已查到中间表数据条数： " + sql.size());
		String queryBodySql = null;
		try {
			GetPk gp = new GetPk(datasource);
			Log.getInstance().info("进入查询方法时的数据源======" + datasource);
			// 查表头,返回SmartVO类
			hvos = (YBNCBillHVO[]) gp.selectBySql2(queryHeadSql,
					nc.vo.hzyb.ybvo.YBNCBillHVO.class);
			// 组织表体查询条件 ---通过外键,当前公司
			// queryBodySql = buildBodySql(hvos, tableName_B
			// ,hmp.get("dr_corp")); pk_corp

			queryBodySql = buildBodySql(tableName_B, queryHeadSql);// 表体sql
			// 查询表体
			bvos = (YBNCBillBVO[]) gp.selectBySql2(queryBodySql,
					nc.vo.hzyb.ybvo.YBNCBillBVO.class);
			// 组织单据VO

			bills = buildBills(hvos, bvos);
			// 根据流水号重组VO

			bills = rebuildBills(bills);
			// 回写，表示完成数据提取
			// this.dataRewriteFlag(tableName_H, bills,
			// nc.bs.hzyb.sql.UpdateInform.done, id);
		} catch (SQLException e) {
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
			throw new Exception("查询数据错误： " + e);
		} catch (SystemException e) {
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (NamingException e) {
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return bills;
	}

	public String buildBodySql(YBNCBillHVO[] hvos, String tableName_B,
			String corpcode) {
		String Str = null;
		int k = 0;
		String temp = "select * from " + tableName_B + " where accno ='"
				+ corpcode + "' and sid in (";
		String[] value = new String[hvos.length];
		for (int i = 0; i < hvos.length; i++) {
			value[i] = (String) hvos[i].getSid();
			if (i == 0)
				temp += value[i];
			else if (i < 1000 * (k + 1) && i > 1000 * k) { // 实际大于1000 都会报SQL错误
				temp += "," + value[i];
			} else if (i == 1000 * (k + 1)) {
				temp += ") or sid in (" + value[i];
				k++;
			}
		}
		Str = temp + ")";
		return Str;
	}

	public String buildBodySql(String tableName_B, String qurySql_hvo) {
		String n_qurySql_hvo = qurySql_hvo.replace("*", "sid");
		String bodysql = "select * from hzsi." + tableName_B
				+ " where sid in (" + n_qurySql_hvo + ")" + " order by remark";

		return bodysql;
	}

	public String buildBodySql(String tableName_B, String accno,
			String qurySql_hvo) {
		String n_qurySql_hvo = qurySql_hvo.replace("*", "sid");
		String bodysql = "select * from hzsi." + tableName_B
				+ " where accno = '" + accno + "' and sid in (" + n_qurySql_hvo
				+ ")" + " order by remark";

		return bodysql;
	}

	public AggYBNCBillHVO[] buildBills(YBNCBillHVO[] hvos, YBNCBillBVO[] bvos) {
		// 传来的数组表头和数组表体，组织生成单据数组
		// 用表体中外键的值 组建一个MapList键值对，然后在表头中获取所有主键（相对于表头的外键）
		// 通过主键获取键值对中所对应的 列表值，作为其表头的表体。
		// 各自赋值，组合成单据VO，

		// List自增长度
		List<AggYBNCBillHVO> lst = new ArrayList<AggYBNCBillHVO>();
		// 用表体中外键的值 组建一个MapList键值对
		MapList<String, YBNCBillBVO> ml = new MapList<String, YBNCBillBVO>();
		for (YBNCBillBVO bvo : bvos) {
			String str = bvo.getSid();
			ml.put(str, bvo);
		}

		for (YBNCBillHVO hvo : hvos) {
			String key = hvo.getSid(); // 就是表头的SID
										// ,,,,,,原来是hvo.get.getPrimaryKey()
			List<YBNCBillBVO> bodys = ml.get(key); // 循环由表头的唯一sid获得对应表体的记录个数存放到list中
			if (bodys != null) {
				AggYBNCBillHVO bill = new AggYBNCBillHVO();
				bill.setParentVO(hvo);
				bill.setChildrenVO(bodys.toArray(new YBNCBillBVO[0]));
				lst.add(bill);
			}
		}
		return lst.toArray(new AggYBNCBillHVO[0]); // 初始化空数组
	}

	private AggYBNCBillHVO[] rebuildBills(AggYBNCBillHVO[] vos) {
		String key = null;
		String name = null;
		DataSwitch data = new DataSwitch();
		List<AggYBNCBillHVO> lst = new ArrayList<AggYBNCBillHVO>();
		MapList<String, YBNCBillHVO> hml = new MapList<String, YBNCBillHVO>();
		MapList<String, YBNCBillBVO> bml = new MapList<String, YBNCBillBVO>();
		MapList<String, String> names = new MapList<String, String>();
		ArrayList<String> nameList = new ArrayList<String>();
		for (AggYBNCBillHVO vo : vos) {
			YBNCBillHVO hvo = (YBNCBillHVO) vo.getParentVO();
			YBNCBillBVO[] bvos = (YBNCBillBVO[]) vo.getChildrenVO();
			// 获取流水号主键
			key = hvo.getNc_voucher();
			// 将原流水号保存，用于回写东软中间表
			keyList = new ArrayList<String>();
			if (!keyList.contains(key)) {
				keyList.add(key);
			}
			// 根据相同业务，为区分支付点，将流水号末尾加上支付点
			/*
			 * if (data.isDaiFangOpertype2(hvo)) { if
			 * (hvo.getOpertype().equals("NY02")) { if
			 * (hvo.getDatatype().equals("1")) { key =
			 * key.concat(hvo.getDatatype()); hvo.setNc_voucher(key); } } else
			 * if (hvo.getOpertype().equals("NY01") ||
			 * hvo.getOpertype().equals("NY07") ||
			 * hvo.getOpertype().equals("NY20")) { key =
			 * key.concat(hvo.getDatatype()); hvo.setNc_voucher(key); }
			 * 
			 * }
			 */
			// 银行走网银的 在贷方要分开
			// 新增NY09 2012-12-19
			if (data.isBankNeedDivided(hvo.getOpertype())) {
				// UFDouble temp = new UFDouble(0);
				name = hvo.getPname();
				if(!nameList.contains(name)){
					nameList.add(name);
					bvos[0].setEnpname(name); // 做拆分后的摘要
					bvos[0].setBys6(hvo.getSummoney()); // 拆分后单家医院的总金额
				}else{
					String sid = hvo.getSid();
					name = name.concat(sid);
					nameList.add(name);
					bvos[0].setEnpname(name); // 做拆分后的摘要
					bvos[0].setBys6(hvo.getSummoney()); // 拆分后单家医院的总金额
				}

				
			}
			hml.put(key, hvo);
			for (YBNCBillBVO bvo : bvos) {
				bml.put(key, bvo);
			}
		}
		Set<String> st = hml.keySet();
		Iterator<String> it = st.iterator();
		while (it.hasNext()) {
			String key_value = it.next();
			List<YBNCBillHVO> heads = hml.get(key_value);
			List<YBNCBillBVO> bodys = bml.get(key_value);
			YBNCBillHVO[] ar_h = heads.toArray(new YBNCBillHVO[0]);
			AggYBNCBillHVO bill = new AggYBNCBillHVO();
			bill.setParentVO(ar_h[0]);
			bill.setChildrenVO(bodys.toArray(new YBNCBillBVO[0]));
			lst.add(bill);
		}
		return lst.toArray(new AggYBNCBillHVO[0]); // 初始化空数组
	}

	/**
	 * 数据标志位回写done
	 */
	public void dataRewriteFlag(String tableName_H, AggYBNCBillHVO[] aggvos,
			int flag, String id) throws DAOException {

		String updateSql = "update hzsi." + tableName_H + " set flag = " + flag
				+ " where nc_voucher = '" + id + "'";
		try {

			excute = MidTableExcute.getInstance();
			excute.oracleUpdate(this.getDbName(), updateSql);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
		}
	}

	public Map<String, Object> sql(String dbname, String sql) {

		Map<String, Object> obj = new HashMap<String, Object>();
		try {

			excute = MidTableExcute.getInstance();
			obj = excute.sql(dbname, sql);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
		}
		return obj;
	}

	/**
	 * 数据标志位回写success
	 */
	public String rewriteRetVO1(String tableName_H, AggYBNCBillHVO[] aggvos,
			int flag, String voucherno, String id) throws DAOException {

		int n = 0;
		flag = 0;
		String ts = new UFDateTime(System.currentTimeMillis()).toString();// 回写系统时间
		String updateSqlH = "update hzsi." + tableName_H + " set flag = "
				+ flag + " , posting_date = '" + ts + "', account = '" + ts
				+ "' , voucherno = '" + voucherno + "' where nc_voucher = '"
				+ id + "'";

		try {
			excute = MidTableExcute.getInstance();
			excute.oracleUpdate(this.getDbName(), updateSqlH);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
		}
		return ts;
	}

	public void rewriteRetVO2(String tableName_H, AggYBNCBillHVO[] aggvos,
			int flag, String voucherno, String id) throws DAOException {

		int n = 0;
		String ts = new UFDateTime(System.currentTimeMillis()).toString();// 回写系统时间
		String updateSqlH = "update hzsi." + tableName_H
				+ " set flag = 0  , posting_date = '" + ts + "', account = '"
				+ ts + "' , voucherno = '" + voucherno
				+ "' where nc_voucher = '" + id + "'";

		try {
			excute = MidTableExcute.getInstance();
			excute.oracleUpdate(this.getDbName(), updateSqlH);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
		}

	}

	public void rewriteRetVO(String tableName_H, AggYBNCBillHVO[] aggvos,
			int flag) throws DAOException {

		int n = 0;
		String ts = new UFDateTime(System.currentTimeMillis()).toString();// 回写系统时间
		String ts_date = ts.substring(0, 10);
		StringBuffer seqcode = new StringBuffer(ts_date);
		// seqcode = seqcode.append("-" + SdVoucherTask.current_seq);
		String finalseqcode = seqcode.toString();
		String updateSql = "update hzsi." + tableName_H + " set flag = " + flag
				+ " , posting_date = '" + ts + "', account = '" + finalseqcode
				+ "' where nc_voucher in (";
		for (AggYBNCBillHVO vo : aggvos) {
			if (aggvos.length == 0)
				updateSql += "''";
			else {
				if (n >= 1) {
					updateSql += ",";
				}
				YBNCBillHVO hvo = (YBNCBillHVO) vo.getParentVO();
				if (hvo.getOpertype().equals("NY01")
						|| hvo.getOpertype().equals("NY02")
						|| hvo.getOpertype().equals("NY03")) {
					updateSql += hvo.getNc_voucher().substring(0, 8);
				} else {
					updateSql += hvo.getNc_voucher();
				}
				n++;
			}
		}
		updateSql += ")";
		try {
			int row = -1;
			excute = MidTableExcute.getInstance();
			row = excute.oracleUpdate(this.getDbName(), updateSql);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
		}
	}

	// 好像没用
	public void dataRewriteError(String tableName_B, AggYBNCBillHVO[] vos,
			String errormsg) {
		/*
		 * 错误信息回写
		 */
		for (AggYBNCBillHVO vo : vos) {
			String updateSql = null;

			updateSql = "update hzsi." + tableName_B + " set nc_error = "
					+ errormsg + "where billid in (";
			for (int i = 0; i < vo.getChildrenVO().length; i++) {
				if (vos.length == 0)
					updateSql += "''";
				else {
					if (i > 1)
						updateSql += ",";
				}
				updateSql += ((YBNCBillBVO) vo.getChildrenVO()[i]).getSid();
			}
			updateSql += ")";
			try {
				excute.oracleUpdate(this.getDbName(), updateSql);
			} catch (SQLException e) {
				e.printStackTrace();
				Logger.error(e.getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				Logger.error(e.getMessage());
			}

		}
	}

	/**
	 * 参数是NC的信息，大部分是主键。组织的查询条件放在 login_inform ，key以dr_开头
	 */
	public HashMap<String, String> getQueryInform(
			HashMap<String, String> login_inform, HashMap<String, String> hmp) {
		login_inform.put("accountBook", hmp.get("accountBook"));
		login_inform.put("group", hmp.get("group"));
		login_inform.put("NC_billtype", hmp.get("NC_billtype"));
		login_inform.put("operator", hmp.get("operator"));
		login_inform.put("startTime", hmp.get("startTime"));
		login_inform.put("endTime", hmp.get("endTime"));
		login_inform.put("nc_voucher", hmp.get("nc_voucher"));
		try {
			BaseDAO dao = new BaseDAO();
			// 经办人
			String operatorsql = "select user_name from sm_user where cuserid = '"
					+ login_inform.get("operator") + "'";
			String createman = (String) dao.executeQuery(operatorsql,
					new ColumnProcessor());
			login_inform.put("dr_operator", createman);
			// 业务类型
			String busitypesql = "select pk_billtypecode  from bd_billtype where pk_billtypeid= '"
					+ login_inform.get("NC_billtype") + "'";
			String busitype = (String) dao.executeQuery(busitypesql,
					new ColumnProcessor());
			login_inform.put("dr_NC_billtype", busitype);
			String busi = busitype.substring(9);
			login_inform.put("dr_busi", busi);
			// 开始时间
			String starttimesql = "select begindate from bd_accperiodmonth where pk_accperiodmonth = '"
					+ login_inform.get("startTime") + "'";
			String startTime = (String) dao.executeQuery(starttimesql,
					new ColumnProcessor());
			String dr_startTime = startTime.substring(0, 10);
			login_inform.put("dr_startTime", dr_startTime);
			// 结束时间
			String endtimesql = "select enddate from bd_accperiodmonth where pk_accperiodmonth = '"
					+ login_inform.get("endTime") + "'";
			String endTime = (String) dao.executeQuery(endtimesql,
					new ColumnProcessor());
			String dr_endTime = endTime.substring(0, 10);
			login_inform.put("dr_endTime", dr_endTime);
			// pk_org
			String orgsql = "select pk_relorg from org_accountingbook where  pk_accountingbook ='"
					+ login_inform.get("accountBook") + "'";
			String dr_pkorg = (String) dao.executeQuery(orgsql,
					new ColumnProcessor());
			login_inform.put("dr_pkorg", dr_pkorg);

		} catch (DAOException e) {
			e.printStackTrace();
			Log.getInstance().error(e);
		}
		return login_inform;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbName() {
		return dbName;
	}

	public AggYBNCBillHVO[] agQueryYBBillVO(String midDBname,
			String tableName_H, String tableName_B,
			HashMap<String, String> login_inform) throws Exception {
		AggYBNCBillHVO[] bills = null;
		YBNCBillHVO[] hvos = null;
		YBNCBillBVO[] bvos = null;
		String queryHeadSql = "";
		if (login_inform.get("nc_voucher") == null
				|| "".equals(login_inform.get("nc_voucher").trim())) {
			queryHeadSql = "select * from hzsi." + tableName_H
					+ " where operperson = '" + login_inform.get("dr_operator")
					+ "'" + " and opertype = '" + login_inform.get("dr_busi")
					+ "'" + " and operdate >='"
					+ login_inform.get("dr_startTime") + "'"
					+ " and operdate <='" + login_inform.get("dr_endTime")
					+ "'" + " and nc_voucher is not null";
			// + " and flag in (0,2)";
		} else {
			queryHeadSql = "select * from hzsi." + tableName_H
					+ " where operperson = '" + login_inform.get("dr_operator")
					+ "'" + " and opertype = '" + login_inform.get("dr_busi")
					+ "'" + " and operdate >='"
					+ login_inform.get("dr_startTime") + "'"
					+ " and operdate <='" + login_inform.get("dr_endTime")
					+ "'" + " and nc_voucher ='"
					+ login_inform.get("nc_voucher") + "'" + " ";
		}
		String queryBodySql = null;
		try {
			GetPk gp = new GetPk(midDBname);
			// 查表头,返回SmartVO类
			hvos = (YBNCBillHVO[]) gp.selectBySql2(queryHeadSql,
					nc.vo.hzyb.ybvo.YBNCBillHVO.class);
			// pk_corp 用pk_org去查相应帐套号
			queryBodySql = buildBodySql(tableName_B,
					login_inform.get("dr_pk_org"), queryHeadSql);// 表体sql
			// 查询表体
			bvos = (YBNCBillBVO[]) gp.selectBySql2(queryBodySql,
					nc.vo.hzyb.ybvo.YBNCBillBVO.class);
			// 组织单据VO
			bills = buildBills(hvos, bvos);
			// 根据流水号重组VO
			bills = rebuildBills(bills);
			// 回写，表示完成数据提取
			/*
			 * this.agdataRewriteFlag(tableName_H, bills,
			 * nc.bs.hzyb.sql.UpdateInform.done);
			 */
		} catch (SQLException e) {
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (SystemException e) {
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (NamingException e) {
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return bills;
	}

	private void agdataRewriteFlag(String tableName_H, AggYBNCBillHVO[] aggvos,
			int flag) {
		String updateSql = "update hzsi." + tableName_H + " set flag = " + flag
				+ " where nc_voucher in (";
		int n = 0;
		if (aggvos.length == 0)
			updateSql += "''";
		else {
			Iterator<String> it = keyList.iterator();
			while (it.hasNext()) {
				if (n >= 1) {
					updateSql += ",";
				}
				updateSql += it.next(); // 缺少对n>1000的处理
				n++;
			}
			// keyList.clear();
		}
		updateSql += ")";
		try {
			int row = -1;
			excute = MidTableExcute.getInstance();
			row = excute.oracleUpdate(this.getDbName(), updateSql);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
		}
	}

	public void agrewriteRetVO1(String tableName_H, AggYBNCBillHVO[] aggvos,
			int flag, String voucherno) {

		int n = 0;
		flag = 0;
		String ts = new UFDateTime(System.currentTimeMillis()).toString();// 回写系统时间
		String ts_date = ts.substring(0, 10);

		StringBuffer seqcode = new StringBuffer(ts_date);

		String finalseqcode = seqcode.toString();
		String updateSql = "update hzsi." + tableName_H + " set flag = " + flag
				+ " , posting_date = '" + ts + "', account = '" + finalseqcode
				+ "' where nc_voucher in (";
		for (AggYBNCBillHVO vo : aggvos) {
			if (aggvos.length == 0)
				updateSql += "''";
			else {
				if (n >= 1) {
					updateSql += ",";
				}
				YBNCBillHVO hvo = (YBNCBillHVO) vo.getParentVO();
				if (hvo.getOpertype().equals("NY01")
						|| hvo.getOpertype().equals("NY02")
						|| hvo.getOpertype().equals("NY03")) {
					updateSql += hvo.getNc_voucher().substring(0, 8);
				} else {
					updateSql += hvo.getNc_voucher();
				}
				n++;
			}
		}
		updateSql += ")";
		try {
			int row = -1;
			excute = MidTableExcute.getInstance();
			row = excute.oracleUpdate(this.getDbName(), updateSql);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
		}
	}

}
