package nc.vo.hzyb.ybvo;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggYBNCBillHVOMeta extends AbstractBillMeta{
	
	public AggYBNCBillHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.hzyb.ybvo.YBNCBillHVO.class);
		this.addChildren(nc.vo.hzyb.ybvo.YBNCBillBVO.class);
	}
}