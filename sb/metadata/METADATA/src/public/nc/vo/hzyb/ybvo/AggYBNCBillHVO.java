package nc.vo.hzyb.ybvo;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hzyb.ybvo.YBNCBillHVO")

public class AggYBNCBillHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggYBNCBillHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public YBNCBillHVO getParentVO(){
	  	return (YBNCBillHVO)this.getParent();
	  }
	  
}