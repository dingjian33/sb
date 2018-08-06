package nc.vo.hzyb.ybvo;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggYBBillHVOMeta extends AbstractBillMeta{
	
	public AggYBBillHVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.hzyb.ybvo.YBBillHVO.class);
		this.addChildren(nc.vo.hzyb.ybvo.YBBillBVO.class);
	}
}