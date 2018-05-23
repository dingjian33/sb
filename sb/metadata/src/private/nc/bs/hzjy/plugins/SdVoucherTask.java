package nc.bs.hzjy.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.hzjy.fipVoucher.FipToVoucher;
import nc.bs.hzjy.sql.MidTableFactory;
import nc.itf.agSync.JyAgVoucherSyncItf;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.hzjy.jyvo.AggJYNCBillHVO;
import nc.vo.hzjy.jyvo.JYNCBillBVO;
import nc.vo.hzjy.jyvo.JYNCBillHVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.hzjy.pub.Log;

public class SdVoucherTask implements JyAgVoucherSyncItf {

	/**
	 * 
	 */
	public static String configFilePath = "modules/arap/META-INF/arap_hzjy1.properties";

	private static HashMap<String, String> login_inform = new HashMap<String, String>(); // �洢��ǰ��¼ʱ��һЩ��Ϣ
	private static String tableName = null; // "ad50";
	private static String DBIp = null; // "172.16.68.101:1521";
	private static String datasource = null; // "hzrsrac"; //
	private static String sid = null;
	private static String username = null; // "hzrs";
	private static String password = null; // "hzrs";
	private static String NC_billtype = null; // "S201";���ݱ�� ����֮�� ���»�ȡ
	public static DataSwitch data = new DataSwitch();

	private static String pk_org_v;

	private static String pk_opertype;// ��������
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

		AggJYNCBillHVO[] NCbillvo = null;// 201
		this.readDateInform();// ��ȡ�����ļ�

		try {
			MidTableFactory sqlfactory = new MidTableFactory();
			// ��ѯ�м����������Ϣ
			Log.getInstance().info("��ѯ�м����������Ϣ");
			NCbillvo = sqlfactory.querySBBillVO(datasource, tableName, id);
			// add by fulj 20170409 �����м����¼�ӷ���ƽ̨ǰ������
			int iins = sqlfactory.Insert2NCBills(NCbillvo);
			Log.getInstance().info("�����м��¼��" + String.valueOf(iins) + "��");
			// ��ȡ����vo�л�ȡ�����Ϣ
			for (AggJYNCBillHVO NCbillvos : NCbillvo) {
				// SBNCBillHVO hvo = (SBNCBillHVO)NCbillvos.getParent();
				JYNCBillBVO[] bvos = (JYNCBillBVO[]) NCbillvos.getChildrenVO();
				JYNCBillBVO bvo = bvos[0];
				// �ò�ѯ����������ȥ���ݿ���Ҷ�Ӧ��pk�ֶΣ�������
				String operator = bvo.getOperator();
				pk_operator = GetPk.getPkOperator(operator);
				accno = bvo.getAccno();
				String opertype = bvo.getOpertype();
				NC_billtype = "J201-Cxx-" + opertype;
				pk_opertype = GetPk.getPkOpertype(NC_billtype);
			}
			pk_group = "0001A6100000000002TD";
			pk_org = data.corpSwitch(accno);
			pk_org_v = GetPk.getPkOrg_v(pk_org);
			// �ӹ� ����pk����
			Log.getInstance().info("�齨pkֵ");
			NCbillvo = changeToNCBill(NCbillvo, pk_org, NC_billtype,
					pk_opertype, pk_operator, pk_group, pk_org_v);
			// ���ͻ��ƽ̨
			// FipMsgResultVO[] fipMsgResultVOs = null;
			Log.getInstance().info("���������ƽ̨");
			FipToVoucher<AggJYNCBillHVO> fipVoucher = new FipToVoucher<AggJYNCBillHVO>();
			fipVoucher.sendFipMsg(NCbillvo);
			Log.getInstance().info("���ͷ���ִ�����");
			// ��ȡƾ֤��
			nc_voucher = GetPk.getPK_voucher(id);
			if (nc_voucher.equals("")) {
				for (int i = 0; i < 1; i++) {
					Log.getInstance().info("��һ��û������ƾ֤�ţ���һ�η��������ƽ̨");
					fipVoucher.sendFipMsg(NCbillvo);
					Log.getInstance().info("�ڶ��η��ͷ���ִ�����");
				}
				nc_voucher = GetPk.getPK_voucher(id);
			}
			Log.getInstance().info("=======================");
			Log.getInstance().info("ƾ֤�ţ�" + nc_voucher);
			Log.getInstance().info("ƾ֤�ţ�" + nc_voucher + "����id�� " + id);

			if (nc_voucher != null && !nc_voucher.equals("")) {
				// // ��д��سɹ���Ϣ

				String ts = UpdateVoucherInform(tableName, nc_voucher,
						nc.bs.hzjy.sql.UpdateInform.success, id);

				json = " { \"successs\" : true, \"flag\" : \"1\" ,\"errorMessage\":null ,\"reserve5\" :"
						+ ts + " ,\"nc_voucher\" :" + nc_voucher + " }";
			} else {
				json = " { \"successs\" : false, \"flag\" : "
						+ flag
						+ " ,\"errorMessage\":\"ƾ֤δ���ɣ�ƾ֤��Ϊ��,���������ƽ̨ʱ��������\" ,\"reserve5\" :null ,\"nc_voucher\" :null }";
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

	public AggJYNCBillHVO[] changeToNCBill(AggJYNCBillHVO[] billvos,
			String pk_org, String NC_billtype, String pk_opertype,
			String pk_operator, String pk_group, String pk_org_v) {

		SequenceGenerator se = new SequenceGenerator();
		BaseDAO basedao = new BaseDAO();
		String pk_feesubj = null;
		String pk_enpprop = null;
		for (AggJYNCBillHVO billvo : billvos) {
			String pk_billid = se.generate(); // ����NC����,һ��ҵ�������� ��Ӧһ��20λ����
			JYNCBillBVO[] bodys = (JYNCBillBVO[]) billvo.getChildrenVO();
			JYNCBillHVO hvo = (JYNCBillHVO) billvo.getParentVO();
			hvo.setPk_billid(pk_billid);
			hvo.setPk_billtype(NC_billtype);
			hvo.setPk_org(pk_org);
			hvo.setPk_group(pk_group);
			hvo.setPk_org_v(pk_org_v);
			hvo.setPk_busitype(pk_opertype);// ҵ������
			hvo.setVbys1(bodys[0].getRemark()); // ���ڽ�����ҵ���¼����Ӧ�����кϲ���һ����¼ʱ����ƾ֤ģ����ժҪ��ȡ�ֶΣ�����ҵ��ʹ�ã�
			for (JYNCBillBVO body : bodys) {
				String oriOpertype = body.getOpertype();
				body.setPrimaryKey(pk_billid);
				body.setPk_billid(pk_billid);
				body.setPk_billtype(NC_billtype);// ��������
				body.setPk_busitype(pk_opertype);// ҵ������
				body.setPk_group(pk_group);
				body.setPk_org(pk_org);
				body.setPk_org_v(pk_org_v);
				body.setOperatorid(pk_operator); // �����Ƶ���
				body.setDcreatedate(new UFDate(body.getOperatetime())); // ����ҵ������
				String orisubj = body.getFeeitem();
				// ������ƿ�Ŀת��
				String feesubjsql = "select a.pk_accasoa from  bd_accasoa a inner join bd_accchart c on a.pk_accchart=c.pk_accchart inner join bd_account b on a.pk_account = b.pk_account where c.pk_org = '"
						+ pk_org + "' and b.code  = '" + orisubj + "'";
				try {
					pk_feesubj = (String) basedao.executeQuery(feesubjsql,
							new ColumnProcessor());
				} catch (DAOException e) {
					e.printStackTrace();
				}

				body.setFeeitem(pk_feesubj);

				// ��������
				if (body.getEnpprop() != null) {
					Integer enpcode = new Integer(body.getEnpprop());

					String enpprop = "select pk_defdoc from bd_defdoc where code = '"
							+ enpcode + "'";
					try {
						pk_enpprop = (String) basedao.executeQuery(enpprop,
								new ColumnProcessor());
					} catch (DAOException e) {
						e.printStackTrace();
					}

					body.setEnpprop(pk_enpprop);
				}

				body.setDr(0);
				body.setTs(new UFDateTime(System.currentTimeMillis()));

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
			AggJYNCBillHVO[] NCbillvo, String nc_voucher, int success) {
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
		AggJYNCBillHVO[] NCbillvo = null;// 201

		this.readDateInform();// ��ȡ�����ļ�

		try {
			MidTableFactory sqlfactory = new MidTableFactory();

			// nc���в�ѯ��Ӧpk������
			login_inform = sqlfactory.getQueryInform(login_inform, hmp);
			login_inform = data.corpSwitch(login_inform);
			// ��ѯ�м����������Ϣ
			NCbillvo = sqlfactory.agQuerySBBillVO(datasource, tableName,
					login_inform);

			pk_group = "0001A610000000000NWC";
			pk_org_v = GetPk.getPkOrg_v(login_inform.get("dr_pkorg"));
			// �ӹ� ����pk����
			NCbillvo = changeToNCBill(NCbillvo, login_inform.get("dr_pkorg"),
					login_inform.get("dr_NC_billtype"),
					login_inform.get("NC_billtype"),
					login_inform.get("dr_operator"), pk_group, pk_org_v);
			// ���ͻ��ƽ̨
			// FipMsgResultVO[] fipMsgResultVOs = null;
			FipToVoucher<AggJYNCBillHVO> fipVoucher = new FipToVoucher<AggJYNCBillHVO>();
			fipVoucher.sendFipMsg(NCbillvo);
			// ��ȡƾ֤��
			String reserve2 = "";
			for (AggJYNCBillHVO billvo : NCbillvo) {
				JYNCBillBVO[] bodys = (JYNCBillBVO[]) billvo.getChildrenVO();
				reserve2 = bodys[0].getReserve2();
			}
			nc_voucher = GetPk.getPK_voucher(reserve2);
			if (nc_voucher.equals("")) {
				for (int i = 0; i < 1; i++) {
					fipVoucher.sendFipMsg(NCbillvo);
				}
				nc_voucher = GetPk.getPK_voucher(reserve2);
			}
			// // ��д��سɹ���Ϣ
			agUpdateVoucherInform(tableName, NCbillvo, nc_voucher,
					nc.bs.hzjy.sql.UpdateInform.success);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}