package nc.bs.hzsb.plugins;

import java.sql.SQLException;
import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.pub.DataManageObject;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.hzsb.pub.Log;
import nc.vo.hzsb.sbvo.SBNCBillBVO;

public class GetPk extends DataManageObject {
	public static nc.impl.pubapp.pattern.database.DataAccessUtils sqlUtil = new nc.impl.pubapp.pattern.database.DataAccessUtils();

	private static BaseDAO dao = null;

	private String dbName;

	public GetPk(String dbName) throws javax.naming.NamingException,
			nc.bs.pub.SystemException {
		super(dbName);
		this.dbName = dbName;
	}

	public static BaseDAO getBaseDao(String dbName) {
		if (dao == null) {
			dao = new BaseDAO(dbName);
		}
		return dao;
	}

	public SBNCBillBVO[] selectBySql2(String querySql, Class voClass)
			throws Exception {
		return selectBySql2(querySql, voClass, null, null);
	}

	@SuppressWarnings("unchecked")
	public SBNCBillBVO[] selectBySql2(String querySql, Class voClass,
			ArrayList values, ArrayList javatypes) throws Exception {
		byte flag = -1; // 0---参数合法，不需要执行setStmt;1--参数非法；2---参数合法，需要执行setStmt.
		if (values == null) {
			if (javatypes == null)
				flag = 0;
			else
				flag = 1;
		} else {
			if (javatypes == null)
				flag = 1;
			else if (values.size() == javatypes.size())
				flag = 2;
			else
				flag = 1;
		}
		if (flag == 1)
			throw new SQLException(nc.bs.ml.NCLangResOnserver.getInstance()
					.getStrByID("nc_scm_smart", "UPPnc_scm_smart-000000")/*
																		 * @res
																		 * "程序错误：参数非法。"
																		 */);
		ArrayList<SBNCBillBVO> aryData = new ArrayList<SBNCBillBVO>();
		try {
			Log.getInstance().info("====数据源名字======"+dbName);
			Log.getInstance().info("====sql名字======"+querySql);
			Log.getInstance().info("====voClass名字======"+voClass);
			aryData = (ArrayList<SBNCBillBVO>) getBaseDao(dbName).executeQuery(
					querySql, new BeanListProcessor(voClass));
			if (aryData.size() < 1) {
				throw new Exception("=未查询到中间表记录=");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			Log.getInstance().info("查询表体时发生错误： " + e);
			throw new Exception("查询中间表数时出现错误： " +e);
		}
		Object[] ret = (Object[]) java.lang.reflect.Array.newInstance(voClass,
				aryData.size());
		for (int i = 0, j = ret.length; i < j; i++) {
			ret[i] = aryData.get(i);
		}
		return (SBNCBillBVO[]) ret;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public static String getPK_voucher(String id) throws BusinessException {

		String sql = "select g.pk_voucher from gl_voucher g, fip_relation r where r.des_relationid=g.pk_voucher and r.busimessage1='"
				+ id + "'";
		String pk_voucher = "";
		IRowSet rs = GetPk.sqlUtil.query(sql);
		while (rs.next()) {
			pk_voucher = rs.getString(0);
		}
		return pk_voucher;
	}

	public static String getPkOpertype(String NC_billtype) {
		String sql = "select pk_billtypeid from bd_billtype where pk_billtypecode= '"
				+ NC_billtype + "'";
		String billtype = "";
		IRowSet rs = GetPk.sqlUtil.query(sql);
		while (rs.next()) {
			billtype = rs.getString(0);// itemID
		}
		return billtype;
	}

	public static String getPkOperator(String operator) {
		String sql = "select cuserid from sm_user where user_name= '"
				+ operator + "'";
		String pk_operator = "";
		IRowSet rs = GetPk.sqlUtil.query(sql);
		while (rs.next()) {
			pk_operator = rs.getString(0);// itemID
		}
		return pk_operator;
	}

	public static String getPkOrg_v(String pk_org) {
		String sql = "select pk_vid from org_corp where pk_corp= '" + pk_org
				+ "'";
		String pk_org_v = "";
		IRowSet rs = GetPk.sqlUtil.query(sql);
		while (rs.next()) {
			pk_org_v = rs.getString(0);// itemID
		}
		return pk_org_v;
	}

	/*
	 * public static String getNC_billtype(String billtypename) { String sql =
	 * "select pk_billtypecode from bd_billtype where billtypename= '" +
	 * billtypename + "'"; String nc_billtype = ""; IRowSet rs =
	 * GetPk.sqlUtil.query(sql); while (rs.next()) { nc_billtype =
	 * rs.getString(0);// itemID } return nc_billtype; }
	 */
}
