package nc.vo.hzsb.sbvo;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hzsb.sbvo.SBNCBillHVO")

public class AggSBNCBillHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggSBNCBillHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public SBNCBillHVO getParentVO(){
	  	return (SBNCBillHVO)this.getParent();
	  }
	  
}