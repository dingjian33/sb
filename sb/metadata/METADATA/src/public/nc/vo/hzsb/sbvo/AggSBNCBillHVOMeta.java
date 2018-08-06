package nc.vo.hzsb.sbvo;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggSBNCBillHVOMeta extends AbstractBillMeta{
	
	public AggSBNCBillHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.hzsb.sbvo.SBNCBillHVO.class);
		this.addChildren(nc.vo.hzsb.sbvo.SBNCBillBVO.class);
	}
}