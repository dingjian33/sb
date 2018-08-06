package nc.vo.hzjy.jyvo;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2017-10-26
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class JYNCBillHVO extends SuperVO {
	
/**
*vssid
*/
public String vssid;
/**
*pk_org
*/
public String pk_org;
/**
*pk_org_v
*/
public String pk_org_v;
/**
*pk_group
*/
public String pk_group;
/**
*pk_billid
*/
public String pk_billid;
/**
*pk_busitype
*/
public String pk_busitype;
/**
*pk_billtype
*/
public String pk_billtype;
/**
*vbys1
*/
public String vbys1;
/**
*vbys2
*/
public String vbys2;
/**
*vbys3
*/
public String vbys3;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� vssid��Getter����.��������vssid
*  ��������:2017-10-26
* @return java.lang.String
*/
public String getVssid() {
return this.vssid;
} 

/**
* ����vssid��Setter����.��������vssid
* ��������:2017-10-26
* @param newVssid java.lang.String
*/
public void setVssid ( String vssid) {
this.vssid=vssid;
} 
 
/**
* ���� pk_org��Getter����.��������pk_org
*  ��������:2017-10-26
* @return nc.vo.org.DeptVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.��������pk_org
* ��������:2017-10-26
* @param newPk_org nc.vo.org.DeptVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_org_v��Getter����.��������pk_org_v
*  ��������:2017-10-26
* @return java.lang.String
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* ����pk_org_v��Setter����.��������pk_org_v
* ��������:2017-10-26
* @param newPk_org_v java.lang.String
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* ���� pk_group��Getter����.��������pk_group
*  ��������:2017-10-26
* @return java.lang.String
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.��������pk_group
* ��������:2017-10-26
* @param newPk_group java.lang.String
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� pk_billid��Getter����.��������pk_billid
*  ��������:2017-10-26
* @return java.lang.String
*/
public String getPk_billid() {
return this.pk_billid;
} 

/**
* ����pk_billid��Setter����.��������pk_billid
* ��������:2017-10-26
* @param newPk_billid java.lang.String
*/
public void setPk_billid ( String pk_billid) {
this.pk_billid=pk_billid;
} 
 
/**
* ���� pk_busitype��Getter����.��������pk_busitype
*  ��������:2017-10-26
* @return java.lang.String
*/
public String getPk_busitype() {
return this.pk_busitype;
} 

/**
* ����pk_busitype��Setter����.��������pk_busitype
* ��������:2017-10-26
* @param newPk_busitype java.lang.String
*/
public void setPk_busitype ( String pk_busitype) {
this.pk_busitype=pk_busitype;
} 
 
/**
* ���� pk_billtype��Getter����.��������pk_billtype
*  ��������:2017-10-26
* @return java.lang.String
*/
public String getPk_billtype() {
return this.pk_billtype;
} 

/**
* ����pk_billtype��Setter����.��������pk_billtype
* ��������:2017-10-26
* @param newPk_billtype java.lang.String
*/
public void setPk_billtype ( String pk_billtype) {
this.pk_billtype=pk_billtype;
} 
 
/**
* ���� vbys1��Getter����.��������vbys1
*  ��������:2017-10-26
* @return java.lang.String
*/
public String getVbys1() {
return this.vbys1;
} 

/**
* ����vbys1��Setter����.��������vbys1
* ��������:2017-10-26
* @param newVbys1 java.lang.String
*/
public void setVbys1 ( String vbys1) {
this.vbys1=vbys1;
} 
 
/**
* ���� vbys2��Getter����.��������vbys2
*  ��������:2017-10-26
* @return java.lang.String
*/
public String getVbys2() {
return this.vbys2;
} 

/**
* ����vbys2��Setter����.��������vbys2
* ��������:2017-10-26
* @param newVbys2 java.lang.String
*/
public void setVbys2 ( String vbys2) {
this.vbys2=vbys2;
} 
 
/**
* ���� vbys3��Getter����.��������vbys3
*  ��������:2017-10-26
* @return java.lang.String
*/
public String getVbys3() {
return this.vbys3;
} 

/**
* ����vbys3��Setter����.��������vbys3
* ��������:2017-10-26
* @param newVbys3 java.lang.String
*/
public void setVbys3 ( String vbys3) {
this.vbys3=vbys3;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-10-26
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-10-26
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("arap.JYNCBillHVO");
    }
   }
    