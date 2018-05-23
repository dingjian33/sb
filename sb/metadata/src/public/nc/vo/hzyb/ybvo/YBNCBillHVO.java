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

public class YBNCBillHVO extends SuperVO {
	public int dr;

	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}

	/**
	 * account
	 */
	public String account;
	/**
	 * bank
	 */
	public String bank;
	/**
	 * bankacc
	 */
	public String bankacc;
	/**
	 * datasouce
	 */
	public String datasouce;
	/**
	 * datasouceid
	 */
	public String datasoucesid;
	/**
	 * datatype
	 */
	public String datatype;
	/**
	 * deptname
	 */
	public String deptname;
	/**
	 * deptno
	 */
	public String deptno;
	/**
	 * flag
	 */
	public String flag;
	/**
	 * nc_error
	 */
	public String nc_error;
	/**
	 * nc_voucher
	 */
	public String nc_voucher;
	/**
	 * operatorid
	 */
	public String operatorid;
	/**
	 * operperson
	 */
	public String operperson;
	/**
	 * opertype
	 */
	public String opertype;
	/**
	 * opertypename
	 */
	public String opertypename;
	/**
	 * pcode
	 */
	public String pcode;
	/**
	 * pname
	 */
	public String pname;
	/**
	 * posting_date
	 */
	public String posting_date;
	/**
	 * posting_person
	 */
	public String posting_person;
	/**
	 * sid
	 */
	public String sid;
	/**
	 * year
	 */
	public String year;
	/**
	 * operdate
	 */
	public UFDate operdate;
	/**
	 * prepareddate
	 */
	public String prepareddate;
	/**
	 * summoney
	 */
	public UFDouble summoney;
	/**
	 * pk_billid
	 */
	public String pk_billid;
	/**
	 * pk_billtype
	 */
	public String pk_billtype;
	/**
	 * pk_busitype
	 */
	public String pk_busitype;
	/**
	 * pk_org
	 */
	public String pk_org;
	/**
	 * pk_org_v
	 */
	public String pk_org_v;
	/**
	 * pk_group
	 */
	public String pk_group;
	/**
	 * 时间戳
	 */
	public UFDateTime ts;

	/**
	 * 属性 account的Getter方法.属性名：account 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getAccount() {
		return this.account;
	}

	/**
	 * 属性account的Setter方法.属性名：account 创建日期:2017-11-1
	 * 
	 * @param newAccount
	 *            java.lang.String
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 属性 bank的Getter方法.属性名：bank 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getBank() {
		return this.bank;
	}

	/**
	 * 属性bank的Setter方法.属性名：bank 创建日期:2017-11-1
	 * 
	 * @param newBank
	 *            java.lang.String
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * 属性 bankacc的Getter方法.属性名：bankacc 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getBankacc() {
		return this.bankacc;
	}

	/**
	 * 属性bankacc的Setter方法.属性名：bankacc 创建日期:2017-11-1
	 * 
	 * @param newBankacc
	 *            java.lang.String
	 */
	public void setBankacc(String bankacc) {
		this.bankacc = bankacc;
	}

	/**
	 * 属性 datasouce的Getter方法.属性名：datasouce 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDatasouce() {
		return this.datasouce;
	}

	/**
	 * 属性datasouce的Setter方法.属性名：datasouce 创建日期:2017-11-1
	 * 
	 * @param newDatasouce
	 *            java.lang.String
	 */
	public void setDatasouce(String datasouce) {
		this.datasouce = datasouce;
	}

	/**
	 * 属性 datasouceid的Getter方法.属性名：datasouceid 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDatasoucesid() {
		return this.datasoucesid;
	}

	/**
	 * 属性datasouceid的Setter方法.属性名：datasouceid 创建日期:2017-11-1
	 * 
	 * @param newDatasouceid
	 *            java.lang.String
	 */
	public void setDatasoucesid(String datasoucesid) {
		this.datasoucesid = datasoucesid;
	}

	/**
	 * 属性 datatype的Getter方法.属性名：datatype 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDatatype() {
		return this.datatype;
	}

	/**
	 * 属性datatype的Setter方法.属性名：datatype 创建日期:2017-11-1
	 * 
	 * @param newDatatype
	 *            java.lang.String
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	/**
	 * 属性 deptname的Getter方法.属性名：deptname 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDeptname() {
		return this.deptname;
	}

	/**
	 * 属性deptname的Setter方法.属性名：deptname 创建日期:2017-11-1
	 * 
	 * @param newDeptname
	 *            java.lang.String
	 */
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	/**
	 * 属性 deptno的Getter方法.属性名：deptno 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDeptno() {
		return this.deptno;
	}

	/**
	 * 属性deptno的Setter方法.属性名：deptno 创建日期:2017-11-1
	 * 
	 * @param newDeptno
	 *            java.lang.String
	 */
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	/**
	 * 属性 flag的Getter方法.属性名：flag 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性flag的Setter方法.属性名：flag 创建日期:2017-11-1
	 * 
	 * @param newFlag
	 *            java.lang.String
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 属性 nc_error的Getter方法.属性名：nc_error 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getNc_error() {
		return this.nc_error;
	}

	/**
	 * 属性nc_error的Setter方法.属性名：nc_error 创建日期:2017-11-1
	 * 
	 * @param newNc_error
	 *            java.lang.String
	 */
	public void setNc_error(String nc_error) {
		this.nc_error = nc_error;
	}

	/**
	 * 属性 nc_voucher的Getter方法.属性名：nc_voucher 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getNc_voucher() {
		return this.nc_voucher;
	}

	/**
	 * 属性nc_voucher的Setter方法.属性名：nc_voucher 创建日期:2017-11-1
	 * 
	 * @param newNc_voucher
	 *            java.lang.String
	 */
	public void setNc_voucher(String nc_voucher) {
		this.nc_voucher = nc_voucher;
	}

	/**
	 * 属性 operatorid的Getter方法.属性名：operatorid 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getOperatorid() {
		return this.operatorid;
	}

	/**
	 * 属性operatorid的Setter方法.属性名：operatorid 创建日期:2017-11-1
	 * 
	 * @param newOperatorid
	 *            java.lang.String
	 */
	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	/**
	 * 属性 operperson的Getter方法.属性名：operperson 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getOperperson() {
		return this.operperson;
	}

	/**
	 * 属性operperson的Setter方法.属性名：operperson 创建日期:2017-11-1
	 * 
	 * @param newOperperson
	 *            java.lang.String
	 */
	public void setOperperson(String operperson) {
		this.operperson = operperson;
	}

	/**
	 * 属性 opertype的Getter方法.属性名：opertype 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getOpertype() {
		return this.opertype;
	}

	/**
	 * 属性opertype的Setter方法.属性名：opertype 创建日期:2017-11-1
	 * 
	 * @param newOpertype
	 *            java.lang.String
	 */
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}

	/**
	 * 属性 opertypename的Getter方法.属性名：opertypename 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getOpertypename() {
		return this.opertypename;
	}

	/**
	 * 属性opertypename的Setter方法.属性名：opertypename 创建日期:2017-11-1
	 * 
	 * @param newOpertypename
	 *            java.lang.String
	 */
	public void setOpertypename(String opertypename) {
		this.opertypename = opertypename;
	}

	/**
	 * 属性 pcode的Getter方法.属性名：pcode 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPcode() {
		return this.pcode;
	}

	/**
	 * 属性pcode的Setter方法.属性名：pcode 创建日期:2017-11-1
	 * 
	 * @param newPcode
	 *            java.lang.String
	 */
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	/**
	 * 属性 pname的Getter方法.属性名：pname 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPname() {
		return this.pname;
	}

	/**
	 * 属性pname的Setter方法.属性名：pname 创建日期:2017-11-1
	 * 
	 * @param newPname
	 *            java.lang.String
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	/**
	 * 属性 posting_date的Getter方法.属性名：posting_date 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPosting_date() {
		return this.posting_date;
	}

	/**
	 * 属性posting_date的Setter方法.属性名：posting_date 创建日期:2017-11-1
	 * 
	 * @param newPosting_date
	 *            java.lang.String
	 */
	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}

	/**
	 * 属性 posting_person的Getter方法.属性名：posting_person 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPosting_person() {
		return this.posting_person;
	}

	/**
	 * 属性posting_person的Setter方法.属性名：posting_person 创建日期:2017-11-1
	 * 
	 * @param newPosting_person
	 *            java.lang.String
	 */
	public void setPosting_person(String posting_person) {
		this.posting_person = posting_person;
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
	 * 属性 year的Getter方法.属性名：year 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getYear() {
		return this.year;
	}

	/**
	 * 属性year的Setter方法.属性名：year 创建日期:2017-11-1
	 * 
	 * @param newYear
	 *            java.lang.String
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * 属性 operdate的Getter方法.属性名：operdate 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */

	/**
	 * 属性 prepareddate的Getter方法.属性名：prepareddate 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPrepareddate() {
		return this.prepareddate;
	}

	public UFDate getOperdate() {
		return operdate;
	}

	public void setOperdate(UFDate operdate) {
		this.operdate = operdate;
	}

	/**
	 * 属性prepareddate的Setter方法.属性名：prepareddate 创建日期:2017-11-1
	 * 
	 * @param newPrepareddate
	 *            java.lang.String
	 */
	public void setPrepareddate(String prepareddate) {
		this.prepareddate = prepareddate;
	}

	/**
	 * 属性 summoney的Getter方法.属性名：summoney 创建日期:2017-11-1
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */

	/**
	 * 属性 pk_billid的Getter方法.属性名：pk_billid 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_billid() {
		return this.pk_billid;
	}

	/**
	 * 属性pk_billid的Setter方法.属性名：pk_billid 创建日期:2017-11-1
	 * 
	 * @param newPk_billid
	 *            java.lang.String
	 */
	public void setPk_billid(String pk_billid) {
		this.pk_billid = pk_billid;
	}

	/**
	 * 属性 pk_billtype的Getter方法.属性名：pk_billtype 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_billtype() {
		return this.pk_billtype;
	}

	/**
	 * 属性pk_billtype的Setter方法.属性名：pk_billtype 创建日期:2017-11-1
	 * 
	 * @param newPk_billtype
	 *            java.lang.String
	 */
	public void setPk_billtype(String pk_billtype) {
		this.pk_billtype = pk_billtype;
	}

	/**
	 * 属性 pk_busitype的Getter方法.属性名：pk_busitype 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_busitype() {
		return this.pk_busitype;
	}

	/**
	 * 属性pk_busitype的Setter方法.属性名：pk_busitype 创建日期:2017-11-1
	 * 
	 * @param newPk_busitype
	 *            java.lang.String
	 */
	public void setPk_busitype(String pk_busitype) {
		this.pk_busitype = pk_busitype;
	}

	/**
	 * 属性 pk_org的Getter方法.属性名：pk_org 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_org() {
		return this.pk_org;
	}

	/**
	 * 属性pk_org的Setter方法.属性名：pk_org 创建日期:2017-11-1
	 * 
	 * @param newPk_org
	 *            java.lang.String
	 */
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	/**
	 * 属性 pk_org_v的Getter方法.属性名：pk_org_v 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_org_v() {
		return this.pk_org_v;
	}

	/**
	 * 属性pk_org_v的Setter方法.属性名：pk_org_v 创建日期:2017-11-1
	 * 
	 * @param newPk_org_v
	 *            java.lang.String
	 */
	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	/**
	 * 属性 pk_group的Getter方法.属性名：pk_group 创建日期:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_group() {
		return this.pk_group;
	}

	/**
	 * 属性pk_group的Setter方法.属性名：pk_group 创建日期:2017-11-1
	 * 
	 * @param newPk_group
	 *            java.lang.String
	 */
	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
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

	public UFDouble getSummoney() {
		return summoney;
	}

	public void setSummoney(UFDouble summoney) {
		this.summoney = summoney;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("arap.YBNCBillHVO");
	}
}
