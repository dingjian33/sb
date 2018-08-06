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
 * 此处添加累的描述信息
 * </p>
 * 创建日期:2017-10-26
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
	 * 上层单据主键
	 */
	public String pk_billid;
	/**
	 * 时间戳
	 */
	public UFDateTime ts;

	/**
	 * 属性 sid的Getter方法.属性名：sid 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getSid() {
		return this.sid;
	}

	/**
	 * 属性sid的Setter方法.属性名：sid 创建日期:2017-10-26
	 * 
	 * @param newSid
	 *            java.lang.String
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * 属性 accno的Getter方法.属性名：accno 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getAccno() {
		return this.accno;
	}

	/**
	 * 属性accno的Setter方法.属性名：accno 创建日期:2017-10-26
	 * 
	 * @param newAccno
	 *            java.lang.String
	 */
	public void setAccno(String accno) {
		this.accno = accno;
	}

	/**
	 * 属性 bank的Getter方法.属性名：bank 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getBank() {
		return this.bank;
	}

	/**
	 * 属性bank的Setter方法.属性名：bank 创建日期:2017-10-26
	 * 
	 * @param newBank
	 *            java.lang.String
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * 属性 bankacc的Getter方法.属性名：bankacc 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getBankacc() {
		return this.bankacc;
	}

	/**
	 * 属性bankacc的Setter方法.属性名：bankacc 创建日期:2017-10-26
	 * 
	 * @param newBankacc
	 *            java.lang.String
	 */
	public void setBankacc(String bankacc) {
		this.bankacc = bankacc;
	}

	/**
	 * 属性 billno的Getter方法.属性名：billno 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getBillno() {
		return this.billno;
	}

	/**
	 * 属性billno的Setter方法.属性名：billno 创建日期:2017-10-26
	 * 
	 * @param newBillno
	 *            java.lang.String
	 */
	public void setBillno(String billno) {
		this.billno = billno;
	}

	/**
	 * 属性 dataprimaryid的Getter方法.属性名：dataprimaryid 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getDataprimaryid() {
		return this.dataprimaryid;
	}

	/**
	 * 属性dataprimaryid的Setter方法.属性名：dataprimaryid 创建日期:2017-10-26
	 * 
	 * @param newDataprimaryid
	 *            java.lang.String
	 */
	public void setDataprimaryid(String dataprimaryid) {
		this.dataprimaryid = dataprimaryid;
	}

	/**
	 * 属性 datasource的Getter方法.属性名：datasource 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getDatasource() {
		return this.datasource;
	}

	/**
	 * 属性datasource的Setter方法.属性名：datasource 创建日期:2017-10-26
	 * 
	 * @param newDatasource
	 *            java.lang.String
	 */
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	/**
	 * 属性 deptname的Getter方法.属性名：deptname 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getDeptname() {
		return this.deptname;
	}

	/**
	 * 属性deptname的Setter方法.属性名：deptname 创建日期:2017-10-26
	 * 
	 * @param newDeptname
	 *            java.lang.String
	 */
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	/**
	 * 属性 deptno的Getter方法.属性名：deptno 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getDeptno() {
		return this.deptno;
	}

	/**
	 * 属性deptno的Setter方法.属性名：deptno 创建日期:2017-10-26
	 * 
	 * @param newDeptno
	 *            java.lang.String
	 */
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	/**
	 * 属性 direction的Getter方法.属性名：direction 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getDirection() {
		return this.direction;
	}

	/**
	 * 属性direction的Setter方法.属性名：direction 创建日期:2017-10-26
	 * 
	 * @param newDirection
	 *            java.lang.String
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * 属性 enpprop的Getter方法.属性名：enpprop 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getEnpprop() {
		return this.enpprop;
	}

	/**
	 * 属性enpprop的Setter方法.属性名：enpprop 创建日期:2017-10-26
	 * 
	 * @param newEnpprop
	 *            java.lang.String
	 */
	public void setEnpprop(String enpprop) {
		this.enpprop = enpprop;
	}

	/**
	 * 属性 feeitem的Getter方法.属性名：feeitem 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getFeeitem() {
		return this.feeitem;
	}

	/**
	 * 属性feeitem的Setter方法.属性名：feeitem 创建日期:2017-10-26
	 * 
	 * @param newFeeitem
	 *            java.lang.String
	 */
	public void setFeeitem(String feeitem) {
		this.feeitem = feeitem;
	}

	/**
	 * 属性 feeitemname的Getter方法.属性名：feeitemname 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getFeeitemname() {
		return this.feeitemname;
	}

	/**
	 * 属性feeitemname的Setter方法.属性名：feeitemname 创建日期:2017-10-26
	 * 
	 * @param newFeeitemname
	 *            java.lang.String
	 */
	public void setFeeitemname(String feeitemname) {
		this.feeitemname = feeitemname;
	}

	/**
	 * 属性 flag的Getter方法.属性名：flag 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getFlag() {
		return this.flag;
	}

	/**
	 * 属性flag的Setter方法.属性名：flag 创建日期:2017-10-26
	 * 
	 * @param newFlag
	 *            java.lang.String
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 属性 fund的Getter方法.属性名：fund 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */


	/**
	 * 属性 issue的Getter方法.属性名：issue 创建日期:2017-10-26
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
	 * 属性issue的Setter方法.属性名：issue 创建日期:2017-10-26
	 * 
	 * @param newIssue
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setIssue(String issue) {
		this.issue = issue;
	}

	/**
	 * 属性 nc_error的Getter方法.属性名：nc_error 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getNc_error() {
		return this.nc_error;
	}

	/**
	 * 属性nc_error的Setter方法.属性名：nc_error 创建日期:2017-10-26
	 * 
	 * @param newNc_error
	 *            java.lang.String
	 */
	public void setNc_error(String nc_error) {
		this.nc_error = nc_error;
	}

	/**
	 * 属性 nc_voucher的Getter方法.属性名：nc_voucher 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getNc_voucher() {
		return this.nc_voucher;
	}

	/**
	 * 属性nc_voucher的Setter方法.属性名：nc_voucher 创建日期:2017-10-26
	 * 
	 * @param newNc_voucher
	 *            java.lang.String
	 */
	public void setNc_voucher(String nc_voucher) {
		this.nc_voucher = nc_voucher;
	}

	/**
	 * 属性 operator的Getter方法.属性名：operator 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getOperator() {
		return this.operator;
	}

	/**
	 * 属性operator的Setter方法.属性名：operator 创建日期:2017-10-26
	 * 
	 * @param newOperator
	 *            java.lang.String
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 属性 operatorid的Getter方法.属性名：operatorid 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getOperatorid() {
		return this.operatorid;
	}

	/**
	 * 属性operatorid的Setter方法.属性名：operatorid 创建日期:2017-10-26
	 * 
	 * @param newOperatorid
	 *            java.lang.String
	 */
	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	/**
	 * 属性 opertype的Getter方法.属性名：opertype 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getOpertype() {
		return this.opertype;
	}

	/**
	 * 属性opertype的Setter方法.属性名：opertype 创建日期:2017-10-26
	 * 
	 * @param newOpertype
	 *            java.lang.String
	 */
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}

	/**
	 * 属性 opertypename的Getter方法.属性名：opertypename 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getOpertypename() {
		return this.opertypename;
	}

	/**
	 * 属性opertypename的Setter方法.属性名：opertypename 创建日期:2017-10-26
	 * 
	 * @param newOpertypename
	 *            java.lang.String
	 */
	public void setOpertypename(String opertypename) {
		this.opertypename = opertypename;
	}

	/**
	 * 属性 remark的Getter方法.属性名：remark 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 属性remark的Setter方法.属性名：remark 创建日期:2017-10-26
	 * 
	 * @param newRemark
	 *            java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 属性 reserve1的Getter方法.属性名：reserve1 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getReserve1() {
		return this.reserve1;
	}

	/**
	 * 属性reserve1的Setter方法.属性名：reserve1 创建日期:2017-10-26
	 * 
	 * @param newReserve1
	 *            java.lang.String
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	/**
	 * 属性 reserve2的Getter方法.属性名：reserve2 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getReserve2() {
		return this.reserve2;
	}

	/**
	 * 属性reserve2的Setter方法.属性名：reserve2 创建日期:2017-10-26
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
	 * 属性 dcreatedate的Getter方法.属性名：dcreatedate 创建日期:2017-10-26
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public UFDate getDcreatedate() {
		return this.dcreatedate;
	}

	/**
	 * 属性dcreatedate的Setter方法.属性名：dcreatedate 创建日期:2017-10-26
	 * 
	 * @param newDcreatedate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setDcreatedate(UFDate dcreatedate) {
		this.dcreatedate = dcreatedate;
	}

	/**
	 * 属性 operatetime的Getter方法.属性名：operatetime 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getOperatetime() {
		return this.operatetime;
	}

	/**
	 * 属性operatetime的Setter方法.属性名：operatetime 创建日期:2017-10-26
	 * 
	 * @param newOperatetime
	 *            java.lang.String
	 */
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

	/**
	 * 属性 reserve4的Getter方法.属性名：reserve4 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getReserve4() {
		return this.reserve4;
	}

	/**
	 * 属性reserve4的Setter方法.属性名：reserve4 创建日期:2017-10-26
	 * 
	 * @param newReserve4
	 *            java.lang.String
	 */
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	/**
	 * 属性 reserve5的Getter方法.属性名：reserve5 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getReserve5() {
		return this.reserve5;
	}

	/**
	 * 属性reserve5的Setter方法.属性名：reserve5 创建日期:2017-10-26
	 * 
	 * @param newReserve5
	 *            java.lang.String
	 */
	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	/**
	 * 属性 tablesource的Getter方法.属性名：tablesource 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getTablesource() {
		return this.tablesource;
	}

	/**
	 * 属性tablesource的Setter方法.属性名：tablesource 创建日期:2017-10-26
	 * 
	 * @param newTablesource
	 *            java.lang.String
	 */
	public void setTablesource(String tablesource) {
		this.tablesource = tablesource;
	}

	/**
	 * 属性 pk_billtype的Getter方法.属性名：pk_billtype 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_billtype() {
		return this.pk_billtype;
	}

	/**
	 * 属性pk_billtype的Setter方法.属性名：pk_billtype 创建日期:2017-10-26
	 * 
	 * @param newPk_billtype
	 *            java.lang.String
	 */
	public void setPk_billtype(String pk_billtype) {
		this.pk_billtype = pk_billtype;
	}

	/**
	 * 属性 pk_busitype的Getter方法.属性名：pk_busitype 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_busitype() {
		return this.pk_busitype;
	}

	/**
	 * 属性pk_busitype的Setter方法.属性名：pk_busitype 创建日期:2017-10-26
	 * 
	 * @param newPk_busitype
	 *            java.lang.String
	 */
	public void setPk_busitype(String pk_busitype) {
		this.pk_busitype = pk_busitype;
	}

	/**
	 * 属性 pk_org的Getter方法.属性名：pk_org 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_org() {
		return this.pk_org;
	}

	/**
	 * 属性pk_org的Setter方法.属性名：pk_org 创建日期:2017-10-26
	 * 
	 * @param newPk_org
	 *            java.lang.String
	 */
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	/**
	 * 属性 pk_userid的Getter方法.属性名：pk_userid 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_userid() {
		return this.pk_userid;
	}

	/**
	 * 属性pk_userid的Setter方法.属性名：pk_userid 创建日期:2017-10-26
	 * 
	 * @param newPk_userid
	 *            java.lang.String
	 */
	public void setPk_userid(String pk_userid) {
		this.pk_userid = pk_userid;
	}

	/**
	 * 属性 pk_org_v的Getter方法.属性名：pk_org_v 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_org_v() {
		return this.pk_org_v;
	}

	/**
	 * 属性pk_org_v的Setter方法.属性名：pk_org_v 创建日期:2017-10-26
	 * 
	 * @param newPk_org_v
	 *            java.lang.String
	 */
	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	/**
	 * 属性 pk_group的Getter方法.属性名：pk_group 创建日期:2017-10-26
	 * 
	 * @return java.lang.String
	 */
	public String getPk_group() {
		return this.pk_group;
	}

	/**
	 * 属性pk_group的Setter方法.属性名：pk_group 创建日期:2017-10-26
	 * 
	 * @param newPk_group
	 *            java.lang.String
	 */
	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	/**
	 * 属性 生成上层主键的Getter方法.属性名：上层主键 创建日期:2017-10-26
	 * 
	 * @return String
	 */
	public String getPk_billid() {
		return this.pk_billid;
	}

	/**
	 * 属性生成上层主键的Setter方法.属性名：上层主键 创建日期:2017-10-26
	 * 
	 * @param newPk_billid
	 *            String
	 */
	public void setPk_billid(String pk_billid) {
		this.pk_billid = pk_billid;
	}

	/**
	 * 属性 生成时间戳的Getter方法.属性名：时间戳 创建日期:2017-10-26
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public UFDateTime getTs() {
		return this.ts;
	}

	/**
	 * 属性生成时间戳的Setter方法.属性名：时间戳 创建日期:2017-10-26
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
