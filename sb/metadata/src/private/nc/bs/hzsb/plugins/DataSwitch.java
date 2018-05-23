package nc.bs.hzsb.plugins;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class DataSwitch {

	public HashMap<String, String> getLoginInform(HashMap<String, String> hmp,
			LinkedHashMap<String, Object> keyMap) {
		Iterator<String> iterator = keyMap.keySet().iterator();
		while (iterator.hasNext()) {
			String paramName = iterator.next();
			String paramVaule = (String) keyMap.get(paramName);
			if (paramName.equals("busitype")) {
				hmp.put("pk_busitype", paramVaule);
			}
			if (paramName.equals("operator")) {
				hmp.put("pk_operator", paramVaule);
			}
			if (paramName.equals("starTime")) {
				hmp.put("starttime", paramVaule);
			}
			if (paramName.equals("endTime")) {
				hmp.put("endtime", paramVaule);
			}
			if (paramName.equals("glorgbook")) {
				hmp.put("pk_glorgbook", paramVaule);
			}
		}
		return hmp;
	}

	public String corpSwitch(String accno) {
		Integer codetemp = new Integer(accno);
		String pk_org = null;
		switch (codetemp) {
		// 帐套编号 TO 查询公司编码 pk_org
		case 601:
			pk_org = "0001A610000000000NXG";// 652 企业养老

			break;
		case 602:
			pk_org = "0001A610000000000NXC";// 652 机关事业

			break;
		case 606:
			pk_org = "0001A610000000000NXS";// 652 城乡居民

			break;
		case 609:
			pk_org = "0001A610000000000NX3";// 652 离休

			break;
		case 610:
			pk_org = "0001A610000000000NX6";// 652 社会化管理

			break;
		case 611:
			pk_org = "0001A610000000000NXM";// 652 工伤保险

			break;
		case 612:
			pk_org = "0001A610000000000NXP";// 652  生育保险基金

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
		case "0001A610000000000NXG":
			unitcode = "601";
			break;
		case "0001A610000000000NXC":
			unitcode = "602";
			break;
		case "0001A610000000000NXS":
			unitcode = "606";
			break;
		case "0001A610000000000NX3":
			unitcode = "609";
			break;
		case "0001A610000000000NX6":
			unitcode = "610";
			break;
		case "0001A610000000000NXM":
			unitcode = "611";
			break;
		case "0001A610000000000NXP":
			unitcode = "612";
			break;
		}
		login_inform.put("dr_pk_org", unitcode);
		return login_inform;
	}

	public String subjcodeSwitch(Integer enpcode) {
		String pk_enpprop = null;
		// 自定义档案 bd_defdoc
		switch (enpcode) {
		case 101: // 机关事业
			pk_enpprop = new String("1001A610000000000IWY");
			break;
		case 201: // 企业
			pk_enpprop = new String("1001A610000000000IWZ");//离休
			break;
		case 301: // 其他
			pk_enpprop = new String("1001A6100000000028KG");
			break;
		// 上面 测试库与正式库数据一样
		case 401: // 城镇
			pk_enpprop = new String("1001A610000000000U4V");// 1001A61000000000HUYD
			break;
		case 501: // 农村
			pk_enpprop = new String("1001A610000000000U4W");// 1001A61000000000HUYE
			break;
		case 601: // 市财政经费
			pk_enpprop = new String("1001A610000000000JQK"); 
			break;
		case 701: // 补充生活补贴单位缴
			pk_enpprop = new String("1001A610000000000JQM");
			break;
		case 801: // 职教幼教生活补贴
			pk_enpprop = new String("1001A610000000000JQN");
			break;
		case 901: // 统筹外项目--不是单位属性
			pk_enpprop = new String("1001A610000000000JQJ"); 
			break;
		case 902: // 萧山
			pk_enpprop = new String("1001A610000000000JQO"); 
			break;
		case 904: // 富阳
			pk_enpprop = new String("1001A610000000000JQQ"); 
			break;
		case 903: // 余杭 
			pk_enpprop = new String("1001A610000000000JQP"); 
			break;
		case 905: // 临安
			pk_enpprop = new String("1001A610000000000JQR"); 
			break;
		default:
			break;
		}
		return pk_enpprop;
	}

	public String anotherSubjSwitch(String vopertype) {
		String pk_resever2 = null;
		Integer type_code = new Integer(vopertype);
		switch (type_code) {
		case 105:
			pk_resever2 = "1005AA1000000000000F";
			break;
		// case 106: pk_resever2 = ""; break;
		// case 107: pk_resever2 = ""; break;
		// case 108: pk_resever2 = ""; break;
		// case 109: pk_resever2 = ""; break;
		case 110:
			pk_resever2 = "1005AA1000000000000N";
			break;
		// default: break;
		}
		return pk_resever2;
	}

	public String toPkSubjFor121(String feeitem_temp, String pk_glorgbook) {
		Integer subjcode_temp = new Integer(feeitem_temp);
		String pk_subj = null;
		if (pk_glorgbook.equals("0001AA10000000017D1J")) { // 企业养老
			switch (subjcode_temp) {
			case 50101:
				pk_subj = new String("1005AA1000000000002C");
				break;
			case 50102:
				pk_subj = new String("1005AA1000000000002G");
				break;
			case 50103:
				pk_subj = new String("1005AA1000000000002I");
				break;
			case 50105:
				pk_subj = new String("1005AA1000000000002K");
				break;
			case 50106:
				pk_subj = new String("1005AA1000000000002M");
				break;
			case 50107:
				pk_subj = new String("1005AA1000000000002O");
				break;
			case 50108:
				pk_subj = new String("1005AA1000000000002Q");
				break;
			case 503:
				pk_subj = new String("1005AA1000000000002U");
				break;
			}
			return pk_subj;
		} else if (pk_glorgbook.equals("-")) {// 离休两费

		}
		return pk_subj;
	}
}
