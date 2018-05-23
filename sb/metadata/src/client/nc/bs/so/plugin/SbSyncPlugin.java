package nc.bs.so.plugin;

import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.itf.agSync.SbAgVoucherSyncItf;
import nc.vo.pub.BusinessException;

public class SbSyncPlugin implements IBackgroundWorkPlugin {

	@SuppressWarnings("deprecation")
	@Override
	public PreAlertObject executeTask(BgWorkingContext arg0)
			throws BusinessException {
		HashMap<String, String> hmp = new HashMap<String, String>(); // 存储当前登录时的一些信息
		String startTime = "";
		String endTime = "";
		String operator = "";
		String busitype = "";
		String accountBook = "";
		String group = nc.vo.pubapp.AppContext.getInstance().getPkGroup(); // 集团

		HashMap<String, Object> km = arg0.getKeyMap();
		hmp.put("group", group);
		if (km == null || km.get("startTime") == null
				|| km.get("startTime") == "") {// 阀值没填写类别，任务不执行
			startTime = "";
		} else {
			startTime = km.get("startTime").toString();
			hmp.put("startTime", startTime);
		}
		if (km == null || km.get("endTime") == null || km.get("endTime") == "") {// 阀值没填写类别，任务不执行
			endTime = "";
		} else {
			endTime = km.get("endTime").toString();
			hmp.put("endTime", endTime);
		}
		if (km == null || km.get("operator") == null
				|| km.get("operator") == "") {// 阀值没填写类别，任务不执行
			operator = "";
		} else {
			operator = km.get("operator").toString();

			hmp.put("operator", operator);
		}
		if (km == null || km.get("busitype") == null
				|| km.get("busitype") == "") {// 阀值没填写类别，任务不执行
			busitype = "";
		} else {
			busitype = km.get("busitype").toString();
			hmp.put("NC_billtype", busitype);
		}
		if (km == null || km.get("accountBook") == null
				|| km.get("accountBook") == "") {// 阀值没填写类别，任务不执行
			accountBook = "";
		} else {
			accountBook = km.get("accountBook").toString();
			hmp.put("accountBook", accountBook);
		}

		try {
			NCLocator.getInstance().lookup(SbAgVoucherSyncItf.class)
					.exesoVoucher(hmp);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return null;
	}

}
