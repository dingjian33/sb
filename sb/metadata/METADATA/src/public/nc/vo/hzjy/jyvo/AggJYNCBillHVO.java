package nc.vo.hzjy.jyvo;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hzjy.jyvo.JYNCBillHVO")

public class AggJYNCBillHVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggJYNCBillHVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public JYNCBillHVO getParentVO(){
	  	return (JYNCBillHVO)this.getParent();
	  }
	  
}