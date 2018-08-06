package nc.bs.hzyb.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import nc.bs.dao.DAOException;
import nc.bs.hzyb.plugins.GetPk;
import nc.bs.hzyb.fipVoucher.FipToVoucher;
import nc.bs.hzyb.sql.MidTableFactory;
import nc.itf.agSync.YbAgVoucherSyncItf;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.vo.fip.service.FipMsgResultVO;
import nc.vo.hzyb.pub.Log;
import nc.vo.hzyb.ybvo.AggYBNCBillHVO;
import nc.vo.hzyb.ybvo.YBNCBillBVO;
import nc.vo.hzyb.ybvo.YBNCBillHVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * ���м��ȡ�����͵����ƽ̨����ƾ֤�ĺ�̨������
 * 
 * ��NC��ע����м��������ΪY202 ע���NC��������ΪY201
 * 
 * @author liutya 2012-12-20
 * 
 */
public class SdVoucherTask implements YbAgVoucherSyncItf {

	private static DataSwitch dataSwitch = new DataSwitch();
	public static String configFilePath = "modules/arap/META-INF/arap_hzyb.properties";
	private static HashMap<String, String> login_inform = new HashMap<String, String>(); // �洢��ǰ��¼ʱ��һЩ��Ϣ
	private static String tableName_H = null;
	private static String tableName_B = null;
	private static String DBIp = null;
	private static String sid = null;
	private static String midDBname = null; // �ⲿmodules/arap/META-INF/arap_hzyb.properties�ж�ȡ
	private static String username = null;
	private static String password = null;
	private static String NC_billtype = null;// Y201 ����֮�����»�ȡ

	public static String[] DaiFangType = null;
	public static String[] YueMo = null;
	public static String[] DaiFangSubj = null;
	private static String voucherno;
	private static String opertype;
	private static String pk_opertype;
	private static String pk_accno;
	private static String pk_operator;
	private static String pk_group;
	private static String pk_org;
	private static String pk_org_v;
	private static int flag = 0;
	private String json = "";

	/**
	 * ����ƾ֤���������
	 * 
	 * @throws Exception
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String executeTask(String id) throws Exception {
		AggYBNCBillHVO[] NCBills = null;

		// ������Դ��Ϣ
		this.readDateInform();

		try {
			MidTableFactory sqlfactory = new MidTableFactory(midDBname);

			// ��ԃ���g�����ݣ�������־λ��д ȡ��
			Log.getInstance().info("��ѯ�м���������Ϣ");
			NCBills = sqlfactory.queryYBBillVO(midDBname, tableName_H,
					tableName_B, id);

			// ��ȡ����vo�л�ȡ�����Ϣ
			for (AggYBNCBillHVO ncbillvos : NCBills) {
				YBNCBillHVO hvo = (YBNCBillHVO) ncbillvos.getParentVO();
				YBNCBillBVO[] bvos = (YBNCBillBVO[]) ncbillvos.getChildrenVO();
				YBNCBillBVO bvo = bvos[0];
				String operator = hvo.getOperperson();
				pk_operator = GetPk.getPkOperator(operator);
				// �ò�ѯ����������ȥ���ݿ���Ҷ�Ӧ��pk�ֶΣ�������
				pk_accno = bvo.getAccno();
				opertype = hvo.getOpertype();
				NC_billtype = "Y201-Cxx-" + opertype;
				pk_opertype = GetPk.getPkOpertype(NC_billtype);

			}

			pk_group = "0001A6100000000002TD";// �����
			pk_org = dataSwitch.corpSwitch(pk_accno);
			pk_org_v = GetPk.getPkOrg_v(pk_org);
			// �ӹ�(��Ӹ��ֶ�����ֵ)
			NCBills = changeToNCBill(NCBills, pk_org, NC_billtype, pk_opertype,
					pk_operator, pk_group, pk_org_v);
			// ���ͻ��ƽ̨

			FipToVoucher<AggYBNCBillHVO> voucher = new FipToVoucher<AggYBNCBillHVO>();
			FipMsgResultVO[] fip = null;
			fip = voucher.sendFipMsg(NCBills);
			if (opertype.equals("NY01") || opertype.equals("NY02")
					|| opertype.equals("NY07") || opertype.equals("NY20")
					|| opertype.equals("NY22")) {
				String vouno = "00000000";
				UpadateVoucherInform1(tableName_H, NCBills,
						nc.bs.hzyb.sql.UpdateInform.success, vouno, id);
				String ts = new UFDateTime(System.currentTimeMillis())
						.toString();// ��дϵͳʱ��
				json = " { \"successs\" : true, \"flag\" :  " + flag
						+ " ,\"errorMessage\":null ,\"posting_date\" :" + ts
						+ " ,\"voucherno\" : ��ʱ���ݣ�����дƾ֤�� �� }";
			} else {
				voucherno = GetPk.getPK_voucher(id);
				if (voucherno.equals("")) {
					for (int i = 0; i < 1; i++) {
						Log.getInstance().info("��һ��û������ƾ֤�ţ���һ�η��������ƽ̨");
						voucher.sendFipMsg(NCBills);
					}
					voucherno = GetPk.getPK_voucher(id);
				}
				Log.getInstance().info("=======================");
				Log.getInstance().info("ƾ֤�ţ�" + voucherno);
				Log.getInstance().info("ƾ֤�ţ�" + voucherno + "����id�� " + id);
				if (voucherno != null && !voucherno.equals("")) {
					// ��д��سɹ���Ϣ,ʵʱƾ֤�������ţ��ɹ���־

					String ts = UpadateVoucherInform(tableName_H, NCBills,
							nc.bs.hzyb.sql.UpdateInform.success, voucherno, id);
					json = " { \"successs\" : true, \"flag\" :  " + flag
							+ " ,\"errorMessage\":null ,\"posting_date\" :"
							+ ts + " ,\"voucherno\" :" + voucherno + " }";
				} else {
					json = " { \"successs\" : false, \"flag\" : "
							+ flag
							+ "  ,\"errorMessage\":\"ƾ֤δ���ɣ�ƾ֤��Ϊ�գ����������ƽ̨���ִ���\" ,\"posting_date\" :null ,\"voucherno\" :null }";
				}
			}
		} catch (Exception e) {
			json = " { \"successs\" : false, \"flag\" :  " + flag
					+ "  ,\"errorMessage\":" + e.toString()
					+ " ,\"posting_date\" :null ,\"voucherno\" :null }";
			return json;
		}
		return json;
	}

	private AggYBNCBillHVO[] changeToNCBill(AggYBNCBillHVO[] NCBills,
			String pk_org, String NC_billtype, String pk_opertype,
			String pk_operator, String pk_group, String pk_org_v) {
		String pk_drsubj = null;
		String pk_ncsubk = null;
		String oriSubjCode = null;
		String typeForShow = null;
		String zhifudian = null;
		String typecode = null;
		SequenceGenerator se = new SequenceGenerator();
		for (AggYBNCBillHVO NCBill : NCBills) {
			YBNCBillHVO head = (YBNCBillHVO) NCBill.getParentVO();// ��ȡ��ͷ
			YBNCBillBVO[] bodys = (YBNCBillBVO[]) NCBill.getChildrenVO();// ��ȡ����
			String pk_billid = se.generate(); // ����NC����
			head.setPrimaryKey(pk_billid);
			head.setPk_billid(pk_billid);
			head.setPk_billtype(NC_billtype);
			head.setPk_busitype(pk_opertype);
			head.setPk_group(pk_group);
			head.setPk_org(pk_org);
			head.setPk_org_v(pk_org_v);
			head.setOperatorid(pk_operator);
			// head.setPk_userid(pk_user);
			zhifudian = head.getDatatype(); // ֧����
			typecode = head.getOpertype(); // ҵ������
		
			// NY01��ʾ��ҵ������
			if (head.getOpertype().equals("NY01")
					|| head.getOpertype().equals("NY02")
					|| head.getOpertype().equals("NY07")
					|| head.getOpertype().equals("NY20")) {
				for (int i = 0; i < bodys.length; i++) {
					typeForShow = dataSwitch.createShowTypeName(typecode,
							bodys[i].getAccount_code(), head.getDatatype());
					if (typeForShow != "" && typeForShow != null)
						break;
				}

				head.setOpertypename(typeForShow); // vdatatype�������ݿ��е�datatype����ȷ��֧����
			} // ����
			for (YBNCBillBVO body : bodys) {

				body.setPk_billid(pk_billid); // ��������ͷ������ͬ
				body.setPk_lineid(se.generate());// ���ɱ�������
				oriSubjCode = body.getAccount_code(); // ��ȡԭʼ��ƿ�Ŀ����

				// ���ݱ������ֻ�ƿ�ĿΪ�跽�����
				// �跽
				if (!dataSwitch.isDaiFangSubj(oriSubjCode)) {
					pk_drsubj = dataSwitch.createDrSubj(pk_org, oriSubjCode); // ���ѱ��еĿ�Ŀ����ֵ
					body.setAccount_code(pk_drsubj);

					// �漰���ڽ跽��Ŀ��¼������ģ���еĴ�����Ŀ
					if (dataSwitch.isDaiFangType(head)) {
						pk_ncsubk = dataSwitch.createNcSubj(typecode, // �漰֧�����ѯ
								oriSubjCode, zhifudian, pk_org);
						body.setBys3(pk_ncsubk);
						// body.setVaccount_code(null); //2015-02-01
					}
					// �������½�ҵ���˫���Ŀ����direction�ֶ�ֵ�жϽ���� 2015-1-23
					if (dataSwitch.isYueMoType(head)) {
						// direction�ֶ�Ϊ1ʱ���ÿ�Ŀ����Ϊ���� ����ҽ�ƻ���
						if ((body.getDirect() != null && body.getDirect()
								.equals("1"))
								&& (oriSubjCode.equals("1101010101")
										|| oriSubjCode.equals("1101010201") || oriSubjCode
											.equals("11010102"))) // ���ܣ�����������ҽ�ƻ���
						{
							body.setBys3(pk_drsubj);
							body.setAccount_code(null);
							double absmoney = Math.abs(body.getMoney()
									.toDouble()); // ����ֵ
							body.setBys4(new UFDouble(absmoney));
						}
						// direction�ֶ�Ϊ2ʱ,�Ž跽�����ȡ����ֵ
						else if ((body.getDirect() != null && body.getDirect()
								.equals("2"))
								&& (oriSubjCode.equals("1101010101")
										|| oriSubjCode.equals("1101010201") || oriSubjCode
											.equals("11010102"))) {
							double absmoney = Math.abs(body.getMoney()
									.toDouble());
							body.setMoney(new UFDouble(absmoney));
						}
					}
				}
				// ���ֱ�����ָ��ҵ����Ϊ������Ŀ
				// �����ļ�������11104��Ŀ
				else {
					pk_ncsubk = dataSwitch.createNcSubj(typecode, oriSubjCode,
							zhifudian, pk_org);
					body.setBys3(pk_ncsubk);
					body.setAccount_code(null);

					body.setBys4(body.getMoney());

					// 220ҵ����Ҫ������������Ŀ���ڽ跽 ,210��һ��40101001��Ŀ�������˴�����
					if (dataSwitch.typeChange(typecode, oriSubjCode,
							body.getMoney())) {
						body.setBys6(body.getMoney());
						body.setBys4(null);
						body.setAccount_code(pk_ncsubk);
						body.setBys3(null);
					}
				}
				// �������408,508��Ŀ 2014.10.22 ����408У��
				if (oriSubjCode.substring(0, 4).equals("5008")
						|| oriSubjCode.substring(0, 4).equals("4008")) {
					// 204ҵ��408��Ŀ
					if (typecode.equals("204")) {
						if ((body.getMoney().compareTo(new UFDouble(0)) == 1)) {
							body.setBys5(body.getMoney());
						} else if ((body.getMoney().compareTo(new UFDouble(0)) == -1)) {
							body.setBys7(body.getMoney());
						}
					} else {
						body.setBys5(body.getMoney());
					}
				}
				if (head.getOpertype().equals("NY30")
						|| head.getOpertype().equals("NY22")) {
					if (oriSubjCode.equals("50010104")// 5010201
							|| oriSubjCode.equals("50010204"))// 5010202
					{
						body.setBys5(body.getMoney());
					}
				}
				// ���---ָ������Ǯ�ܺ�
				UFDouble sum = new UFDouble();

				if (body.getBys4() == null) {
					sum = sum.add(body.getMoney());
				} else {
					sum = sum.add(body.getBys4());			
					//body.setMoney(new UFDouble()); // ����
				}
				if (head.getOpertype().equals("204")) {
					// 204ҵ�����������Ǯ�ߵ������ڴ���
					if ((sum.compareTo(new UFDouble(0)) == -1)) {
						bodys[0].setMoney(sum);
					} else if ((sum.compareTo(new UFDouble(0)) == 1)) {
						bodys[0].setMoney(sum);
					}
				}
				// ��������--����D0003�ĵ�λ���ԣ�PK-1001A610000000000IWZ��
				if (body.getEnpprop() != null) {
					Integer enpcode = new Integer(body.getEnpprop());
					String pk_enpprop = dataSwitch.subjcodeSwitch(enpcode);
					body.setEnpprop(pk_enpprop);
				}
				body.setDr(0);
				body.setTs(new UFDateTime(System.currentTimeMillis()));
			}
		
			head.setDr(0);
			head.setTs(new UFDateTime(System.currentTimeMillis()));
		}

		return NCBills;
	}

	private String UpadateVoucherInform(String tableName_H,
			AggYBNCBillHVO[] bills, int success, String voucherno, String id)
			throws DAOException {
		MidTableFactory sql = new MidTableFactory(SdVoucherTask.midDBname);
		String ts = sql.rewriteRetVO1(tableName_H, bills, success, voucherno,
				id);
		return ts;
	}

	private void UpadateVoucherInform1(String tableName_H,
			AggYBNCBillHVO[] bills, int success, String voucherno, String id)
			throws DAOException {
		MidTableFactory sql = new MidTableFactory(SdVoucherTask.midDBname);
		sql.rewriteRetVO2(tableName_H, bills, success, voucherno, id);

	}

	private void readDateInform() {
		FileInputStream fin = null;
		Properties prop = new Properties();
		try {
			fin = new FileInputStream(new File(SdVoucherTask.configFilePath));
			prop.load(fin);
			setTableName_H(prop.getProperty("tableName_H"));
			setTableName_B(prop.getProperty("tableName_B"));
			setDBIp(prop.getProperty("DBip"));
			setSid(prop.getProperty("sid"));// ���ؿ����
			setMidDBname(prop.getProperty("midDBname"));
			setUsername(prop.getProperty("userName"));
			setPassword(prop.getProperty("passWord"));
			setNC_billtype(prop.getProperty("billtype"));
			DaiFangType = prop.getProperty("daifangtype").split(",");
			YueMo = prop.getProperty("yueMoType").split(",");
			DaiFangSubj = prop.getProperty("daifangsubject").split(",");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fin) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getTableName_H() {
		return tableName_H;
	}

	public void setTableName_H(String tableName_H) {
		SdVoucherTask.tableName_H = tableName_H;
	}

	public String getTableName_B() {
		return tableName_B;
	}

	public void setTableName_B(String tableName_B) {
		SdVoucherTask.tableName_B = tableName_B;
	}

	public String getDBIp() {
		return DBIp;
	}

	public void setDBIp(String ip) {
		DBIp = ip;
	}

	public String getMidDBname() {
		return midDBname;
	}

	public void setMidDBname(String midDBname) {
		SdVoucherTask.midDBname = midDBname;
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

	public void setNC_billtype(String nc_billtype) {
		NC_billtype = nc_billtype;
	}

	public static String getPk_group() {
		return pk_group;
	}

	public static void setPk_group(String pk_group) {
		SdVoucherTask.pk_group = pk_group;
	}

	public static String getPk_opertype() {
		return pk_opertype;
	}

	public static void setPk_opertype(String pk_opertype) {
		SdVoucherTask.pk_opertype = pk_opertype;
	}

	public static String getPk_accno() {
		return pk_accno;
	}

	public static void setPk_accno(String pk_accno) {
		SdVoucherTask.pk_accno = pk_accno;
	}

	public static String getPk_operator() {
		return pk_operator;
	}

	public static void setPk_operator(String pk_operator) {
		SdVoucherTask.pk_operator = pk_operator;
	}

	public static String getVoucherno() {
		return voucherno;
	}

	public static void setVoucherno(String voucherno) {
		SdVoucherTask.voucherno = voucherno;
	}

	public static String getPk_org() {
		return pk_org;
	}

	public static void setPk_org(String pk_org) {
		SdVoucherTask.pk_org = pk_org;
	}

	public static String getPk_org_v() {
		return pk_org_v;
	}

	public static void setPk_org_v(String pk_org_v) {
		SdVoucherTask.pk_org_v = pk_org_v;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		SdVoucherTask.sid = sid;
	}

	@Override
	public String exesoVoucher(HashMap<String, String> hmp) throws Exception {
		AggYBNCBillHVO[] NCbillvo = null;// 201

		this.readDateInform();// ��ȡ�����ļ�

		try {
			MidTableFactory sqlfactory = new MidTableFactory();

			// nc���в�ѯ��Ӧpk������
			login_inform = sqlfactory.getQueryInform(login_inform, hmp);
			login_inform = dataSwitch.corpSwitch(login_inform);
			// ��ѯ�м���������Ϣ
			NCbillvo = sqlfactory.agQueryYBBillVO(midDBname, tableName_H,
					tableName_B, login_inform);

			pk_group = "0001A110000000000477";
			pk_org_v = GetPk.getPkOrg_v(login_inform.get("dr_pkorg"));
			// �ӹ� ���pk����
			NCbillvo = changeToNCBill(NCbillvo, login_inform.get("dr_pkorg"),
					login_inform.get("dr_NC_billtype"),
					login_inform.get("NC_billtype"),
					login_inform.get("dr_operator"), pk_group, pk_org_v);
			// ���ͻ��ƽ̨
			// FipMsgResultVO[] fipMsgResultVOs = null;
			FipToVoucher<AggYBNCBillHVO> fipVoucher = new FipToVoucher<AggYBNCBillHVO>();
			fipVoucher.sendFipMsg(NCbillvo);
			// ��ȡƾ֤��
			String vono = "";
			if (login_inform.get("dr_busi").equals("NY01")
					|| login_inform.get("dr_busi").equals("NY02")
					|| login_inform.get("dr_busi").equals("NY07")
					|| login_inform.get("dr_busi").equals("NY20")
					|| login_inform.get("dr_busi").equals("NY22")) {

				for (AggYBNCBillHVO ncbillvo : NCbillvo) {
					YBNCBillHVO hvo = (YBNCBillHVO) ncbillvo.getParentVO();
					String datatype = hvo.getDatatype();
					String vouno = hvo.getNc_voucher();

					String voucno = "00000000";
					UpadateVoucherInform1(tableName_H, NCbillvo,
							nc.bs.hzyb.sql.UpdateInform.success, voucno, vouno);
				}
			} else {
				for (AggYBNCBillHVO billvo : NCbillvo) {
					YBNCBillHVO heads = billvo.getParentVO();
					vono = heads.getNc_voucher();
				}
				voucherno = GetPk.getPK_voucher(vono);
//				for (int i = 0; i < 1; i++) {
//					if (voucherno.equals("")) {
//						fipVoucher.sendFipMsg(NCbillvo);
//						voucherno = GetPk.getPK_voucher(vono);
//					}
//				}
				// // ��д��سɹ���Ϣ
				agUpdateVoucherInform(tableName_H, NCbillvo, voucherno,
						nc.bs.hzsb.sql.UpdateInform.success);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private void agUpdateVoucherInform(String tableName_H,
			AggYBNCBillHVO[] NCbillvo, String voucherno, int success) {
		MidTableFactory sql = new MidTableFactory(SdVoucherTask.midDBname);
		sql.agrewriteRetVO1(tableName_H, NCbillvo, success, voucherno);

	}
}