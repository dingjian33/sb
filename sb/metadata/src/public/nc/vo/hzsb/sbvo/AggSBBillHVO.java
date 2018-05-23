package nc.vo.hzsb.sbvo;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hzsb.sbvo.SBBillHVO")

public class AggSBBillHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggSBBillHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public SBBillHVO getParentVO(){
	  	return (SBBillHVO)this.getParent();
	  }
	  
}