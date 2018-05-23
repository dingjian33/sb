package nc.vo.hzyb.ybvo;

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
 * �˴�����۵�������Ϣ
 * </p>
 * ��������:2017-11-1
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
	 * ʱ���
	 */
	public UFDateTime ts;

	/**
	 * ���� account��Getter����.��������account ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getAccount() {
		return this.account;
	}

	/**
	 * ����account��Setter����.��������account ��������:2017-11-1
	 * 
	 * @param newAccount
	 *            java.lang.String
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * ���� bank��Getter����.��������bank ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getBank() {
		return this.bank;
	}

	/**
	 * ����bank��Setter����.��������bank ��������:2017-11-1
	 * 
	 * @param newBank
	 *            java.lang.String
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * ���� bankacc��Getter����.��������bankacc ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getBankacc() {
		return this.bankacc;
	}

	/**
	 * ����bankacc��Setter����.��������bankacc ��������:2017-11-1
	 * 
	 * @param newBankacc
	 *            java.lang.String
	 */
	public void setBankacc(String bankacc) {
		this.bankacc = bankacc;
	}

	/**
	 * ���� datasouce��Getter����.��������datasouce ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDatasouce() {
		return this.datasouce;
	}

	/**
	 * ����datasouce��Setter����.��������datasouce ��������:2017-11-1
	 * 
	 * @param newDatasouce
	 *            java.lang.String
	 */
	public void setDatasouce(String datasouce) {
		this.datasouce = datasouce;
	}

	/**
	 * ���� datasouceid��Getter����.��������datasouceid ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDatasoucesid() {
		return this.datasoucesid;
	}

	/**
	 * ����datasouceid��Setter����.��������datasouceid ��������:2017-11-1
	 * 
	 * @param newDatasouceid
	 *            java.lang.String
	 */
	public void setDatasoucesid(String datasoucesid) {
		this.datasoucesid = datasoucesid;
	}

	/**
	 * ���� datatype��Getter����.��������datatype ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDatatype() {
		return this.datatype;
	}

	/**
	 * ����datatype��Setter����.��������datatype ��������:2017-11-1
	 * 
	 * @param newDatatype
	 *            java.lang.String
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	/**
	 * ���� deptname��Getter����.��������deptname ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDeptname() {
		return this.deptname;
	}

	/**
	 * ����deptname��Setter����.��������deptname ��������:2017-11-1
	 * 
	 * @param newDeptname
	 *            java.lang.String
	 */
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	/**
	 * ���� deptno��Getter����.��������deptno ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDeptno() {
		return this.deptno;
	}

	/**
	 * ����deptno��Setter����.��������deptno ��������:2017-11-1
	 * 
	 * @param newDeptno
	 *            java.lang.String
	 */
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	/**
	 * ���� flag��Getter����.��������flag ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ����flag��Setter����.��������flag ��������:2017-11-1
	 * 
	 * @param newFlag
	 *            java.lang.String
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * ���� nc_error��Getter����.��������nc_error ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getNc_error() {
		return this.nc_error;
	}

	/**
	 * ����nc_error��Setter����.��������nc_error ��������:2017-11-1
	 * 
	 * @param newNc_error
	 *            java.lang.String
	 */
	public void setNc_error(String nc_error) {
		this.nc_error = nc_error;
	}

	/**
	 * ���� nc_voucher��Getter����.��������nc_voucher ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getNc_voucher() {
		return this.nc_voucher;
	}

	/**
	 * ����nc_voucher��Setter����.��������nc_voucher ��������:2017-11-1
	 * 
	 * @param newNc_voucher
	 *            java.lang.String
	 */
	public void setNc_voucher(String nc_voucher) {
		this.nc_voucher = nc_voucher;
	}

	/**
	 * ���� operatorid��Getter����.��������operatorid ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getOperatorid() {
		return this.operatorid;
	}

	/**
	 * ����operatorid��Setter����.��������operatorid ��������:2017-11-1
	 * 
	 * @param newOperatorid
	 *            java.lang.String
	 */
	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	/**
	 * ���� operperson��Getter����.��������operperson ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getOperperson() {
		return this.operperson;
	}

	/**
	 * ����operperson��Setter����.��������operperson ��������:2017-11-1
	 * 
	 * @param newOperperson
	 *            java.lang.String
	 */
	public void setOperperson(String operperson) {
		this.operperson = operperson;
	}

	/**
	 * ���� opertype��Getter����.��������opertype ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getOpertype() {
		return this.opertype;
	}

	/**
	 * ����opertype��Setter����.��������opertype ��������:2017-11-1
	 * 
	 * @param newOpertype
	 *            java.lang.String
	 */
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}

	/**
	 * ���� opertypename��Getter����.��������opertypename ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getOpertypename() {
		return this.opertypename;
	}

	/**
	 * ����opertypename��Setter����.��������opertypename ��������:2017-11-1
	 * 
	 * @param newOpertypename
	 *            java.lang.String
	 */
	public void setOpertypename(String opertypename) {
		this.opertypename = opertypename;
	}

	/**
	 * ���� pcode��Getter����.��������pcode ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPcode() {
		return this.pcode;
	}

	/**
	 * ����pcode��Setter����.��������pcode ��������:2017-11-1
	 * 
	 * @param newPcode
	 *            java.lang.String
	 */
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	/**
	 * ���� pname��Getter����.��������pname ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPname() {
		return this.pname;
	}

	/**
	 * ����pname��Setter����.��������pname ��������:2017-11-1
	 * 
	 * @param newPname
	 *            java.lang.String
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	/**
	 * ���� posting_date��Getter����.��������posting_date ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPosting_date() {
		return this.posting_date;
	}

	/**
	 * ����posting_date��Setter����.��������posting_date ��������:2017-11-1
	 * 
	 * @param newPosting_date
	 *            java.lang.String
	 */
	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}

	/**
	 * ���� posting_person��Getter����.��������posting_person ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPosting_person() {
		return this.posting_person;
	}

	/**
	 * ����posting_person��Setter����.��������posting_person ��������:2017-11-1
	 * 
	 * @param newPosting_person
	 *            java.lang.String
	 */
	public void setPosting_person(String posting_person) {
		this.posting_person = posting_person;
	}

	/**
	 * ���� sid��Getter����.��������sid ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getSid() {
		return this.sid;
	}

	/**
	 * ����sid��Setter����.��������sid ��������:2017-11-1
	 * 
	 * @param newSid
	 *            java.lang.String
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * ���� year��Getter����.��������year ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getYear() {
		return this.year;
	}

	/**
	 * ����year��Setter����.��������year ��������:2017-11-1
	 * 
	 * @param newYear
	 *            java.lang.String
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * ���� operdate��Getter����.��������operdate ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */

	/**
	 * ���� prepareddate��Getter����.��������prepareddate ��������:2017-11-1
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
	 * ����prepareddate��Setter����.��������prepareddate ��������:2017-11-1
	 * 
	 * @param newPrepareddate
	 *            java.lang.String
	 */
	public void setPrepareddate(String prepareddate) {
		this.prepareddate = prepareddate;
	}

	/**
	 * ���� summoney��Getter����.��������summoney ��������:2017-11-1
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */

	/**
	 * ���� pk_billid��Getter����.��������pk_billid ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_billid() {
		return this.pk_billid;
	}

	/**
	 * ����pk_billid��Setter����.��������pk_billid ��������:2017-11-1
	 * 
	 * @param newPk_billid
	 *            java.lang.String
	 */
	public void setPk_billid(String pk_billid) {
		this.pk_billid = pk_billid;
	}

	/**
	 * ���� pk_billtype��Getter����.��������pk_billtype ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_billtype() {
		return this.pk_billtype;
	}

	/**
	 * ����pk_billtype��Setter����.��������pk_billtype ��������:2017-11-1
	 * 
	 * @param newPk_billtype
	 *            java.lang.String
	 */
	public void setPk_billtype(String pk_billtype) {
		this.pk_billtype = pk_billtype;
	}

	/**
	 * ���� pk_busitype��Getter����.��������pk_busitype ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_busitype() {
		return this.pk_busitype;
	}

	/**
	 * ����pk_busitype��Setter����.��������pk_busitype ��������:2017-11-1
	 * 
	 * @param newPk_busitype
	 *            java.lang.String
	 */
	public void setPk_busitype(String pk_busitype) {
		this.pk_busitype = pk_busitype;
	}

	/**
	 * ���� pk_org��Getter����.��������pk_org ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_org() {
		return this.pk_org;
	}

	/**
	 * ����pk_org��Setter����.��������pk_org ��������:2017-11-1
	 * 
	 * @param newPk_org
	 *            java.lang.String
	 */
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	/**
	 * ���� pk_org_v��Getter����.��������pk_org_v ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_org_v() {
		return this.pk_org_v;
	}

	/**
	 * ����pk_org_v��Setter����.��������pk_org_v ��������:2017-11-1
	 * 
	 * @param newPk_org_v
	 *            java.lang.String
	 */
	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	/**
	 * ���� pk_group��Getter����.��������pk_group ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_group() {
		return this.pk_group;
	}

	/**
	 * ����pk_group��Setter����.��������pk_group ��������:2017-11-1
	 * 
	 * @param newPk_group
	 *            java.lang.String
	 */
	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	/**
	 * ���� ����ʱ�����Getter����.��������ʱ��� ��������:2017-11-1
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * ��������ʱ�����Setter����.��������ʱ��� ��������:2017-11-1
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
