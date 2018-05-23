package nc.bs.hzjy.sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.hzjy.plugins.GetPk;
import nc.bs.hzjy.sql.MidTableExcute;
import nc.bs.logging.Logger;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.hzjy.pub.MapList;
import nc.vo.hzsb.sbvo.AggSBBillHVO;
import nc.vo.hzjy.jyvo.AggJYNCBillHVO;
import nc.vo.hzjy.jyvo.JYNCBillBVO;
import nc.vo.hzjy.jyvo.JYNCBillHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.hzjy.pub.Log;

/**
 * 
 * @author liuty
 * 
 */
public class MidTableFactory {

	private MidTableExcute excute = new MidTableExcute();
	private String dbName;

	public MidTableFactory() {
	}

	public MidTableFactory(String dbname) {
		setDbName(dbname);
	}

	@SuppressWarnings({ "unused" })
	public AggJYNCBillHVO[] querySBBillVO(String datasource, String tableName,
			String id) throws Exception {
		AggJYNCBillHVO[] bills = null;
		JYNCBillBVO[] bvos = null;

		/*
		 * String querySql = "select * from "+ tableName + " where reserve2 ='"
		 * + id + "'";
		 */

		String querySql = "select * from "+ tableName
				+ " where reserve2 ='" + id + "'";

		Map<String, Object> data = new HashMap<String, Object>();
		// 直接查询中间表数据
		data = this.sql(tableName, querySql);
		Log.getInstance().info("直接查询中间数据======" + data.size());
		try {
			GetPk gp = new GetPk(datasource);
			Log.getInstance().info("进入查询方法时的数据源======" + datasource);
			bvos = (JYNCBillBVO[]) gp.selectBySql2(querySql,
					nc.vo.hzjy.jyvo.JYNCBillBVO.class);
			if (bvos.length < 1) {
				throw new Exception("===未查询到中间表数据===");
			}
			// 组织单据VO
			bills = buildBills(bvos);

			// 回写，表示数完成据提取
			// this.RetVOIsReady(tableName, nc.bs.hzsb.sql.UpdateInform.done,
			// id);

		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error(e);
			throw new Exception("query方法未查询到中间表数据" + e);
		}
		return bills;
	}

	public AggJYNCBillHVO[] agQuerySBBillVO(String datasource,
			String tableName, HashMap<String, String> login_inform)
			throws Exception {
		AggJYNCBillHVO[] bills = null;
		JYNCBillBVO[] bvos = null;

		String querySql = "select * from " + tableName + " where operator = '"
				+ login_inform.get("dr_operator") + "'" + " and opertype = '"
				+ login_inform.get("dr_busi") + "'" + " and accno = '"
				+ login_inform.get("dr_pk_org") + "'" + " and operatetime >='"
				+ login_inform.get("dr_startTime") + "'"
				+ " and operatetime <='" + login_inform.get("dr_endTime") + "'"
				+ " ";

		try {
			GetPk gp = new GetPk(datasource);
			bvos = (JYNCBillBVO[]) gp.selectBySql2(querySql,
					nc.vo.hzjy.jyvo.JYNCBillBVO.class);

			// 组织单据VO
			bills = buildBills(bvos);
			// 回写，表示数完成据提取
			/*
			 * this.agRetVOIsReady(tableName, bills,
			 * nc.bs.hzsb.sql.UpdateInform.done);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			Log.getInstance().error(e);
		}
		return bills;
	}

	/**
	 * 发送前存储数据
	 * 
	 * @param nCbillvo
	 * @throws BusinessException
	 */
	public int Insert2NCBills(AggJYNCBillHVO[] nCbillvo)
			throws BusinessException {

		BaseDAO basedao = new BaseDAO();
		int icount = 0;
		try {
			for (int i = 0; i < nCbillvo.length; i++) {
				JYNCBillBVO[] bvos = (JYNCBillBVO[]) nCbillvo[i]
						.getChildrenVO();
				for (JYNCBillBVO body : bvos) {
					String inssql = " insert into NCAD50_J  (sid, dataprimaryid, tablesource, datasource, issue, deptno, deptname,"
							+ " accno, opertype, opertypename, feeitem, feeitemname, direction, remark, enpprop, fund, bank, bankacc,"
							+ " billno, operator, operatetime, flag, nc_voucher, nc_error, reserve1, reserve2,"
							+ " reserve3, reserve4, reserve5) values ('"
							+ body.getSid()
							+ "', '','"
							+ body.getTablesource()
							+ "', "
							+ " '"
							+ body.getDatasource()
							+ "', '"
							+ body.getIssue()
							+ "', "
							+ " '"
							+ body.getDeptno()
							+ "', '"
							+ body.getDeptname()
							+ "', "
							+ " '"
							+ body.getAccno()
							+ "', '"
							+ body.getOpertype()
							+ "', "
							+ " '"
							+ body.getOpertypename()
							+ "', '"
							+ body.getFeeitem()
							+ "', '"
							+ body.getFeeitemname()
							+ "', "
							+ " '"
							+ body.getDirection()
							+ "', '"
							+ body.getRemark()
							+ "',"
							+ " '"
							+ body.getEnpprop()
							+ "', '"
							+ body.getFund()
							+ "', '', '', '', '"
							+ body.getOperatorid()
							+ "', "
							+ " to_date('"
							+ body.getOperatetime()
							+ "','yyyy-mm-dd'),"
							+ " '"
							+ body.getFlag()
							+ "', '', '', '', '"
							+ body.getReserve2() + "', '', '', '') ";

					icount += basedao.executeUpdate(inssql);
				}
			}
		} catch (BusinessException ex) {
			Logger.error(ex.getMessage(), ex);
			throw new BusinessException(ex.getMessage(), ex);
		}
		return icount;
	}

	public void RetVOIsReady(String tableName, Integer flag, String id) {

		String updateSql = "update " + tableName + " set flag = '" + flag
				+ "' where RESERVE2 = '" + id + "'";
		try {
			MidTableExcute up = new MidTableExcute();
			up.oracleUpdate(tableName, updateSql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void agRetVOIsReady(String tableName, AggJYNCBillHVO[] bills,
			Integer flag) {

		String WaterID = null;
		int k = 0;
		String updateSql = "update " + tableName + " set flag = '" + flag
				+ "' where RESERVE2 in (";
		for (int i = 0; i < bills.length; i++) {
			JYNCBillBVO[] bvos = (JYNCBillBVO[]) bills[i].getChildrenVO();
			WaterID = bvos[0].getReserve2();
			if (i == 0)
				updateSql += WaterID;
			else if (i < 1000 * (k + 1) && i > 1000 * k) { // 实际大于1000 都会报SQL错误
				updateSql += "," + WaterID;
			} else if (i == 1000 * (k + 1)) {
				updateSql += ") or RESERVE2 in (" + WaterID;
				k++;
			}
		}
		updateSql += ")";
		try {
			MidTableExcute up = new MidTableExcute();
			up.oracleUpdate(tableName, updateSql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	private AggJYNCBillHVO[] buildBills(JYNCBillBVO[] bvos) {

		List<AggJYNCBillHVO> lst = new ArrayList<AggJYNCBillHVO>();
		MapList<String, JYNCBillBVO> ml = new MapList<String, JYNCBillBVO>();
		for (JYNCBillBVO bvo : bvos) {
			String str = bvo.getReserve2();
			ml.put(str, bvo);
		}
		Iterator<String> it = ml.keySet().iterator();
		while (it.hasNext()) {
			JYNCBillHVO hvo = new JYNCBillHVO();
			String key = ((String) it.next());
			hvo.setVssid(key);
			List<JYNCBillBVO> bodys = ml.get(key);
			if (bodys != null) {
				AggJYNCBillHVO bill = new AggJYNCBillHVO();
				bill.setParentVO(hvo);
				bill.setChildrenVO(bodys.toArray(new JYNCBillBVO[0]));
				lst.add(bill);
			}
		}
		return lst.toArray(new AggJYNCBillHVO[0]); // 初始化空数组
	}

	public String RetVOIsDone(String tableName, String pk_voucher, int flag,
			String id) {
		int row = -1;
		flag = 0;
		String ts = new UFDateTime(System.currentTimeMillis()).toString();// 回写系统时间
		String updateSql = "update " + tableName + " set flag = '" + flag
				+ "' , RESERVE5 = '"
				+ ts // 回写生成时间
				+ "' , nc_voucher = '" + pk_voucher + "' where RESERVE2 = '"
				+ id + "'";
		try {
			row = excute.oracleUpdate(this.getDbName(), updateSql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return ts;
	}

	public void agRetVOIsDone(String tableName, AggJYNCBillHVO[] aggvos,
			String nc_voucher, int flag) {
		int row = -1;
		int k = 0;
		flag = 0;
		String WaterID = null;
		String ts = new UFDateTime(System.currentTimeMillis()).toString();// 回写系统时间
		String updateSql = "update " + tableName + " set flag = '" + flag
				+ "' , RESERVE1 = '" + ts // 回写生成时间
				+ "' where RESERVE2 in (";
		for (int i = 0; i < aggvos.length; i++) {
			JYNCBillBVO[] bvos = (JYNCBillBVO[]) aggvos[i].getChildrenVO();
			WaterID = bvos[0].getReserve2();
			if (i == 0)
				updateSql += WaterID;
			else if (i < 1000 * (k + 1) && i > 1000 * k) { // 实际大于1000 都会报SQL错误
				updateSql += "," + WaterID;
			} else if (i == 1000 * (k + 1)) {
				updateSql += ") or RESERVE2 in (" + WaterID;
				k++;
			}
		}
		updateSql += ")";
		try {
			row = excute.oracleUpdate(this.getDbName(), updateSql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void dataRewriteError(String tableName_B, AggSBBillHVO[] vos,
			String errormsg) {
		/*
		 * 错误信息回写
		 */
		for (AggSBBillHVO vo : vos) {
			String updateSql = "update " + tableName_B + " set NC_error = "
					+ errormsg + "where billid in (";
			for (int i = 0; i < vo.getChildrenVO().length; i++) {
				if (vos.length == 0)
					updateSql += "''";
				else {
					if (i > 1)
						updateSql += ",";
				}
				updateSql += ((JYNCBillBVO) vo.getChildrenVO()[i]).getSid();
			}
			updateSql += ")";
			try {
				MidTableExcute update = new MidTableExcute();
				update.oracleUpdate(tableName_B, updateSql);
			} catch (SQLException e) {
				e.printStackTrace();
				Log.getInstance().error(e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				Log.getInstance().error(e);
			}

		}
	}

	public Map<String, Object> sql(String dbname, String sql) {

		Map<String, Object> obj = new HashMap<String, Object>();
		try {

			MidTableExcute excute = new MidTableExcute();
			obj = excute.sql(dbname, sql);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(e.getMessage(), e);
		}
		return obj;
	}

	public HashMap<String, String> getQueryInform(
			HashMap<String, String> login_inform, HashMap<String, String> hmp) {

		login_inform.put("accountBook", hmp.get("accountBook"));
		login_inform.put("group", hmp.get("group"));
		login_inform.put("NC_billtype", hmp.get("NC_billtype"));
		login_inform.put("operator", hmp.get("operator"));
		login_inform.put("startTime", hmp.get("startTime"));
		login_inform.put("endTime", hmp.get("endTime"));

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

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String _dbname) {
		this.dbName = _dbname;

	}
}
