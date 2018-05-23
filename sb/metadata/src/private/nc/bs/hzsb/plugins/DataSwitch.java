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
		// ���ױ�� TO ��ѯ��˾���� pk_org
		case 601:
			pk_org = "0001A610000000000NXG";// 652 ��ҵ����

			break;
		case 602:
			pk_org = "0001A610000000000NXC";// 652 ������ҵ

			break;
		case 606:
			pk_org = "0001A610000000000NXS";// 652 �������

			break;
		case 609:
			pk_org = "0001A610000000000NX3";// 652 ����

			break;
		case 610:
			pk_org = "0001A610000000000NX6";// 652 ��ữ����

			break;
		case 611:
			pk_org = "0001A610000000000NXM";// 652 ���˱���

			break;
		case 612:
			pk_org = "0001A610000000000NXP";// 652  �������ջ���

			break;
		}

		return pk_org;
	}

	public HashMap<String, String> corpSwitch(
			HashMap<String, String> login_inform) {
		String unitcode = null;
		String pk_org = login_inform.get("dr_pkorg");

		switch (pk_org) {
		// ��˾���� TO ��ѯ���׵ı��
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
		// �Զ��嵵�� bd_defdoc
		switch (enpcode) {
		case 101: // ������ҵ
			pk_enpprop = new String("1001A610000000000IWY");
			break;
		case 201: // ��ҵ
			pk_enpprop = new String("1001A610000000000IWZ");//����
			break;
		case 301: // ����
			pk_enpprop = new String("1001A6100000000028KG");
			break;
		// ���� ���Կ�����ʽ������һ��
		case 401: // ����
			pk_enpprop = new String("1001A610000000000U4V");// 1001A61000000000HUYD
			break;
		case 501: // ũ��
			pk_enpprop = new String("1001A610000000000U4W");// 1001A61000000000HUYE
			break;
		case 601: // �в�������
			pk_enpprop = new String("1001A610000000000JQK"); 
			break;
		case 701: // �����������λ��
			pk_enpprop = new String("1001A610000000000JQM");
			break;
		case 801: // ְ���׽������
			pk_enpprop = new String("1001A610000000000JQN");
			break;
		case 901: // ͳ������Ŀ--���ǵ�λ����
			pk_enpprop = new String("1001A610000000000JQJ"); 
			break;
		case 902: // ��ɽ
			pk_enpprop = new String("1001A610000000000JQO"); 
			break;
		case 904: // ����
			pk_enpprop = new String("1001A610000000000JQQ"); 
			break;
		case 903: // �ຼ 
			pk_enpprop = new String("1001A610000000000JQP"); 
			break;
		case 905: // �ٰ�
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
		if (pk_glorgbook.equals("0001AA10000000017D1J")) { // ��ҵ����
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
		} else if (pk_glorgbook.equals("-")) {// ��������

		}
		return pk_subj;
	}
}
