package nc.bs.hzyb.plugins;

import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.hzyb.pub.Log;
import nc.vo.hzyb.ybvo.YBNCBillHVO;
import nc.vo.pub.lang.UFDouble;

public class DataSwitch {

	public String subjcodeSwitch(Integer enpcode) {
		String pk_enpprop = null;
		switch (enpcode) {
		case 101: // 机关事业
			pk_enpprop = new String("1001A6100000000028KE");// 0001AA100000000004WX
			break;
		case 102: // 企业
			pk_enpprop = new String("1001A6100000000028KF");// 0001AA100000000004WY
			break;
		case 103: // 其他
			pk_enpprop = new String("1001A6100000000028KG");// 0001AA100000000004WZ
			break;
		// 上面 测试库与正式库数据一样
		case 401: // 城镇
			pk_enpprop = new String("1016F11000000000016X");
			break;
		case 501: // 农村
			pk_enpprop = new String("1016F11000000000016Z");
			break;
		case 601: // 市财政经费
			pk_enpprop = new String("1001A610000000000JQK"); // 1001A1100000000006SQ
			break;
		case 701: // 补充生活补贴单位缴
			pk_enpprop = new String("1001A610000000000JQM");// 1002F11000000000GL7N
			break;
		case 801: // 职教幼教生活补贴
			pk_enpprop = new String("1001A610000000000JQN");// 1002F11000000000GQJG
			break;
		case 901: // 统筹外项目--不是单位属性
			pk_enpprop = new String("1001A610000000000JQJ");// 0001AA100000000019CN
			break;
		default:
			break;
		}
		return pk_enpprop;
	}

	public String corpSwitch(String accno) {
		// 字符型转为整形
		String pk_org = null;
		switch (accno) {
		// 公司主键 TO 查询帐套的编号
		case "001":// 1001
			pk_org = "0001A1100000000031FZ";
			break;
		case "003":// 1002
			pk_org = "0001A1100000000031G5";
			break;
		case "002":// 1003
			pk_org = "0001A1100000000031G2";
			break;
		case "009":// 1004
			pk_org = "0001A1100000000031GD";
			break;
		case "011":// 1007
			pk_org = "0001A1100000000031GJ";
			break;
		case "00101":// 1009
			pk_org = "0001A1100000000031GD";
			break;
		case "020":// 1010
			pk_org = "0001A1100000000031GN";
			break;
		case "030":// 1011
			pk_org = "0001A610000000000NYA";// 652
			break;
		case "040":// 1012
			pk_org = "0001A610000000000NYP";// 652
			break;
		case "021":// 1013
			pk_org = "0001A610000000000NY7";// 652
			break;
		case "005":// 1014
			pk_org = "0001A610000000000NY1";// 652
			break;
		case "050":// 1015
			pk_org = "0001A610000000000NYG";// 652
			break;
		case "060":// 1016
			pk_org = "0001A610000000000NYJ";// 652
			break;
		case "010":// 1017
			pk_org = "0001A610000000000NY4";// 652
			break;
		case "031":// 1018
			pk_org = "0001A610000000000NYM";// 652
			break;
		case "035":// 1020
			pk_org = "0001A610000000000NYD";// 652
			break;
		}
		return pk_org;
	}

	public HashMap<String, String> corpSwitch(
			HashMap<String, String> login_inform) {
		String unitcode = null;
		String pk_org = login_inform.get("dr_pkorg");

		switch (pk_org) {
		// 公司主键 TO 查询帐套的编号
		case "0001A1100000000031FZ":
			unitcode = "001";
			break;
		case "0001A1100000000031G5":
			unitcode = "003";
			break;
		case "0001A1100000000031G2":
			unitcode = "002";
			break;
		case "0001A1100000000031GD":
			unitcode = "009";
			break;
		case "0001A1100000000031GJ":
			unitcode = "011";
			break;
		case "0001A1100000000031GN":
			unitcode = "020";
			break;
		case "0001A610000000000NYA":// 652
			unitcode = "030";
			break;
		case "0001A610000000000NYP":// 652
			unitcode = "040";
			break;
		case "0001A610000000000NY7":// 652
			unitcode = "021";
			break;
		case "0001A610000000000NY1":// 652
			unitcode = "005";
			break;
		case "0001A610000000000NYG":// 652
			unitcode = "050";
			break;
		case "0001A610000000000NYJ":// 652
			unitcode = "060";
			break;
		case "0001A610000000000NY4":// 652
			unitcode = "010";
			break;
		case "0001A610000000000NYM":// 652
			unitcode = "031";
			break;
		case "0001A610000000000NYD":// 652
			unitcode = "035";
			break;
		}
		login_inform.put("dr_pk_org", unitcode);
		return login_inform;
	}

	// 到用友库根据主体帐簿和科目编码查出科目主键
	public String createDrSubj(String pk_org, String account_code) {
		String pk_drsubj = null;
		BaseDAO basedao = new BaseDAO();
		String feesubjsql = "select a.pk_accasoa from  bd_accasoa a inner join bd_accchart c on a.pk_accchart=c.pk_accchart inner join bd_account b on a.pk_account = b.pk_account where c.pk_org = '"
				+ pk_org + "' and b.code  = '" + account_code + "'";
		try {
			pk_drsubj = (String) basedao.executeQuery(feesubjsql,
					new ColumnProcessor());
			if (pk_drsubj.equals("") || pk_drsubj == null) {
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return pk_drsubj;
	}

	public String createNcSubj(String vopertype, String oriSubjCode,
			String zhifudian, String pk_org) {
		// String AChead5 = oriSubjCode.substring(0,5);
		// String AChead3 = oriSubjCode.substring(0,3);

		if (vopertype.equals("NY01") || vopertype.equals("NY07")) {
			// 非现金结转，月结
			if (zhifudian.equals("1")) {
				return createDrSubj(pk_org, "11011801");// 1110501
			} // 医保大厅
			if (zhifudian.equals("2")) {
				return createDrSubj(pk_org, "11011803");// 1110503
			} // 市民之家
			if (zhifudian.equals("3")) {
				return createDrSubj(pk_org, "11011805");// 1110505
			}// 上海
			if (zhifudian.equals("4")) {
				return createDrSubj(pk_org, "11011802");
			} // 宁波
			if (zhifudian.equals("5")) {
				return createDrSubj(pk_org, "11011806");// 1110506
			} // 异地大厅,城南
			if (zhifudian.equals("6")) {
				return createDrSubj(pk_org, "11011807");// 1110507
			} // 城北大厅
			if (zhifudian.equals("7")) {
				return createDrSubj(pk_org, "11011808");// 1110508
			} // 西湖区
			if (zhifudian.equals("8")) {
				return createDrSubj(pk_org, "11011809");// 1110509
			} // 上城区
			if (zhifudian.equals("9")) {
				return createDrSubj(pk_org, "11011810");// 1110510
			} // 下城区
			if (zhifudian.equals("10")) {
				return createDrSubj(pk_org, "11011811");// 1110511
			} // 江干区
			if (zhifudian.equals("11")) {
				return createDrSubj(pk_org, "11011812");// 1110512
			} // 拱墅区
			if (zhifudian.equals("12")) {
				return createDrSubj(pk_org, "11011813");// 1110513
			} // 滨江区
			if (zhifudian.equals("13")) {
				return createDrSubj(pk_org, "11011814");// 1110514
			} // 下沙经开区
			if (zhifudian.equals("14")) {
				return createDrSubj(pk_org, "11011804");// 1110504
			} // 大江东
			if (zhifudian.equals("15")) {
				return createDrSubj(pk_org, "11011815");// 1110504
			} // 余杭
			if (zhifudian.equals("16")) {
				return createDrSubj(pk_org, "11011816");
			} // 富阳
			if (zhifudian.equals("17")) {
				return createDrSubj(pk_org, "11011817");
			} // 萧山
		} else if (vopertype.equals("NY02")) { // 现金月结
			if (zhifudian.equals("1")) { // 医保大厅用现金
				return createDrSubj(pk_org, "100101");// 1010101
			} else { // 其他支付点用银行
				return createDrSubj(pk_org, "10040201");// 1030201
			}
		}
		return createDrSubj(pk_org, oriSubjCode);
	}

	public String createShowTypeName(String typecode, String oriSubjCode,
			String zhifudian) {

		String code5 = "";
		if (oriSubjCode.length() >= 6) {
			code5 = oriSubjCode.substring(0, 6);
		}
		String code3 = "";
		if (oriSubjCode.length() >= 4) {
			code3 = oriSubjCode.substring(0, 4);
		}
		if (typecode.equals("NY01") || typecode.equals("NY20")
				|| typecode.equals("NY07")) {
			if (code5.equals("110101") || code3.equals("5001")
					|| code3.equals("5002") || code3.equals("5008")
					|| code3.equals("5005") || code5.equals("110118")
					|| code3.equals("5401")) {
				// 非现金结转，月结
				if (zhifudian.equals("1")) {
					return new String("医保大厅|月结");
				} // 医保大厅
				if (zhifudian.equals("2")) {
					return new String("市民之家|月结");
				} // 市民之家
				if (zhifudian.equals("3")) {
					return new String("上海|月结");
				} // 上海
				if (zhifudian.equals("4")) {
					return new String("宁波|月结");
				} // 宁波
				if (zhifudian.equals("5")) {
					return new String("异地大厅|月结");
				} // 异地大厅,城南
				if (zhifudian.equals("6")) {
					return new String("城北大厅|月结");
				} // 城北大厅
				if (zhifudian.equals("7")) {
					return new String("西湖区|月结");
				} // 西湖区
				if (zhifudian.equals("8")) {
					return new String("上城区|月结");
				} // 上城区
				if (zhifudian.equals("9")) {
					return new String("下城区|月结");
				} // 下城区
				if (zhifudian.equals("10")) {
					return new String("江干区|月结");
				} // 江干区
				if (zhifudian.equals("11")) {
					return new String("拱墅区|月结");
				} // 拱墅区
				if (zhifudian.equals("12")) {
					return new String("滨江区|月结");
				} // 滨江区
				if (zhifudian.equals("13")) {
					return new String("下沙经开区|月结");
				} // 下沙经开区
				if (zhifudian.equals("14")) {
					return new String("大江东区|月结");
				} // 大江东区
				if (zhifudian.equals("15")) {
					return new String("余杭|月结");
				} // 余杭区
				if (zhifudian.equals("16")) {
					return new String("富阳区|月结");
				} // 富阳
				if (zhifudian.equals("17")) {
					return new String("萧山区|月结");
				} // 萧山
			}
		}
		// 现金月结
		else if (typecode.equals("NY02")) {
			if (zhifudian.equals("1")) {
				return new String("现金月结|医保大厅");
			} else {
				return new String("现金月结|各城区");
			}
		}
		// 非现金每日挂账款
		/*
		 * else if(typecode.equals("NY07"){
		 * 
		 * }
		 */
		return null;
	}

	public boolean isDaiFangSubj(String oriSubjCode) {
		for (String inputSubj : SdVoucherTask.DaiFangSubj) {
			if (oriSubjCode.equals(inputSubj)) {
				return true;
			}
		}
		return false;
	}

	public boolean isDaiFangType(YBNCBillHVO head) {
		String tempType = head.getOpertype();
		for (String type : SdVoucherTask.DaiFangType) {
			if (tempType.equals(type))
				return true;
		}
		return false;
	}

	public boolean isYueMoType(YBNCBillHVO head) {
		String tempYueMoType = head.getOpertype();
		for (String type : SdVoucherTask.YueMo) {
			if (tempYueMoType.equals(type))
				return true;
		}
		return false;
	}

	public boolean isDaiFangOpertype2(YBNCBillHVO head) {
		if (head.getOpertype().equals("NY01")) {
			return true;
		} else if (head.getOpertype().equals("NY02")) {
			return true;
		} else if (head.getOpertype().equals("NY07")) {
			return true;
		} else if (head.getOpertype().equals("NY20")) {
			return true;
		}

		return false;
	}

	public boolean isBankNeedDivided(String opertype) {
		if (opertype.equals("NY04")) {
			return true;
		}
		if (opertype.equals("NY09")) {
			return true;
		}
		if (opertype.equals("NY06")) {
			return true;
		}
		if (opertype.equals("NY21")) {
			return true;
		}
		if (opertype.equals("NY30")) {
			return true;
		}
		if (opertype.equals("NY11")) {
			return true;
		}
		if (opertype.equals("NY12")) {
			return true;
		}
		if (("212".equals(opertype))) {
			return true;
		}
		return false;
	}

	public boolean typeChange(String typecode, String oriSubjCode,
			UFDouble oriMoney) {
		if (typecode.equals("220") || typecode.equals("210")) {
			if (oriSubjCode.equals("40010101")
					|| oriSubjCode.equals("40010102") // 4020107,4020108
					|| oriSubjCode.equals("400101"))// 40101001
			{
				return true;
			}
		}
		return false;
	}

}
