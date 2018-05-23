package nc.vo.hzyb.ybvo;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hzyb.ybvo.YBBillHVO")

public class AggYBBillHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggYBBillHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public YBBillHVO getParentVO(){
	  	return (YBBillHVO)this.getParent();
	  }
	  
}