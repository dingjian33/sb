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
	private ArrayList<String> keyList; // ��ԭ��ˮ�ű��棬���ڻ�д�����м��

	public MidTableFactory(String midDBname) {
		this.setDbName(midDBname);
	}

	public MidTableFactory() {
	}

	/**
	 * ��Ҫ�Լ���֯ ��ѯ��ͷ��SQL��ͨ����ͷVO�����������ѯ����VO��key��dr_��ͷ ����ҽ����vo
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
		Logger.info("�Ѳ鵽�м������������ " + sql.size());
		String queryBodySql = null;
		try {
			GetPk gp = new GetPk(datasource);
			Log.getInstance().info("�����ѯ����ʱ������Դ======" + datasource);
			// ���ͷ,����SmartVO��
			hvos = (YBNCBillHVO[]) gp.selectBySql2(queryHeadSql,
					nc.vo.hzyb.ybvo.YBNCBillHVO.class);
			// ��֯�����ѯ���� ---ͨ�����,��ǰ��˾
			// queryBodySql = buildBodySql(hvos, tableName_B
			// ,hmp.get("dr_corp")); pk_corp

			queryBodySql = buildBodySql(tableName_B, queryHeadSql);// ����sql
			// ��ѯ����
			bvos = (YBNCBillBVO[]) gp.selectBySql2(queryBodySql,
					nc.vo.hzyb.ybvo.YBNCBillBVO.class);
			// ��֯����VO

			bills = buildBills(hvos, bvos);
			// ������ˮ������VO

			bills = rebuildBills(bills);
			// ��д����ʾ���������ȡ
			// this.dataRewriteFlag(tableName_H, bills,
			// nc.bs.hzyb.sql.UpdateInform.done, id);
		} catch (SQLException e) {
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
			throw new Exception("��ѯ���ݴ��� " + e);
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
			else if (i < 1000 * (k + 1) && i > 1000 * k) { // ʵ�ʴ���1000 ���ᱨSQL����
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
		// �����������ͷ��������壬��֯���ɵ�������
		// �ñ����������ֵ �齨һ��MapList��ֵ�ԣ�Ȼ���ڱ�ͷ�л�ȡ��������������ڱ�ͷ�������
		// ͨ��������ȡ��ֵ��������Ӧ�� �б�ֵ����Ϊ���ͷ�ı��塣
		// ���Ը�ֵ����ϳɵ���VO��

		// List��������
		List<AggYBNCBillHVO> lst = new ArrayList<AggYBNCBillHVO>();
		// �ñ����������ֵ �齨һ��MapList��ֵ��
		MapList<String, YBNCBillBVO> ml = new MapList<String, YBNCBillBVO>();
		for (YBNCBillBVO bvo : bvos) {
			String str = bvo.getSid();
			ml.put(str, bvo);
		}

		for (YBNCBillHVO hvo : hvos) {
			String key = hvo.getSid(); // ���Ǳ�ͷ��SID
										// ,,,,,,ԭ����hvo.get.getPrimaryKey()
			List<YBNCBillBVO> bodys = ml.get(key); // ѭ���ɱ�ͷ��Ψһsid��ö�Ӧ����ļ�¼������ŵ�list��
			if (bodys != null) {
				AggYBNCBillHVO bill = new AggYBNCBillHVO();
				bill.setParentVO(hvo);
				bill.setChildrenVO(bodys.toArray(new YBNCBillBVO[0]));
				lst.add(bill);
			}
		}
		return lst.toArray(new AggYBNCBillHVO[0]); // ��ʼ��������
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
			// ��ȡ��ˮ������
			key = hvo.getNc_voucher();
			// ��ԭ��ˮ�ű��棬���ڻ�д�����м��
			keyList = new ArrayList<String>();
			if (!keyList.contains(key)) {
				keyList.add(key);
			}
			// ������ͬҵ��Ϊ����֧���㣬����ˮ��ĩβ����֧����
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
			// ������������ �ڴ���Ҫ�ֿ�
			// ����NY09 2012-12-19
			if (data.isBankNeedDivided(hvo.getOpertype())) {
				// UFDouble temp = new UFDouble(0);
				name = hvo.getPname();
				if(!nameList.contains(name)){
					nameList.add(name);
					bvos[0].setEnpname(name); // ����ֺ��ժҪ
					bvos[0].setBys6(hvo.getSummoney()); // ��ֺ󵥼�ҽԺ���ܽ��
				}else{
					String sid = hvo.getSid();
					name = name.concat(sid);
					nameList.add(name);
					bvos[0].setEnpname(name); // ����ֺ��ժҪ
					bvos[0].setBys6(hvo.getSummoney()); // ��ֺ󵥼�ҽԺ���ܽ��
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
		return lst.toArray(new AggYBNCBillHVO[0]); // ��ʼ��������
	}

	/**
	 * ���ݱ�־λ��дdone
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
	 * ���ݱ�־λ��дsuccess
	 */
	public String rewriteRetVO1(String tableName_H, AggYBNCBillHVO[] aggvos,
			int flag, String voucherno, String id) throws DAOException {

		int n = 0;
		flag = 0;
		String ts = new UFDateTime(System.currentTimeMillis()).toString();// ��дϵͳʱ��
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
		String ts = new UFDateTime(System.currentTimeMillis()).toString();// ��дϵͳʱ��
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
		String ts = new UFDateTime(System.currentTimeMillis()).toString();// ��дϵͳʱ��
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

	// ����û��
	public void dataRewriteError(String tableName_B, AggYBNCBillHVO[] vos,
			String errormsg) {
		/*
		 * ������Ϣ��д
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
	 * ������NC����Ϣ���󲿷�����������֯�Ĳ�ѯ�������� login_inform ��key��dr_��ͷ
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
			// ������
			String operatorsql = "select user_name from sm_user where cuserid = '"
					+ login_inform.get("operator") + "'";
			String createman = (String) dao.executeQuery(operatorsql,
					new ColumnProcessor());
			login_inform.put("dr_operator", createman);
			// ҵ������
			String busitypesql = "select pk_billtypecode  from bd_billtype where pk_billtypeid= '"
					+ login_inform.get("NC_billtype") + "'";
			String busitype = (String) dao.executeQuery(busitypesql,
					new ColumnProcessor());
			login_inform.put("dr_NC_billtype", busitype);
			String busi = busitype.substring(9);
			login_inform.put("dr_busi", busi);
			// ��ʼʱ��
			String starttimesql = "select begindate from bd_accperiodmonth where pk_accperiodmonth = '"
					+ login_inform.get("startTime") + "'";
			String startTime = (String) dao.executeQuery(starttimesql,
					new ColumnProcessor());
			String dr_startTime = startTime.substring(0, 10);
			login_inform.put("dr_startTime", dr_startTime);
			// ����ʱ��
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
			// ���ͷ,����SmartVO��
			hvos = (YBNCBillHVO[]) gp.selectBySql2(queryHeadSql,
					nc.vo.hzyb.ybvo.YBNCBillHVO.class);
			// pk_corp ��pk_orgȥ����Ӧ���׺�
			queryBodySql = buildBodySql(tableName_B,
					login_inform.get("dr_pk_org"), queryHeadSql);// ����sql
			// ��ѯ����
			bvos = (YBNCBillBVO[]) gp.selectBySql2(queryBodySql,
					nc.vo.hzyb.ybvo.YBNCBillBVO.class);
			// ��֯����VO
			bills = buildBills(hvos, bvos);
			// ������ˮ������VO
			bills = rebuildBills(bills);
			// ��д����ʾ���������ȡ
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
				updateSql += it.next(); // ȱ�ٶ�n>1000�Ĵ���
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
		String ts = new UFDateTime(System.currentTimeMillis()).toString();// ��дϵͳʱ��
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
