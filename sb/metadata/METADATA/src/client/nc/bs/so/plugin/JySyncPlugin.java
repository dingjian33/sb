package nc.bs.so.plugin;

import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.itf.agSync.JyAgVoucherSyncItf;
import nc.vo.pub.BusinessException;

public class JySyncPlugin implements IBackgroundWorkPlugin {

	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)
			throws BusinessException {
		HashMap<String, String> hmp = new HashMap<String, String>(); // �洢��ǰ��¼ʱ��һЩ��Ϣ
		String startTime = "";
		String endTime = "";
		String operator = "";
		String busitype = "";
		String accountBook = "";
		String group = nc.vo.pubapp.AppContext.getInstance().getPkGroup(); // ����
		String pk_org = arg0.getPk_org(); // ��֯
		HashMap<String, Object> km = arg0.getKeyMap();
		hmp.put("pk_org", pk_org);
		hmp.put("group", group);

		if (km == null || km.get("startTime") == null
				|| km.get("startTime") == "") {// ��ֵû��д�������ִ��
			startTime = "";
		} else {
			startTime = km.get("startTime").toString();
			hmp.put("startTime", startTime);
		}
		if (km == null || km.get("endTime") == null || km.get("endTime") == "") {// ��ֵû��д�������ִ��
			endTime = "";
		} else {
			endTime = km.get("endTime").toString();
			hmp.put("endTime", endTime);
		}
		if (km == null || km.get("operator") == null
				|| km.get("operator") == "") {// ��ֵû��д�������ִ��
			operator = "";
		} else {
			operator = km.get("operator").toString();
			hmp.put("operator", operator);
		}
		if (km == null || km.get("busitype") == null
				|| km.get("busitype") == "") {// ��ֵû��д�������ִ��
			busitype = "";
		} else {
			busitype = km.get("busitype").toString();
			hmp.put("busitype", busitype);
		}
		if (km == null || km.get("accountBook") == null
				|| km.get("accountBook") == "") {// ��ֵû��д�������ִ��
			accountBook = "";
		} else {
			accountBook = km.get("accountBook").toString();
			hmp.put("accountBook", accountBook);
		}
		try {
			NCLocator.getInstance().lookup(JyAgVoucherSyncItf.class)
					.exesoVoucher(hmp);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

}
