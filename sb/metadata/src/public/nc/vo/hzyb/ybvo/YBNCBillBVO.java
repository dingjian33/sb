package nc.vo.hzyb.ybvo;

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
 * 此处添加累的描述信息
 * </p>
 * 创建日期:2017-11-1
 * 
 * @author YONYOU NC
 * @version NCPrj ??
 */

public class YBNCBillBVO extends SuperVO {

	
	public int dr;

	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}

	/**
	 * accname
	 */
	public String accname;
	/**
	 * accno
	 */
	public String accno;
	/**
	 * account_code
	 */
	public String account_code;
	/**
	 * bys3
	 */
	public String bys3;
	/**
	 * bys4
	 */
	public UFDouble bys4;
	/**
	 * bys5
	 */
	public UFDouble bys5;
	/**
	 * bys6
	 */
	public UFDouble bys6;
	/**
	 * bys7
	 */
	public UFDouble bys7;
	/**
	 * direct
	 */
	public String direct;
	/**
	 * enpname
	 */
	public String enpname;
	/**
	 * enpno
	 */
	public String enpno;
	/**
	 * enpprop
	 */
	public String enpprop;
	/**
	 * enppropname
	 */
	public String enppropname;
	/**
	 * money
	 */
	public UFDouble money;
	/**
	 * remark
	 */
	public String remark;
	/**
	 * sid
	 */
	public String sid;
	/**
	 * sidseq
	 */
	public String sidseq;
	/**
	 * pk_lineid
	 */
	public String pk_lineid;
	/**
	 * 上层单据主键
	 */
	public String pk_billid;
	/**
	 * 时间戳
	 */
	public UFDateTime ts;

	/**
	 * 属性 accname的Getter方法.属性名：accname 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getAccname() {
		return this.accname;
	}

	/**
	 * 属性accname的Setter方法.属性名：accname 创建日期:2017-11-1
	 * 
	 * @param newAccname
	 *            java.lang.String
	 */
	public void setAccname(String accname) {
		this.accname = accname;
	}

	/**
	 * 属性 accno的Getter方法.属性名：accno 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getAccno() {
		return this.accno;
	}

	/**
	 * 属性accno的Setter方法.属性名：accno 创建日期:2017-11-1
	 * 
	 * @param newAccno
	 *            java.lang.String
	 */
	public void setAccno(String accno) {
		this.accno = accno;
	}

	/**
	 * 属性 account_code的Getter方法.属性名：account_code 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getAccount_code() {
		return this.account_code;
	}

	/**
	 * 属性account_code的Setter方法.属性名：account_code 创建日期:2017-11-1
	 * 
	 * @param newAccount_code
	 *            java.lang.String
	 */
	public void setAccount_code(String account_code) {
		this.account_code = account_code;
	}

	/**
	 * 属性 bys3的Getter方法.属性名：bys3 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getBys3() {
		return this.bys3;
	}

	/**
	 * 属性bys3的Setter方法.属性名：bys3 创建日期:2017-11-1
	 * 
	 * @param newBys3
	 *            java.lang.String
	 */
	public void setBys3(String bys3) {
		this.bys3 = bys3;
	}

	/**
	 * 属性 direct的Getter方法.属性名：direct 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDirect() {
		return this.direct;
	}

	/**
	 * 属性direct的Setter方法.属性名：direct 创建日期:2017-11-1
	 * 
	 * @param newDirect
	 *            java.lang.String
	 */
	public void setDirect(String direct) {
		this.direct = direct;
	}

	/**
	 * 属性 enpname的Getter方法.属性名：enpname 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getEnpname() {
		return this.enpname;
	}

	/**
	 * 属性enpname的Setter方法.属性名：enpname 创建日期:2017-11-1
	 * 
	 * @param newEnpname
	 *            java.lang.String
	 */
	public void setEnpname(String enpname) {
		this.enpname = enpname;
	}

	/**
	 * 属性 enpno的Getter方法.属性名：enpno 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getEnpno() {
		return this.enpno;
	}

	/**
	 * 属性enpno的Setter方法.属性名：enpno 创建日期:2017-11-1
	 * 
	 * @param newEnpno
	 *            java.lang.String
	 */
	public void setEnpno(String enpno) {
		this.enpno = enpno;
	}

	/**
	 * 属性 enpprop的Getter方法.属性名：enpprop 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getEnpprop() {
		return this.enpprop;
	}

	/**
	 * 属性enpprop的Setter方法.属性名：enpprop 创建日期:2017-11-1
	 * 
	 * @param newEnpprop
	 *            java.lang.String
	 */
	public void setEnpprop(String enpprop) {
		this.enpprop = enpprop;
	}

	/**
	 * 属性 enppropname的Getter方法.属性名：enppropname 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getEnppropname() {
		return this.enppropname;
	}

	/**
	 * 属性enppropname的Setter方法.属性名：enppropname 创建日期:2017-11-1
	 * 
	 * @param newEnppropname
	 *            java.lang.String
	 */
	public void setEnppropname(String enppropname) {
		this.enppropname = enppropname;
	}

	public UFDouble getBys4() {
		return bys4;
	}

	public void setBys4(UFDouble bys4) {
		this.bys4 = bys4;
	}

	public UFDouble getBys5() {
		return bys5;
	}

	public void setBys5(UFDouble bys5) {
		this.bys5 = bys5;
	}

	public UFDouble getBys6() {
		return bys6;
	}

	public void setBys6(UFDouble bys6) {
		this.bys6 = bys6;
	}

	public UFDouble getBys7() {
		return bys7;
	}

	public void setBys7(UFDouble bys7) {
		this.bys7 = bys7;
	}

	public UFDouble getMoney() {
		return money;
	}

	public void setMoney(UFDouble money) {
		this.money = money;
	}

	/**
	 * 属性 remark的Getter方法.属性名：remark 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 属性remark的Setter方法.属性名：remark 创建日期:2017-11-1
	 * 
	 * @param newRemark
	 *            java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 属性 sid的Getter方法.属性名：sid 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getSid() {
		return this.sid;
	}

	/**
	 * 属性sid的Setter方法.属性名：sid 创建日期:2017-11-1
	 * 
	 * @param newSid
	 *            java.lang.String
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * 属性 sidseq的Getter方法.属性名：sidseq 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getSidseq() {
		return this.sidseq;
	}

	/**
	 * 属性sidseq的Setter方法.属性名：sidseq 创建日期:2017-11-1
	 * 
	 * @param newSidseq
	 *            java.lang.String
	 */
	public void setSidseq(String sidseq) {
		this.sidseq = sidseq;
	}

	/**
	 * 属性 pk_lineid的Getter方法.属性名：pk_lineid 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_lineid() {
		return this.pk_lineid;
	}

	/**
	 * 属性pk_lineid的Setter方法.属性名：pk_lineid 创建日期:2017-11-1
	 * 
	 * @param newPk_lineid
	 *            java.lang.String
	 */
	public void setPk_lineid(String pk_lineid) {
		this.pk_lineid = pk_lineid;
	}

	/**
	 * 属性 生成上层主键的Getter方法.属性名：上层主键 创建日期:2017-11-1
	 * 
	 * @return String
	 */
	public String getPk_billid() {
		return this.pk_billid;
	}

	/**
	 * 属性生成上层主键的Setter方法.属性名：上层主键 创建日期:2017-11-1
	 * 
	 * @param newPk_billid
	 *            String
	 */
	public void setPk_billid(String pk_billid) {
		this.pk_billid = pk_billid;
	}

	/**
	 * 属性 生成时间戳的Getter方法.属性名：时间戳 创建日期:2017-11-1
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * 属性生成时间戳的Setter方法.属性名：时间戳 创建日期:2017-11-1
	 * 
	 * @param newts
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("arap.YBNCBillBVO");
	}
}
