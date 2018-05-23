package nc.vo.hzsb.sbvo;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSBBillHVOMeta extends AbstractBillMeta{
	
	public AggSBBillHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.hzsb.sbvo.SBBillHVO.class);
		this.addChildren(nc.vo.hzsb.sbvo.SBBillBVO.class);
	}
}