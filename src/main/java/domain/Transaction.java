package domain;

import java.sql.Date;

public class Transaction {

	Integer TRANSID;
	private String TRANSDESC;
	private Float POINTSAMT;
	private Date TRANSDATE;
	private String CUSTID;

    public Transaction() {
		
	}
    public Transaction(String TRANSDESC, Float POINTSAMT, Date TRANSDATE, String CUSTID) {
		this(null, TRANSDESC, POINTSAMT, TRANSDATE, CUSTID);
	}
    
    public Transaction(Integer TRANSID, String TRANSDESC, Float POINTSAMT, Date TRANSDATE, String CUSTID) {
		
		this.TRANSID = TRANSID;
		this.TRANSDESC = TRANSDESC;
		this.POINTSAMT = POINTSAMT;
		this.TRANSDATE = TRANSDATE;
		this.CUSTID = CUSTID;
	}
	public Integer getTRANSID() {
		return TRANSID;
	}
	public void setTRANSID(Integer TRANSID) {
		this.TRANSID = TRANSID;
	}
	public String getTRANSDESC() {
		return TRANSDESC;
	}
	public void setTRANSDESC(String TRANSDESC) {
		this.TRANSDESC = TRANSDESC;
	}
	public Float getPOINTSAMT() {
		return POINTSAMT;
	}
	public void setPOINTSAMT(Float POINTSAMT) {
		this.POINTSAMT = POINTSAMT;
	}
	public Date getTRANSDATE() {
		return TRANSDATE;
	}
	public void setTRANSDATE(Date TRANSDATE) {
		this.TRANSDATE = TRANSDATE;
	}
	public String getCUSTID() {
		return CUSTID;
	}
	public void setCUSTID(String CUSTID) {
		this.CUSTID = CUSTID;
	}

}
