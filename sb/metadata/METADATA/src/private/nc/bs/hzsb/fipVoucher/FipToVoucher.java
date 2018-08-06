package nc.bs.hzsb.fipVoucher;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.server.ISecurityTokenCallback;
import nc.bs.hzsb.plugins.GetPk;
import nc.pubitf.fip.service.IFipMessageService;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipMsgResultVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pub.BusinessException;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.hzsb.sbvo.SBNCBillBVO;
import nc.vo.hzsb.sbvo.SBNCBillHVO;

public class FipToVoucher<E extends AbstractBill> {

	// 发送至会计平台
	public FipMsgResultVO[] sendFipMsg(E[] billVOs) throws BusinessException {

		// InvocationInfoProxy.getInstance().setUserDataSource("NC651");
		ISecurityTokenCallback sc = (ISecurityTokenCallback) NCLocator
				.getInstance().lookup(ISecurityTokenCallback.class);
		sc.token("NCSystem".getBytes(), "pfxx".getBytes());
		FipMsgResultVO[] fmr = null;
		FipMessageVO[] fiMsg = createFipMsg(FipMessageVO.MESSAGETYPE_ADD,
				billVOs);
		IFipMessageService fms = (IFipMessageService) NCLocator.getInstance()
				.lookup(IFipMessageService.class);
		return fms.sendMessages(fiMsg);
		
	}

	// 构建会计平台信息
	public FipMessageVO[] createFipMsg(int msgtype, E[] billVOs)
			throws BusinessException {
		List<FipMessageVO> fipMsgVOs = new ArrayList<FipMessageVO>();
		for (AbstractBill billVO : billVOs) {
			SBNCBillBVO[] bvos = (SBNCBillBVO[]) billVO.getChildrenVO();
			String operator = bvos[0].getOperator();
			String pk_operator = GetPk.getPkOperator(operator);

			FipMessageVO fipMsgVO = new FipMessageVO();
			fipMsgVO.setBillVO(billVO);
			fipMsgVO.setMessagetype(msgtype);
			FipRelationInfoVO infoVO = createFipRelationInfoVO(billVO);

			if (msgtype == FipMessageVO.MESSAGETYPE_ADD) {
				if (pk_operator != null) {
					infoVO.setPk_operator(pk_operator);
				} else {
					infoVO.setPk_operator(InvocationInfoProxy.getInstance()
							.getUserId());
				}
			} else if (msgtype == FipMessageVO.MESSAGETYPE_DEL) {
				infoVO.setPk_operator(InvocationInfoProxy.getInstance()
						.getUserId());
			}
			fipMsgVO.setMessageinfo(infoVO);
			fipMsgVOs.add(fipMsgVO);
		}
		return fipMsgVOs.toArray(new FipMessageVO[0]);

	}

	// 构建社保VO的组织实时凭证信息
	public FipRelationInfoVO createFipRelationInfoVO(AbstractBill billVO) {
		SBNCBillHVO headVO = (SBNCBillHVO) billVO.getParent();
		// SuperVO[] bodysVO = (SuperVO[]) billVO.getChildrenVO();

		FipRelationInfoVO infoVO = new FipRelationInfoVO();

		// 组织集团
		infoVO.setPk_group(headVO.getPk_group());
		// 公司代码
		infoVO.setPk_org(headVO.getPk_org());

		// 系统编号
		infoVO.setPk_system("AR");
		// 业务类型
		infoVO.setPk_billtype(headVO.getPk_billtype());
		//infoVO.setDefdoc1("");//來源业务
		//infoVO.setFreedef1("");//来源单据号
		//infoVO.setFreedef2("");//来源备注
		infoVO.setFreedef3(headVO.getVbys3());//来源金额

		/*
		 * SequenceGenerator se = new SequenceGenerator(); // 凭证关联号--与单据关联的值(合并)
		 * String pk_billid = se.generate();
		 */
		infoVO.setRelationID(headVO.getPrimaryKey());// NC主键 唯一标识
		return infoVO;
	}
}
