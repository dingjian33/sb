package nc.bs.hzyb.fipVoucher;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.server.ISecurityTokenCallback;
import nc.bs.hzyb.plugins.GetPk;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.pubitf.fip.service.IFipMessageService;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipMsgResultVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.hzyb.ybvo.YBNCBillBVO;
import nc.vo.hzyb.ybvo.YBNCBillHVO;

public class FipToVoucher<E extends AbstractBill> {

	// 构建FipRelationInfoVO对象
	public FipMsgResultVO[] buildFipRelationInfoVO(E[] billVOs) {
		return null;
	}

	// 发送至会计平台
	public FipMsgResultVO[] sendFipMsg(E[] billVOs) throws BusinessException {
		ISecurityTokenCallback sc = (ISecurityTokenCallback) NCLocator
				.getInstance().lookup(ISecurityTokenCallback.class);
		sc.token("NCSystem".getBytes(), "pfxx".getBytes());

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
			YBNCBillHVO hvo = (YBNCBillHVO) billVO.getParentVO();
			String operator = hvo.getOperperson();
			String pk_operator = GetPk.getPkOperator(operator);

			FipMessageVO fipMsgVO = new FipMessageVO();
			fipMsgVO.setBillVO(billVO);
			fipMsgVO.setMessagetype(msgtype);
			FipRelationInfoVO infoVO = createFipRelationInfoVO(billVO);

			if (msgtype == FipMessageVO.MESSAGETYPE_ADD) {
				// 制单人
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
		YBNCBillHVO headVO = (YBNCBillHVO) billVO.getParent();
		YBNCBillBVO[] bodysVO = (YBNCBillBVO[]) billVO.getChildrenVO();
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
		
		// 金额---指单据中钱总和
		UFDouble sum = new UFDouble();
		DecimalFormat df = new DecimalFormat("#.00");
		for (YBNCBillBVO body : bodysVO) {
		if (body.getBys4() == null) {
			sum = sum.add(body.getMoney());
		} else {
			sum = sum.add(body.getBys4());
			body.setMoney(new UFDouble()); // 清零			
		}
		}
		infoVO.setFreedef3(df.format(sum).toString());//来源金额
		SequenceGenerator se = new SequenceGenerator();
		// 凭证关联号--与单据关联的值(合并)
		String pk_billid = se.generate();
		infoVO.setRelationID(pk_billid);// NC主键 唯一标识
		return infoVO;
	}
}
