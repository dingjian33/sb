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
	 * �ϲ㵥������
	 */
	public String pk_billid;
	/**
	 * ʱ���
	 */
	public UFDateTime ts;

	/**
	 * ���� accname��Getter����.��������accname ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getAccname() {
		return this.accname;
	}

	/**
	 * ����accname��Setter����.��������accname ��������:2017-11-1
	 * 
	 * @param newAccname
	 *            java.lang.String
	 */
	public void setAccname(String accname) {
		this.accname = accname;
	}

	/**
	 * ���� accno��Getter����.��������accno ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getAccno() {
		return this.accno;
	}

	/**
	 * ����accno��Setter����.��������accno ��������:2017-11-1
	 * 
	 * @param newAccno
	 *            java.lang.String
	 */
	public void setAccno(String accno) {
		this.accno = accno;
	}

	/**
	 * ���� account_code��Getter����.��������account_code ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getAccount_code() {
		return this.account_code;
	}

	/**
	 * ����account_code��Setter����.��������account_code ��������:2017-11-1
	 * 
	 * @param newAccount_code
	 *            java.lang.String
	 */
	public void setAccount_code(String account_code) {
		this.account_code = account_code;
	}

	/**
	 * ���� bys3��Getter����.��������bys3 ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getBys3() {
		return this.bys3;
	}

	/**
	 * ����bys3��Setter����.��������bys3 ��������:2017-11-1
	 * 
	 * @param newBys3
	 *            java.lang.String
	 */
	public void setBys3(String bys3) {
		this.bys3 = bys3;
	}

	/**
	 * ���� direct��Getter����.��������direct ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getDirect() {
		return this.direct;
	}

	/**
	 * ����direct��Setter����.��������direct ��������:2017-11-1
	 * 
	 * @param newDirect
	 *            java.lang.String
	 */
	public void setDirect(String direct) {
		this.direct = direct;
	}

	/**
	 * ���� enpname��Getter����.��������enpname ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getEnpname() {
		return this.enpname;
	}

	/**
	 * ����enpname��Setter����.��������enpname ��������:2017-11-1
	 * 
	 * @param newEnpname
	 *            java.lang.String
	 */
	public void setEnpname(String enpname) {
		this.enpname = enpname;
	}

	/**
	 * ���� enpno��Getter����.��������enpno ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getEnpno() {
		return this.enpno;
	}

	/**
	 * ����enpno��Setter����.��������enpno ��������:2017-11-1
	 * 
	 * @param newEnpno
	 *            java.lang.String
	 */
	public void setEnpno(String enpno) {
		this.enpno = enpno;
	}

	/**
	 * ���� enpprop��Getter����.��������enpprop ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getEnpprop() {
		return this.enpprop;
	}

	/**
	 * ����enpprop��Setter����.��������enpprop ��������:2017-11-1
	 * 
	 * @param newEnpprop
	 *            java.lang.String
	 */
	public void setEnpprop(String enpprop) {
		this.enpprop = enpprop;
	}

	/**
	 * ���� enppropname��Getter����.��������enppropname ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getEnppropname() {
		return this.enppropname;
	}

	/**
	 * ����enppropname��Setter����.��������enppropname ��������:2017-11-1
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
	 * ���� remark��Getter����.��������remark ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ����remark��Setter����.��������remark ��������:2017-11-1
	 * 
	 * @param newRemark
	 *            java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * ���� sidseq��Getter����.��������sidseq ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getSidseq() {
		return this.sidseq;
	}

	/**
	 * ����sidseq��Setter����.��������sidseq ��������:2017-11-1
	 * 
	 * @param newSidseq
	 *            java.lang.String
	 */
	public void setSidseq(String sidseq) {
		this.sidseq = sidseq;
	}

	/**
	 * ���� pk_lineid��Getter����.��������pk_lineid ��������:2017-11-1
	 * 
	 * @return java.lang.String
	 */
	public String getPk_lineid() {
		return this.pk_lineid;
	}

	/**
	 * ����pk_lineid��Setter����.��������pk_lineid ��������:2017-11-1
	 * 
	 * @param newPk_lineid
	 *            java.lang.String
	 */
	public void setPk_lineid(String pk_lineid) {
		this.pk_lineid = pk_lineid;
	}

	/**
	 * ���� �����ϲ�������Getter����.���������ϲ����� ��������:2017-11-1
	 * 
	 * @return String
	 */
	public String getPk_billid() {
		return this.pk_billid;
	}

	/**
	 * ���������ϲ�������Setter����.���������ϲ����� ��������:2017-11-1
	 * 
	 * @param newPk_billid
	 *            String
	 */
	public void setPk_billid(String pk_billid) {
		this.pk_billid = pk_billid;
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

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("arap.YBNCBillBVO");
	}
}
