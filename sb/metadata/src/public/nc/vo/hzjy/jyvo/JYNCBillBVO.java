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
 * �˴�����۵�������Ϣ
 * </p>
 * ��������:2017-10-26
 * 
 * @author YONYOU NC
 * @version NCPrj ??
 */

public class JYNCBillBVO extends SuperVO {

	/**
	 * sid
	 */
	public String sid;
	/**
	 * accno
	 */
	public String accno;
	/**
	 * bank
	 */
	public String bank;
	/**
	 * bankacc
	 */
	public String bankacc;
	/**
	 * billno
	 */
	public String billno;
	/**
	 * dataprimaryid
	 */
	public String dataprimaryid;
	/**
	 * datasource
	 */
	public String datasource;
	/**
	 * deptname
	 */
	public String deptname;
	/**
	 * deptno
	 */
	public String deptno;
	/**
	 * direction
	 */
	public String direction;
	/**
	 * enpprop
	 */
	public String enpprop;
	/**
	 * feeitem
	 */
	public String feeitem;
	/**
	 * feeitemname
	 */
	public String feeitemname;
	/**
	 * flag
	 */
	public String flag;

	public int dr;
	/**
	 * fund
	 */
	public UFDouble fund;
	/**
	 * issue
	 */
	public String issue;
	/**
	 * nc_error
	 */
	public String nc_error;
	/**
	 * nc_voucher
	 */
	public String nc_voucher;
	/**
	 * operator
	 */
	public String operator;
	/**
	 * operatorid
	 */
	public String operatorid;
	/**
	 * opertype
	 */
	public String opertype;
	/**
	 * opertypename
	 */
	public String opertypename;
	/**
	 * remark
	 */
	public String remark;
	/**
	 * reserve1
	 */
	public String reserve1;
	/**
	 * reserve2
	 */
	public String reserve2;
	/**
	 * reserve3
	 */
	public UFDouble reserve3;
	/**
	 * dcreatedate
	 */
	public UFDate dcreatedate;
	/**
	 * operatetime
	 */
	public String operatetime;
	/**
	 * nfun
	 */
	public UFDouble nfun;
	/**
	 * reserve4
	 */
	public String reserve4;
	/**
	 * reserve5
	 */
	public String reserve5;
	/**
	 * tablesource
	 */
	public String tablesource;
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
	 * pk_userid
	 */
	public String pk_userid;
	/**
	 * pk_org_v
	 */
	public String pk_org_v;
	/**
	 * pk_group
	 */
	public String pk_group;
	/**
	 * �ϲ㵥������
	 */
	public String pk_billid;
	/**
	 * ʱ���
	 */
	public UFDateTime ts;

	/**
	 * ���� sid��Getter����.��������sid ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getSid() {
		return this.sid;
	}

	/**
	 * ����sid��Setter����.��������sid ��������:2017-10-26
	 * 
	 * @param newSid
	 *            java.lang.String
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * ���� accno��Getter����.��������accno ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getAccno() {
		return this.accno;
	}

	/**
	 * ����accno��Setter����.��������accno ��������:2017-10-26
	 * 
	 * @param newAccno
	 *            java.lang.String
	 */
	public void setAccno(String accno) {
		this.accno = accno;
	}

	/**
	 * ���� bank��Getter����.��������bank ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getBank() {
		return this.bank;
	}

	/**
	 * ����bank��Setter����.��������bank ��������:2017-10-26
	 * 
	 * @param newBank
	 *            java.lang.String
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * ���� bankacc��Getter����.��������bankacc ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getBankacc() {
		return this.bankacc;
	}

	/**
	 * ����bankacc��Setter����.��������bankacc ��������:2017-10-26
	 * 
	 * @param newBankacc
	 *            java.lang.String
	 */
	public void setBankacc(String bankacc) {
		this.bankacc = bankacc;
	}

	/**
	 * ���� billno��Getter����.��������billno ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getBillno() {
		return this.billno;
	}

	/**
	 * ����billno��Setter����.��������billno ��������:2017-10-26
	 * 
	 * @param newBillno
	 *            java.lang.String
	 */
	public void setBillno(String billno) {
		this.billno = billno;
	}

	/**
	 * ���� dataprimaryid��Getter����.��������dataprimaryid ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getDataprimaryid() {
		return this.dataprimaryid;
	}

	/**
	 * ����dataprimaryid��Setter����.��������dataprimaryid ��������:2017-10-26
	 * 
	 * @param newDataprimaryid
	 *            java.lang.String
	 */
	public void setDataprimaryid(String dataprimaryid) {
		this.dataprimaryid = dataprimaryid;
	}

	/**
	 * ���� datasource��Getter����.��������datasource ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getDatasource() {
		return this.datasource;
	}

	/**
	 * ����datasource��Setter����.��������datasource ��������:2017-10-26
	 * 
	 * @param newDatasource
	 *            java.lang.String
	 */
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	/**
	 * ���� deptname��Getter����.��������deptname ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getDeptname() {
		return this.deptname;
	}

	/**
	 * ����deptname��Setter����.��������deptname ��������:2017-10-26
	 * 
	 * @param newDeptname
	 *            java.lang.String
	 */
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	/**
	 * ���� deptno��Getter����.��������deptno ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getDeptno() {
		return this.deptno;
	}

	/**
	 * ����deptno��Setter����.��������deptno ��������:2017-10-26
	 * 
	 * @param newDeptno
	 *            java.lang.String
	 */
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	/**
	 * ���� direction��Getter����.��������direction ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getDirection() {
		return this.direction;
	}

	/**
	 * ����direction��Setter����.��������direction ��������:2017-10-26
	 * 
	 * @param newDirection
	 *            java.lang.String
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * ���� enpprop��Getter����.��������enpprop ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getEnpprop() {
		return this.enpprop;
	}

	/**
	 * ����enpprop��Setter����.��������enpprop ��������:2017-10-26
	 * 
	 * @param newEnpprop
	 *            java.lang.String
	 */
	public void setEnpprop(String enpprop) {
		this.enpprop = enpprop;
	}

	/**
	 * ���� feeitem��Getter����.��������feeitem ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getFeeitem() {
		return this.feeitem;
	}

	/**
	 * ����feeitem��Setter����.��������feeitem ��������:2017-10-26
	 * 
	 * @param newFeeitem
	 *            java.lang.String
	 */
	public void setFeeitem(String feeitem) {
		this.feeitem = feeitem;
	}

	/**
	 * ���� feeitemname��Getter����.��������feeitemname ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getFeeitemname() {
		return this.feeitemname;
	}

	/**
	 * ����feeitemname��Setter����.��������feeitemname ��������:2017-10-26
	 * 
	 * @param newFeeitemname
	 *            java.lang.String
	 */
	public void setFeeitemname(String feeitemname) {
		this.feeitemname = feeitemname;
	}

	/**
	 * ���� flag��Getter����.��������flag ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getFlag() {
		return this.flag;
	}

	/**
	 * ����flag��Setter����.��������flag ��������:2017-10-26
	 * 
	 * @param newFlag
	 *            java.lang.String
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * ���� fund��Getter����.��������fund ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */


	/**
	 * ���� issue��Getter����.��������issue ��������:2017-10-26
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public String getIssue() {
		return this.issue;
	}

	public UFDouble getFund() {
		return fund;
	}

	public void setFund(UFDouble fund) {
		this.fund = fund;
	}

	/**
	 * ����issue��Setter����.��������issue ��������:2017-10-26
	 * 
	 * @param newIssue
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setIssue(String issue) {
		this.issue = issue;
	}

	/**
	 * ���� nc_error��Getter����.��������nc_error ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getNc_error() {
		return this.nc_error;
	}

	/**
	 * ����nc_error��Setter����.��������nc_error ��������:2017-10-26
	 * 
	 * @param newNc_error
	 *            java.lang.String
	 */
	public void setNc_error(String nc_error) {
		this.nc_error = nc_error;
	}

	/**
	 * ���� nc_voucher��Getter����.��������nc_voucher ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getNc_voucher() {
		return this.nc_voucher;
	}

	/**
	 * ����nc_voucher��Setter����.��������nc_voucher ��������:2017-10-26
	 * 
	 * @param newNc_voucher
	 *            java.lang.String
	 */
	public void setNc_voucher(String nc_voucher) {
		this.nc_voucher = nc_voucher;
	}

	/**
	 * ���� operator��Getter����.��������operator ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getOperator() {
		return this.operator;
	}

	/**
	 * ����operator��Setter����.��������operator ��������:2017-10-26
	 * 
	 * @param newOperator
	 *            java.lang.String
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * ���� operatorid��Getter����.��������operatorid ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getOperatorid() {
		return this.operatorid;
	}

	/**
	 * ����operatorid��Setter����.��������operatorid ��������:2017-10-26
	 * 
	 * @param newOperatorid
	 *            java.lang.String
	 */
	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	/**
	 * ���� opertype��Getter����.��������opertype ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getOpertype() {
		return this.opertype;
	}

	/**
	 * ����opertype��Setter����.��������opertype ��������:2017-10-26
	 * 
	 * @param newOpertype
	 *            java.lang.String
	 */
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}

	/**
	 * ���� opertypename��Getter����.��������opertypename ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getOpertypename() {
		return this.opertypename;
	}

	/**
	 * ����opertypename��Setter����.��������opertypename ��������:2017-10-26
	 * 
	 * @param newOpertypename
	 *            java.lang.String
	 */
	public void setOpertypename(String opertypename) {
		this.opertypename = opertypename;
	}

	/**
	 * ���� remark��Getter����.��������remark ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ����remark��Setter����.��������remark ��������:2017-10-26
	 * 
	 * @param newRemark
	 *            java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ���� reserve1��Getter����.��������reserve1 ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getReserve1() {
		return this.reserve1;
	}

	/**
	 * ����reserve1��Setter����.��������reserve1 ��������:2017-10-26
	 * 
	 * @param newReserve1
	 *            java.lang.String
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	/**
	 * ���� reserve2��Getter����.��������reserve2 ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getReserve2() {
		return this.reserve2;
	}

	/**
	 * ����reserve2��Setter����.��������reserve2 ��������:2017-10-26
	 * 
	 * @param newReserve2
	 *            java.lang.String
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public UFDouble getReserve3() {
		return reserve3;
	}

	public void setReserve3(UFDouble reserve3) {
		this.reserve3 = reserve3;
	}

	public UFDouble getNfun() {
		return nfun;
	}

	public void setNfun(UFDouble nfun) {
		this.nfun = nfun;
	}

	/**
	 * ���� dcreatedate��Getter����.��������dcreatedate ��������:2017-10-26
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public UFDate getDcreatedate() {
		return this.dcreatedate;
	}

	/**
	 * ����dcreatedate��Setter����.��������dcreatedate ��������:2017-10-26
	 * 
	 * @param newDcreatedate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setDcreatedate(UFDate dcreatedate) {
		this.dcreatedate = dcreatedate;
	}

	/**
	 * ���� operatetime��Getter����.��������operatetime ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getOperatetime() {
		return this.operatetime;
	}

	/**
	 * ����operatetime��Setter����.��������operatetime ��������:2017-10-26
	 * 
	 * @param newOperatetime
	 *            java.lang.String
	 */
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

	/**
	 * ���� reserve4��Getter����.��������reserve4 ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getReserve4() {
		return this.reserve4;
	}

	/**
	 * ����reserve4��Setter����.��������reserve4 ��������:2017-10-26
	 * 
	 * @param newReserve4
	 *            java.lang.String
	 */
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	/**
	 * ���� reserve5��Getter����.��������reserve5 ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getReserve5() {
		return this.reserve5;
	}

	/**
	 * ����reserve5��Setter����.��������reserve5 ��������:2017-10-26
	 * 
	 * @param newReserve5
	 *            java.lang.String
	 */
	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	/**
	 * ���� tablesource��Getter����.��������tablesource ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getTablesource() {
		return this.tablesource;
	}

	/**
	 * ����tablesource��Setter����.��������tablesource ��������:2017-10-26
	 * 
	 * @param newTablesource
	 *            java.lang.String
	 */
	public void setTablesource(String tablesource) {
		this.tablesource = tablesource;
	}

	/**
	 * ���� pk_billtype��Getter����.��������pk_billtype ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_billtype() {
		return this.pk_billtype;
	}

	/**
	 * ����pk_billtype��Setter����.��������pk_billtype ��������:2017-10-26
	 * 
	 * @param newPk_billtype
	 *            java.lang.String
	 */
	public void setPk_billtype(String pk_billtype) {
		this.pk_billtype = pk_billtype;
	}

	/**
	 * ���� pk_busitype��Getter����.��������pk_busitype ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_busitype() {
		return this.pk_busitype;
	}

	/**
	 * ����pk_busitype��Setter����.��������pk_busitype ��������:2017-10-26
	 * 
	 * @param newPk_busitype
	 *            java.lang.String
	 */
	public void setPk_busitype(String pk_busitype) {
		this.pk_busitype = pk_busitype;
	}

	/**
	 * ���� pk_org��Getter����.��������pk_org ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_org() {
		return this.pk_org;
	}

	/**
	 * ����pk_org��Setter����.��������pk_org ��������:2017-10-26
	 * 
	 * @param newPk_org
	 *            java.lang.String
	 */
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	/**
	 * ���� pk_userid��Getter����.��������pk_userid ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_userid() {
		return this.pk_userid;
	}

	/**
	 * ����pk_userid��Setter����.��������pk_userid ��������:2017-10-26
	 * 
	 * @param newPk_userid
	 *            java.lang.String
	 */
	public void setPk_userid(String pk_userid) {
		this.pk_userid = pk_userid;
	}

	/**
	 * ���� pk_org_v��Getter����.��������pk_org_v ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_org_v() {
		return this.pk_org_v;
	}

	/**
	 * ����pk_org_v��Setter����.��������pk_org_v ��������:2017-10-26
	 * 
	 * @param newPk_org_v
	 *            java.lang.String
	 */
	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	/**
	 * ���� pk_group��Getter����.��������pk_group ��������:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_group() {
		return this.pk_group;
	}

	/**
	 * ����pk_group��Setter����.��������pk_group ��������:2017-10-26
	 * 
	 * @param newPk_group
	 *            java.lang.String
	 */
	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	/**
	 * ���� �����ϲ�������Getter����.���������ϲ����� ��������:2017-10-26
	 * 
	 * @return String
	 */
	public String getPk_billid() {
		return this.pk_billid;
	}

	/**
	 * ���������ϲ�������Setter����.���������ϲ����� ��������:2017-10-26
	 * 
	 * @param newPk_billid
	 *            String
	 */
	public void setPk_billid(String pk_billid) {
		this.pk_billid = pk_billid;
	}

	/**
	 * ���� ����ʱ�����Getter����.��������ʱ��� ��������:2017-10-26
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * ��������ʱ�����Setter����.��������ʱ��� ��������:2017-10-26
	 * 
	 * @param newts
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}
	
	

	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("arap.JYNCBillBVO");
	}
}
