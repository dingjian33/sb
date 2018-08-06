package nc.bs.hzsb.fipVoucher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.logging.Log;
import nc.impl.am.db.DBAccessUtil;
import nc.impl.am.dbbase.BillQueryByCond;
import nc.md.MDBaseQueryFacade;
import nc.md.model.access.javamap.AggVOStyle;
import nc.pubitf.fip.service.IFipMessageService;
import nc.pubitf.fip.service.IFipRelationQueryService;
import nc.vo.am.common.util.AssertUtils;
import nc.vo.am.common.util.CollectionUtils;
import nc.vo.am.common.util.ExceptionUtils;
import nc.vo.am.constant.CommonKeyConst;
import nc.vo.am.proxy.AMProxy;
import nc.vo.am.pub.uap.ModuleInfoQuery;
import nc.vo.fip.external.FipExtendAggVO;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipMsgResultVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.util.SqlWhereUtil;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

@SuppressWarnings("unchecked")
public class PubFipToVoucherImpl {

	private final Log log = Log.getInstance(getClass());

	public Collection<FipExtendAggVO> queryBillByRelations(
			Collection<FipRelationInfoVO> relationvos) throws BusinessException {
		if (CollectionUtils.isEmpty(relationvos))
			return null;
		String bill_type = null;
		List<String> pkList = new ArrayList<String>();
		for (FipRelationInfoVO infoVO : relationvos) {
			pkList.add(infoVO.getRelationID());
			if (StringUtils.isEmpty(bill_type))
				bill_type = infoVO.getPk_billtype();
		}
		// 查询重算的单据数�?
		AbstractBill[] billVOs = queryBillVOs(bill_type,
				pkList.toArray(new String[0]));
		// 处理为会计平台使用的数据
		List<FipExtendAggVO> list = createFipExtendData(billVOs);
		return list;
	}

	/**
	 * 创建会计平台重算数据
	 * 
	 * @param billVOs
	 * @return
	 * @throws BusinessException
	 * @author
	 */
	protected List<FipExtendAggVO> createFipExtendData(AbstractBill[] billVOs)
			throws BusinessException {
		List<FipExtendAggVO> list = new ArrayList<FipExtendAggVO>();

		FipExtendAggVO fipVO = null;
		for (AbstractBill billVO : billVOs) {
			fipVO = new FipExtendAggVO();
			fipVO.setRelationID(billVO.getPrimaryKey());
			fipVO.setBillVO(billVO);

			list.add(fipVO);
		}
		return list;
	}

	/**
	 * 查找单据
	 * 
	 * @param bill_type
	 * @param ids
	 * @return
	 * @throws BusinessException
	 */

	protected AbstractBill[] queryBillVOs(String bill_type, String[] ids)
			throws BusinessException {
		String billTypeCode = bill_type.split("-")[0];
		SqlWhereUtil swu = new SqlWhereUtil();
		// 单据类型
		swu.and(" isnull(istransaction,'N') ='N'");
		swu.and(" pk_billtypecode = '" + billTypeCode + "'");
		swu.and(" (pk_group = '~' or pk_group = 'GLOBLE00000000000000')");

		BilltypeVO[] billTypeVos = new DBAccessUtil().querySuperVosByCondition(
				BilltypeVO.class, swu.getSQLWhere());
		AssertUtils.isTrue(
				nc.vo.am.common.util.ArrayUtils.isNotEmpty(billTypeVos),
				" Predefined billtype is not correct,please check it!");
		BilltypeVO billTypeInfo = billTypeVos[0];

		String aggClassName = ((AggVOStyle) MDBaseQueryFacade.getInstance()
				.getComponentByName(billTypeInfo.getComponent())
				.getPrimaryBusinessEntity().getBeanStyle()).getAggVOClassName();
		Class<? extends AbstractBill> aggClass = null;
		try {
			aggClass = (Class<? extends AbstractBill>) Class
					.forName(aggClassName);
		} catch (ClassNotFoundException e) {
			log.error(
					nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
							"ampub_0", "04501000-0661")/* @res "加载单据的聚合VO类失�? *//*
																				 * -=
																				 * notranslate
																				 * =
																				 * -
																				 */,
					e);
			ExceptionUtils.asBusinessException(e);
		}
		return queryBillVOByPks(aggClass, ids, true);
	}

	public <T extends AbstractBill> AbstractBill[] queryBillVOByPks(
			Class<T> billClass, String[] keys, boolean isQueryBody)
	/*     */{
		/* 50 */BillQueryByCond action = new BillQueryByCond(billClass);
		/* 51 */return ((AbstractBill[]) action.query(keys, isQueryBody));
		/*     */}

	/**
	 * 发�?会计平台消息
	 * 
	 * @param billVO
	 * @throws BusinessException
	 */

	public FipMsgResultVO[] sendFipMessage(
			nc.vo.uif2.LoginContext paramLoginContext, AbstractBill[] billVOs)
			throws BusinessException {
		/*
		 * if (ArrayUt6ils.isEmpty(billVOs)) return null;
		 */
		// return isModuleEnabled("1017");
		if (ModuleInfoQuery.isFIPEnabled()) {
			try {
				return AMProxy.lookup(IFipMessageService.class).sendMessages(
						createFipMessage(paramLoginContext,
								FipMessageVO.MESSAGETYPE_ADD, billVOs));
			} catch (Exception e) {
				ExceptionUtils.asBusinessException(
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"ampub_0", "04501000-0659")/*
															 * @ res "生成凭证出错�?
															 */, e);
			}
		}
		return null;
	}

	/**
	 * 构建会计平台消息
	 * 
	 * @param billVO
	 * @param messagetype
	 * @return
	 * @throws BusinessException
	 */
	protected FipMessageVO[] createFipMessage(nc.vo.uif2.LoginContext context,
			int messagetype, AbstractBill... billVOs) throws BusinessException {
		List<FipMessageVO> fipMessageVOs = new ArrayList<FipMessageVO>();
		for (AbstractBill billVO : billVOs) {
			SuperVO headVO = (SuperVO) billVO.getParentVO();

			FipMessageVO fipMessageVO = new FipMessageVO();
			fipMessageVO.setBillVO(billVO);
			fipMessageVO.setMessagetype(messagetype);
			FipRelationInfoVO infoVO = createFipRelationInfoVO(billVO);
			if (messagetype == FipMessageVO.MESSAGETYPE_ADD) {
				String auditor = (String) headVO.getAttributeValue("approver");
				if (auditor != null) {
					infoVO.setPk_operator(auditor);
				} else
					infoVO.setPk_operator(InvocationInfoProxy.getInstance()
							.getUserId());
			} else if (messagetype == FipMessageVO.MESSAGETYPE_DEL) {
				infoVO.setPk_operator(InvocationInfoProxy.getInstance()
						.getUserId());
			}
			fipMessageVO.setMessageinfo(infoVO);
			fipMessageVOs.add(fipMessageVO);
		}

		return fipMessageVOs.toArray(new FipMessageVO[0]);
	}

	protected FipRelationInfoVO createFipRelationInfoVO(AbstractBill billVO) {
		SuperVO headVO = (SuperVO) billVO.getParentVO();
		SuperVO[] bodysVO = (SuperVO[]) billVO.getChildrenVO();
		String bill_type = "";
		if ("CB02".equals(headVO.getAttributeValue("bill_type"))
				|| "CB03".equals(headVO.getAttributeValue("bill_type"))
				|| "CB05".equals(headVO.getAttributeValue("bill_type"))) {
			bill_type = (String) billVO.getParentVO().getAttributeValue(
					CommonKeyConst.bill_type);
		} else {
			bill_type = (String) billVO.getParentVO().getAttributeValue(
					"bill_type");
		}
		String transType = (String) billVO.getParentVO().getAttributeValue(
				CommonKeyConst.transi_type);
		String billOrTransType = bill_type;
		if (transType != null) {
			billOrTransType = transType;
		}

		FipRelationInfoVO infoVO = new FipRelationInfoVO();
		infoVO.setPk_group((String) headVO
				.getAttributeValue(CommonKeyConst.pk_group));
		infoVO.setPk_org((String) headVO
				.getAttributeValue(CommonKeyConst.pk_org));
		infoVO.setPk_system("cbhz");
		infoVO.setPk_billtype(billOrTransType);
		infoVO.setRelationID(headVO.getPrimaryKey()); // 主键

		UFDouble totalmny = new UFDouble(0);
		UFDouble totalmn = UFDouble.ZERO_DBL;
		if ("CB02".equals(bill_type)) {
			infoVO.setFreedef1((String) headVO.getAttributeValue("billno"));
			if (null != headVO.getAttributeValue("zsy"))
				totalmny = totalmny.add((UFDouble) headVO
						.getAttributeValue("zsy"));
			totalmn = totalmny.setScale(2, BigDecimal.ROUND_HALF_UP);
		} else if ("CB01".equals(bill_type) || "CB04".equals(bill_type)) {
			for (SuperVO bodyVO : bodysVO) {
				if (null != bodyVO
						&& null != bodyVO.getAttributeValue("realmny"))
					totalmny = totalmny.add((UFDouble) bodyVO
							.getAttributeValue("realmny"));
				totalmn = totalmny.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			infoVO.setFreedef1((String) headVO.getAttributeValue("vbillcode"));
		} else if ("CB05".equals(bill_type)) {
			for (SuperVO bodyVO : bodysVO) {
				if (null != bodyVO
						&& null != bodyVO.getAttributeValue("salein"))
					totalmny = totalmny.add((UFDouble) bodyVO
							.getAttributeValue("salein"));
				totalmn = totalmny.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			infoVO.setFreedef1((String) headVO.getAttributeValue("billno"));
		} else {
			for (SuperVO bodyVO : bodysVO) {
				if (null != bodyVO && null != bodyVO.getAttributeValue("sum"))
					totalmny = totalmny.add((UFDouble) bodyVO
							.getAttributeValue("sum"));
				totalmn = totalmny.setScale(2, BigDecimal.ROUND_HALF_UP);

			}
			infoVO.setFreedef1((String) headVO.getAttributeValue("bill_code"));
		}
		infoVO.setFreedef3(String.valueOf(totalmn));
		return infoVO;

	}

	public FipMsgResultVO[] deleteFipMessage(nc.vo.uif2.LoginContext context,
			AbstractBill[] billVOs) throws BusinessException {
		if (ArrayUtils.isEmpty(billVOs))
			return null;
		if (ModuleInfoQuery.isFIPEnabled()) {
			try {
				return AMProxy.lookup(IFipMessageService.class).sendMessages(
						createFipMessage(context, FipMessageVO.MESSAGETYPE_DEL,
								billVOs));
			} catch (Exception e) {
				ExceptionUtils.asBusinessException(
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"ampub_0", "04501000-0660")/*
															 * @ res "删除凭证出错�?
															 */, e);
			}
		}
		return null;
	}

	public UFBoolean[] queryFipState(AbstractBill[] billVOs)
			throws BusinessException {
		FipRelationInfoVO[] infoVOs = new FipRelationInfoVO[billVOs.length];
		for (int i = 0; i < billVOs.length; i++) {
			infoVOs[i] = createFipRelationInfoVO(billVOs[i]);
		}
		return AMProxy.lookup(IFipRelationQueryService.class)
				.queryDesBillState(infoVOs, null)[0];
	}

}
