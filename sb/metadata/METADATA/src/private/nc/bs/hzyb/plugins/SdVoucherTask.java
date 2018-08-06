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
 * 从中间表取数发送到会计平台生成凭证的后台任务插件
 * 
 * 在NC中注册的中间表单据类型为Y202 注册的NC单据类型为Y201
 * 
 * @author liutya 2012-12-20
 * 
 */
public class SdVoucherTask implements YbAgVoucherSyncItf {

	private static DataSwitch dataSwitch = new DataSwitch();
	public static String configFilePath = "modules/arap/META-INF/arap_hzyb.properties";
	private static HashMap<String, String> login_inform = new HashMap<String, String>(); // 存储当前登录时的一些信息
	private static String tableName_H = null;
	private static String tableName_B = null;
	private static String DBIp = null;
	private static String sid = null;
	private static String midDBname = null; // 外部modules/arap/META-INF/arap_hzyb.properties中读取
	private static String username = null;
	private static String password = null;
	private static String NC_billtype = null;// Y201 升级之后重新获取

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
	 * 发送凭证任务函数入口
	 * 
	 * @throws Exception
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String executeTask(String id) throws Exception {
		AggYBNCBillHVO[] NCBills = null;

		// 读数据源信息
		this.readDateInform();

		try {
			MidTableFactory sqlfactory = new MidTableFactory(midDBname);

			// 查詢中間表数据，包括标志位回写 取数
			Log.getInstance().info("查询中间表的所有信息");
			NCBills = sqlfactory.queryYBBillVO(midDBname, tableName_H,
					tableName_B, id);

			// 从取到的vo中获取相关信息
			for (AggYBNCBillHVO ncbillvos : NCBills) {
				YBNCBillHVO hvo = (YBNCBillHVO) ncbillvos.getParentVO();
				YBNCBillBVO[] bvos = (YBNCBillBVO[]) ncbillvos.getChildrenVO();
				YBNCBillBVO bvo = bvos[0];
				String operator = hvo.getOperperson();
				pk_operator = GetPk.getPkOperator(operator);
				// 用查询出来的数据去数据库查找对应的pk字段（主键）
				pk_accno = bvo.getAccno();
				opertype = hvo.getOpertype();
				NC_billtype = "Y201-Cxx-" + opertype;
				pk_opertype = GetPk.getPkOpertype(NC_billtype);

			}

			pk_group = "0001A6100000000002TD";// 人社局
			pk_org = dataSwitch.corpSwitch(pk_accno);
			pk_org_v = GetPk.getPkOrg_v(pk_org);
			// 加工(添加各字段主键值)
			NCBills = changeToNCBill(NCBills, pk_org, NC_billtype, pk_opertype,
					pk_operator, pk_group, pk_org_v);
			// 发送会计平台

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
						.toString();// 回写系统时间
				json = " { \"successs\" : true, \"flag\" :  " + flag
						+ " ,\"errorMessage\":null ,\"posting_date\" :" + ts
						+ " ,\"voucherno\" : 临时单据，不回写凭证号 ！ }";
			} else {
				voucherno = GetPk.getPK_voucher(id);
				if (voucherno.equals("")) {
					for (int i = 0; i < 1; i++) {
						Log.getInstance().info("第一次没有生成凭证号，再一次发送至会计平台");
						voucher.sendFipMsg(NCBills);
					}
					voucherno = GetPk.getPK_voucher(id);
				}
				Log.getInstance().info("=======================");
				Log.getInstance().info("凭证号：" + voucherno);
				Log.getInstance().info("凭证号：" + voucherno + "传入id： " + id);
				if (voucherno != null && !voucherno.equals("")) {
					// 回写相关成功信息,实时凭证的主键号，成功标志

					String ts = UpadateVoucherInform(tableName_H, NCBills,
							nc.bs.hzyb.sql.UpdateInform.success, voucherno, id);
					json = " { \"successs\" : true, \"flag\" :  " + flag
							+ " ,\"errorMessage\":null ,\"posting_date\" :"
							+ ts + " ,\"voucherno\" :" + voucherno + " }";
				} else {
					json = " { \"successs\" : false, \"flag\" : "
							+ flag
							+ "  ,\"errorMessage\":\"凭证未生成，凭证号为空，发送至会计平台出现错误\" ,\"posting_date\" :null ,\"voucherno\" :null }";
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
			YBNCBillHVO head = (YBNCBillHVO) NCBill.getParentVO();// 提取表头
			YBNCBillBVO[] bodys = (YBNCBillBVO[]) NCBill.getChildrenVO();// 提取表体
			String pk_billid = se.generate(); // 生成NC主键
			head.setPrimaryKey(pk_billid);
			head.setPk_billid(pk_billid);
			head.setPk_billtype(NC_billtype);
			head.setPk_busitype(pk_opertype);
			head.setPk_group(pk_group);
			head.setPk_org(pk_org);
			head.setPk_org_v(pk_org_v);
			head.setOperatorid(pk_operator);
			// head.setPk_userid(pk_user);
			zhifudian = head.getDatatype(); // 支付点
			typecode = head.getOpertype(); // 业务类型
		
			// NY01显示的业务名称
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

				head.setOpertypename(typeForShow); // vdatatype根据数据库中的datatype数字确定支付点
			} // 设置
			for (YBNCBillBVO body : bodys) {

				body.setPk_billid(pk_billid); // 外键，与表头主键相同
				body.setPk_lineid(se.generate());// 生成表体主键
				oriSubjCode = body.getAccount_code(); // 获取原始会计科目编码

				// 根据编码区分会计科目为借方或贷方
				// 借方
				if (!dataSwitch.isDaiFangSubj(oriSubjCode)) {
					pk_drsubj = dataSwitch.createDrSubj(pk_org, oriSubjCode); // 用友表中的科目主键值
					body.setAccount_code(pk_drsubj);

					// 涉及到在借方科目分录中设置模板中的贷方科目
					if (dataSwitch.isDaiFangType(head)) {
						pk_ncsubk = dataSwitch.createNcSubj(typecode, // 涉及支付点查询
								oriSubjCode, zhifudian, pk_org);
						body.setBys3(pk_ncsubk);
						// body.setVaccount_code(null); //2015-02-01
					}
					// 各帐套月结业务的双向科目根据direction字段值判断借贷方 2015-1-23
					if (dataSwitch.isYueMoType(head)) {
						// direction字段为1时，该科目设置为贷方 两定医疗机构
						if ((body.getDirect() != null && body.getDirect()
								.equals("1"))
								&& (oriSubjCode.equals("1101010101")
										|| oriSubjCode.equals("1101010201") || oriSubjCode
											.equals("11010102"))) // 汇总，两定，两定医疗机构
						{
							body.setBys3(pk_drsubj);
							body.setAccount_code(null);
							double absmoney = Math.abs(body.getMoney()
									.toDouble()); // 绝对值
							body.setBys4(new UFDouble(absmoney));
						}
						// direction字段为2时,放借方，金额取绝对值
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
				// 部分编码在指定业务下为贷方科目
				// 属性文件中新增11104科目
				else {
					pk_ncsubk = dataSwitch.createNcSubj(typecode, oriSubjCode,
							zhifudian, pk_org);
					body.setBys3(pk_ncsubk);
					body.setAccount_code(null);

					body.setBys4(body.getMoney());

					// 220业务需要将贷方几个科目放在借方 ,210有一个40101001科目（放在了贷方）
					if (dataSwitch.typeChange(typecode, oriSubjCode,
							body.getMoney())) {
						body.setBys6(body.getMoney());
						body.setBys4(null);
						body.setAccount_code(pk_ncsubk);
						body.setBys3(null);
					}
				}
				// 如果存在408,508科目 2014.10.22 增加408校验
				if (oriSubjCode.substring(0, 4).equals("5008")
						|| oriSubjCode.substring(0, 4).equals("4008")) {
					// 204业务408科目
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
				// 金额---指单据中钱总和
				UFDouble sum = new UFDouble();

				if (body.getBys4() == null) {
					sum = sum.add(body.getMoney());
				} else {
					sum = sum.add(body.getBys4());			
					//body.setMoney(new UFDouble()); // 清零
				}
				if (head.getOpertype().equals("204")) {
					// 204业务产生负数的钱走的银行在贷方
					if ((sum.compareTo(new UFDouble(0)) == -1)) {
						bodys[0].setMoney(sum);
					} else if ((sum.compareTo(new UFDouble(0)) == 1)) {
						bodys[0].setMoney(sum);
					}
				}
				// 辅助核算--基于D0003的单位属性（PK-1001A610000000000IWZ）
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
			setSid(prop.getProperty("sid"));// 本地库测试
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

		this.readDateInform();// 读取配置文件

		try {
			MidTableFactory sqlfactory = new MidTableFactory();

			// nc表中查询对应pk与数据
			login_inform = sqlfactory.getQueryInform(login_inform, hmp);
			login_inform = dataSwitch.corpSwitch(login_inform);
			// 查询中间表的所有信息
			NCbillvo = sqlfactory.agQueryYBBillVO(midDBname, tableName_H,
					tableName_B, login_inform);

			pk_group = "0001A110000000000477";
			pk_org_v = GetPk.getPkOrg_v(login_inform.get("dr_pkorg"));
			// 加工 添加pk数据
			NCbillvo = changeToNCBill(NCbillvo, login_inform.get("dr_pkorg"),
					login_inform.get("dr_NC_billtype"),
					login_inform.get("NC_billtype"),
					login_inform.get("dr_operator"), pk_group, pk_org_v);
			// 发送会计平台
			// FipMsgResultVO[] fipMsgResultVOs = null;
			FipToVoucher<AggYBNCBillHVO> fipVoucher = new FipToVoucher<AggYBNCBillHVO>();
			fipVoucher.sendFipMsg(NCbillvo);
			// 获取凭证号
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
				// // 回写相关成功信息
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