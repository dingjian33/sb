package nc.vo.hzjy.jyvo;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2017-10-26
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
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 vssid的Getter方法.属性名：vssid
*  创建日期:2017-10-26
* @return java.lang.String
*/
public String getVssid() {
return this.vssid;
} 

/**
* 属性vssid的Setter方法.属性名：vssid
* 创建日期:2017-10-26
* @param newVssid java.lang.String
*/
public void setVssid ( String vssid) {
this.vssid=vssid;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：pk_org
*  创建日期:2017-10-26
* @return nc.vo.org.DeptVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：pk_org
* 创建日期:2017-10-26
* @param newPk_org nc.vo.org.DeptVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：pk_org_v
*  创建日期:2017-10-26
* @return java.lang.String
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* 属性pk_org_v的Setter方法.属性名：pk_org_v
* 创建日期:2017-10-26
* @param newPk_org_v java.lang.String
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：pk_group
*  创建日期:2017-10-26
* @return java.lang.String
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：pk_group
* 创建日期:2017-10-26
* @param newPk_group java.lang.String
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 pk_billid的Getter方法.属性名：pk_billid
*  创建日期:2017-10-26
* @return java.lang.String
*/
public String getPk_billid() {
return this.pk_billid;
} 

/**
* 属性pk_billid的Setter方法.属性名：pk_billid
* 创建日期:2017-10-26
* @param newPk_billid java.lang.String
*/
public void setPk_billid ( String pk_billid) {
this.pk_billid=pk_billid;
} 
 
/**
* 属性 pk_busitype的Getter方法.属性名：pk_busitype
*  创建日期:2017-10-26
* @return java.lang.String
*/
public String getPk_busitype() {
return this.pk_busitype;
} 

/**
* 属性pk_busitype的Setter方法.属性名：pk_busitype
* 创建日期:2017-10-26
* @param newPk_busitype java.lang.String
*/
public void setPk_busitype ( String pk_busitype) {
this.pk_busitype=pk_busitype;
} 
 
/**
* 属性 pk_billtype的Getter方法.属性名：pk_billtype
*  创建日期:2017-10-26
* @return java.lang.String
*/
public String getPk_billtype() {
return this.pk_billtype;
} 

/**
* 属性pk_billtype的Setter方法.属性名：pk_billtype
* 创建日期:2017-10-26
* @param newPk_billtype java.lang.String
*/
public void setPk_billtype ( String pk_billtype) {
this.pk_billtype=pk_billtype;
} 
 
/**
* 属性 vbys1的Getter方法.属性名：vbys1
*  创建日期:2017-10-26
* @return java.lang.String
*/
public String getVbys1() {
return this.vbys1;
} 

/**
* 属性vbys1的Setter方法.属性名：vbys1
* 创建日期:2017-10-26
* @param newVbys1 java.lang.String
*/
public void setVbys1 ( String vbys1) {
this.vbys1=vbys1;
} 
 
/**
* 属性 vbys2的Getter方法.属性名：vbys2
*  创建日期:2017-10-26
* @return java.lang.String
*/
public String getVbys2() {
return this.vbys2;
} 

/**
* 属性vbys2的Setter方法.属性名：vbys2
* 创建日期:2017-10-26
* @param newVbys2 java.lang.String
*/
public void setVbys2 ( String vbys2) {
this.vbys2=vbys2;
} 
 
/**
* 属性 vbys3的Getter方法.属性名：vbys3
*  创建日期:2017-10-26
* @return java.lang.String
*/
public String getVbys3() {
return this.vbys3;
} 

/**
* 属性vbys3的Setter方法.属性名：vbys3
* 创建日期:2017-10-26
* @param newVbys3 java.lang.String
*/
public void setVbys3 ( String vbys3) {
this.vbys3=vbys3;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-10-26
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-10-26
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
    