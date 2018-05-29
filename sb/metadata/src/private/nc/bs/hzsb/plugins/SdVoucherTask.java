package nc.bs.hzsb.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.hzsb.fipVoucher.FipToVoucher;
import nc.bs.hzsb.sql.MidTableFactory;
import nc.itf.agSync.SbAgVoucherSyncItf;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.hzsb.sbvo.AggSBNCBillHVO;
import nc.vo.hzsb.sbvo.SBNCBillBVO;
import nc.vo.hzsb.sbvo.SBNCBillHVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.hzsb.pub.Log;

public class SdVoucherTask implements SbAgVoucherSyncItf {

	/**
	 * 
	 */
	public static String configFilePath = "modules/arap/META-INF/arap_hzsb1.properties";

	private static HashMap<String, String> login_inform = new HashMap<String, String>(); // 存储当前登录时的一些信息
	private static String tableName = null; // "ad50";
	private static String DBIp = null; // "172.16.68.101:1521";
	private static String datasource = null; // "hzrsrac"; //
	private static String sid = null;
	private static String username = null; // "hzrs";
	private static String password = null; // "hzrs";
	private static String NC_billtype = null; // "S201";单据编号 更新之后 重新获取
	public static DataSwitch data = new DataSwitch();

	private static String pk_org_v;

	private static String pk_opertype;// 交易类型
	private static String accno;
	private static String pk_operator;
	private static String nc_voucher;
	private static String pk_org;
	private static String pk_group;
	private static int flag = 0;

	private String json = "";

	public SdVoucherTask() {
	}

	public String executeTask(String id) throws Exception {

		AggSBNCBillHVO[] NCbillvo = null;// 201
		this.readDateInform();// 读取配置文件

		try {
			MidTableFactory sqlfactory = new MidTableFactory();
			// 查询中间表的所有信息
			Log.getInstance().info("查询中间表的所有信息");
			NCbillvo = sqlfactory.querySBBillVO(datasource, tableName, id);
			// add by fulj 20170409 插入中间表记录从发送平台前的数据
			int iins = sqlfactory.Insert2NCBills(NCbillvo);
			Log.getInstance().info("插入中间记录表" + String.valueOf(iins) + "条");
			// 从取到的vo中获取相关信息
			for (AggSBNCBillHVO NCbillvos : NCbillvo) {
				// SBNCBillHVO hvo = (SBNCBillHVO)NCbillvos.getParent();
				SBNCBillBVO[] bvos = (SBNCBillBVO[]) NCbillvos.getChildrenVO();
				SBNCBillBVO bvo = bvos[0];
				// 用查询出来的数据去数据库查找对应的pk字段（主键）
				String operator = bvo.getOperator();
				pk_operator = GetPk.getPkOperator(operator);
				accno = bvo.getAccno();
				String opertype = bvo.getOpertype();
				NC_billtype = "S201-Cxx-" + opertype;
				pk_opertype = GetPk.getPkOpertype(NC_billtype);
			}
			pk_group = "0001A6100000000002TD";
			pk_org = data.corpSwitch(accno);
			pk_org_v = GetPk.getPkOrg_v(pk_org);
			// 加工 添加pk数据
			Log.getInstance().info("组建pk值");
			NCbillvo = changeToNCBill(NCbillvo, pk_org, NC_billtype,
					pk_opertype, pk_operator, pk_group, pk_org_v);
			// 发送会计平台
			// FipMsgResultVO[] fipMsgResultVOs = null;
			Log.getInstance().info("发送至会计平台");
			FipToVoucher<AggSBNCBillHVO> fipVoucher = new FipToVoucher<AggSBNCBillHVO>();
			fipVoucher.sendFipMsg(NCbillvo);
			Log.getInstance().info("发送方法执行完成");
			// 获取凭证号
			nc_voucher = GetPk.getPK_voucher(id);
			if (nc_voucher.equals("")) {
				for (int i = 0; i < 1; i++) {
					Log.getInstance().info("第一次没有生成凭证号，再一次发送至会计平台");
					fipVoucher.sendFipMsg(NCbillvo);
					Log.getInstance().info("第二次发送方法执行完成");
				}
				nc_voucher = GetPk.getPK_voucher(id);
			}
			Log.getInstance().info("=======================");
			Log.getInstance().info("凭证号：" + nc_voucher);
			Log.getInstance().info("凭证号：" + nc_voucher + "传入id： " + id);

			if (nc_voucher != null && !nc_voucher.equals("")) {
				// // 回写相关成功信息

				String ts = UpdateVoucherInform(tableName, nc_voucher,
						nc.bs.hzsb.sql.UpdateInform.success, id);

				json = " { \"successs\" : true, \"flag\" : \"1\" ,\"errorMessage\":null ,\"reserve5\" :"
						+ ts + " ,\"nc_voucher\" :" + nc_voucher + " }";
			} else {
				json = " { \"successs\" : false, \"flag\" : "
						+ flag
						+ " ,\"errorMessage\":\"凭证未生成，凭证号为空,发送至会计平台时发生错误\" ,\"reserve5\" :null ,\"nc_voucher\" :null }";
			}
		} catch (Exception e) {
			json = " { \"successs\" : false, \"flag\" : " + flag
					+ " ,\"errorMessage\":" + e.toString()
					+ " ,\"reserve5\" :null ,\"nc_voucher\" :null }";
			return json;
		}
		return json;
	}

	private void readDateInform() {
		FileInputStream fin = null;
		Properties prop = new Properties();
		try {
			fin = new FileInputStream(new File(SdVoucherTask.configFilePath));
			prop.load(fin);
			setTableName(prop.getProperty("tableName"));
			setDBIp(prop.getProperty("DBip"));
			setDatasource(prop.getProperty("datasource"));
			setUsername(prop.getProperty("userName"));
			setPassword(prop.getProperty("passWord"));
			setSid(prop.getProperty("sid"));
			setNC_billtype(prop.getProperty("billtype"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public AggSBNCBillHVO[] changeToNCBill(AggSBNCBillHVO[] billvos,
			String pk_org, String NC_billtype, String pk_opertype,
			String pk_operator, String pk_group, String pk_org_v) {

		SequenceGenerator se = new SequenceGenerator();
		BaseDAO basedao = new BaseDAO();
		String pk_feesubj = null;
		String pk_enpprop = null;
		for (AggSBNCBillHVO billvo : billvos) {
			String pk_billid = se.generate(); // 生成NC主键,一个业务数据组 对应一个20位主键
			SBNCBillBVO[] bodys = (SBNCBillBVO[]) billvo.getChildrenVO();
			SBNCBillHVO hvo = (SBNCBillHVO) billvo.getParentVO();
			hvo.setPk_billid(pk_billid);
			hvo.setPk_billtype(NC_billtype);
			hvo.setPk_org(pk_org);
			hvo.setPk_group(pk_group);
			hvo.setPk_org_v(pk_org_v);
			hvo.setPk_busitype(pk_opertype);// 业务类型
			hvo.setVbys1(bodys[0].getRemark()); // 用于将多条业务分录所对应的银行合并成一条记录时，在凭证模板上摘要索取字段（根据业务使用）
			for (SBNCBillBVO body : bodys) {
				String oriOpertype = body.getOpertype();
				body.setPrimaryKey(pk_billid);
				body.setPk_billid(pk_billid);
				body.setPk_billtype(NC_billtype);// 单据类型
				body.setPk_busitype(pk_opertype);// 业务类型
				body.setPk_group(pk_group);
				body.setPk_org(pk_org);
				body.setPk_org_v(pk_org_v);
				body.setOperatorid(pk_operator); // 设置制单人
			    String issue1 = body.getIssue().substring(0,4);
			    String issue2 = body.getIssue().substring(4,6);
			    String issue3 = issue1+"-"+issue2+"-01";
				body.setDcreatedate(new UFDate(issue3)); // 添加业务日期..经办日期
				String orisubj = body.getFeeitem();
				// 东软会计科目转化
  				String feesubjsql = "select a.pk_accasoa from  bd_accasoa a inner join bd_accchart c on a.pk_accchart=c.pk_accchart inner join bd_account b on a.pk_account = b.pk_account where c.pk_org = '"
						+ pk_org + "' and b.code  = '" + orisubj + "'";
				try {
					pk_feesubj = (String) basedao.executeQuery(feesubjsql,
							new ColumnProcessor());
				} catch (DAOException e) {
					e.printStackTrace();
				}
				if ("165".equals(oriOpertype) && "4301".equals(orisubj)) {
					body.setReserve4(pk_feesubj);
					body.setFeeitem(null);
					body.setReserve3(body.getFund());
					body.setFund(new UFDouble(0));
				} else {
					body.setFeeitem(pk_feesubj);
				}

				// 辅助核算
				if (body.getEnpprop() != null) {
					if ("601".equals(body.getAccno())) {

						body.setEnpprop("1001A61000000000HUY6");// 企业养老
					} else {
						Integer enpcode = new Integer(body.getEnpprop());
						pk_enpprop = data.subjcodeSwitch(enpcode);
						body.setEnpprop(pk_enpprop);
					}
				}// 610帐套 的 统筹外项目支出 科目，增加辅助核算
				else if (body.getFeeitem().equals("1001A610000000000JXD")) {
					body.setEnpprop("1001A610000000000JQJ"); // 统筹外项目辅助核算
				}
				body.setDr(0);
				body.setTs(new UFDateTime(System.currentTimeMillis()));

				// 611帐套下，地税征收业务（100）增加 储备金 计算（本期实缴的5%） ----2014.10.23
				// liuty-----
				if (body.getAccno().equals("611")
						&& (body.getOpertype().equals("100"))
						&& (body.getFeeitemname().equals("单位缴纳"))) {
					body.setReserve3(body.getFund());
					
				}
			}
		}
		return billvos;
	}

	private String UpdateVoucherInform(String tableName, String nc_voucher,
			int success, String id) {
		MidTableFactory sql = new MidTableFactory();
		String ts = sql.RetVOIsDone(tableName, nc_voucher, success, id);
		return ts;
	}

	private void agUpdateVoucherInform(String tableName,
			AggSBNCBillHVO[] NCbillvo, String nc_voucher, int success) {
		MidTableFactory sql = new MidTableFactory();
		sql.agRetVOIsDone(tableName, NCbillvo, nc_voucher, success);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		SdVoucherTask.tableName = tableName;
	}

	public String getDBIp() {
		return DBIp;
	}

	public void setDBIp(String dBIp) {
		DBIp = dBIp;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		SdVoucherTask.datasource = datasource;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		SdVoucherTask.sid = sid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		SdVoucherTask.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		SdVoucherTask.password = password;
	}

	public String getNC_billtype() {
		return NC_billtype;
	}

	public void setNC_billtype(String nC_billtype) {
		NC_billtype = nC_billtype;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		SdVoucherTask.accno = accno;
	}

	public static String getPk_operator() {
		return pk_operator;
	}

	public static void setPk_operator(String pk_operator) {
		SdVoucherTask.pk_operator = pk_operator;
	}

	public String getNc_voucher() {
		return nc_voucher;
	}

	public void setNc_voucher(String nc_voucher) {
		SdVoucherTask.nc_voucher = nc_voucher;
	}

	public static String getPk_org() {
		return pk_org;
	}

	public static void setPk_org(String pk_org) {
		SdVoucherTask.pk_org = pk_org;
	}

	public static String getPk_opertype() {
		return pk_opertype;
	}

	public static void setPk_opertype(String pk_opertype) {
		SdVoucherTask.pk_opertype = pk_opertype;
	}

	public static String getPk_group() {
		return pk_group;
	}

	public static void setPk_group(String pk_group) {
		SdVoucherTask.pk_group = pk_group;
	}

	public static String getPk_org_v() {
		return pk_org_v;
	}

	public static void setPk_org_v(String pk_org_v) {
		SdVoucherTask.pk_org_v = pk_org_v;
	}

	public static int getFlag() {
		return flag;
	}

	public static void setFlag(int flag) {
		SdVoucherTask.flag = flag;
	}

	@Override
	public String exesoVoucher(HashMap<String, String> hmp) throws Exception {
		AggSBNCBillHVO[] NCbillvo = null;// 201

		this.readDateInform();// 读取配置文件

		try {
			MidTableFactory sqlfactory = new MidTableFactory();

			// nc表中查询对应pk与数据
			login_inform = sqlfactory.getQueryInform(login_inform, hmp);
			login_inform = data.corpSwitch(login_inform);
			// 查询中间表的所有信息
			NCbillvo = sqlfactory.agQuerySBBillVO(datasource, tableName,
					login_inform);

			pk_group = "0001A110000000000477";
			pk_org_v = GetPk.getPkOrg_v(login_inform.get("dr_pkorg"));
			// 加工 添加pk数据
			NCbillvo = changeToNCBill(NCbillvo, login_inform.get("dr_pkorg"),
					login_inform.get("dr_NC_billtype"),
					login_inform.get("NC_billtype"),
					login_inform.get("dr_operator"), pk_group, pk_org_v);
			// 发送会计平台
			// FipMsgResultVO[] fipMsgResultVOs = null;
			FipToVoucher<AggSBNCBillHVO> fipVoucher = new FipToVoucher<AggSBNCBillHVO>();
			fipVoucher.sendFipMsg(NCbillvo);
			// 获取凭证号
			String reserve2 = "";
			for (AggSBNCBillHVO billvo : NCbillvo) {
				SBNCBillBVO[] bodys = (SBNCBillBVO[]) billvo.getChildrenVO();
				reserve2 = bodys[0].getReserve2();
			}
			nc_voucher = GetPk.getPK_voucher(reserve2);
			if (nc_voucher.equals("")) {
				for (int i = 0; i < 1; i++) {
					fipVoucher.sendFipMsg(NCbillvo);
				}
				nc_voucher = GetPk.getPK_voucher(reserve2);
			}
			// // 回写相关成功信息
			agUpdateVoucherInform(tableName, NCbillvo, nc_voucher,
					nc.bs.hzsb.sql.UpdateInform.success);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
