package nc.bs.hzjy.plugins;

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
		case 1302:
			pk_org = "0001A610000000000NYS";// 652 失业

			break;
		case 1301:
			pk_org = "0001A610000000000NYV";// 652 就业

			break;
		case 1303:
			pk_org = "0001A610000000000NYY";// 652 普惠

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
		case "0001A610000000000NYS":
			unitcode = "1302";
			break;
		case "0001A610000000000NYV":
			unitcode = "1301";
			break;
		case "0001A610000000000NYY":
			unitcode = "1303";
			break;
		}
		login_inform.put("dr_pk_org", unitcode);
		return login_inform;
	}

	public String subjcodeSwitch(Integer enpcode) {
		String pk_enpprop = null;
		// 自定义档案 bd_defdoc
		
		return pk_enpprop;
	}

}
