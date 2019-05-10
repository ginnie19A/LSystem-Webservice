package domain;

import java.sql.Date;

public class Customer {
	
	Integer CUSTID;
	private String FNAME;
	private String MNAME;
	private String LNAME;
	private String GENDER;
	private Date BIRTHDATE;
	private Date EXPDATE;
	private Float TOTALBAL;
	
	public Customer() {
		
	}
	
	public Customer(String FNAME, String MNAME, String LNAME, String GENDER, Date BIRTHDATE, Date EXPDATE, Float TOTALBAL ) {
		this(null, FNAME, MNAME, LNAME, GENDER, BIRTHDATE, EXPDATE, TOTALBAL);
	}

	public Customer(Integer CUSTID, String FNAME, String MNAME, String LNAME , String GENDER, Date BIRTHDATE, Date EXPDATE, Float TOTALBAL) {
		this.CUSTID = CUSTID;
		this.FNAME = FNAME;
		this.MNAME = MNAME;
		this.LNAME = LNAME;
		this.GENDER = GENDER;
		this.BIRTHDATE = BIRTHDATE;
		this.EXPDATE = EXPDATE;
		this.TOTALBAL = TOTALBAL;
	}
	
	
	public Float getTOTALBAL() {
		return TOTALBAL;
	}

	public void setTOTALBAL(Float TOTALBAL) {
		this.TOTALBAL = TOTALBAL;
	}

	public Integer getCUSTID() {
		return CUSTID;
	}

	public void setCUSTID(Integer CUSTID) {
		this.CUSTID = CUSTID;
	}

	public String getFNAME() {
		return FNAME;
	}
	
	public void setFNAME(String FNAME) {
		this.FNAME = FNAME;
	}
	public String getMNAME() {
		return MNAME;
	}
	
	public void setMNAME(String MNAME) {
		this.MNAME = MNAME;
	}
	public String getLNAME() {
		return LNAME;
	}
	public void setLNAME(String LNAME) {
		this.LNAME = LNAME;
	}
	
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String GENDER) {
		this.GENDER = GENDER;
	}
	public Date getBIRTHDATE() {
		return BIRTHDATE;
	}
	public void setBIRTHDATE(Date BIRTHDATE) {
		this.BIRTHDATE = BIRTHDATE;
	}
	public Date getEXPDATE() {
		return EXPDATE;
	}
	public void setEXPDATE(Date EXPDATE) {
		this.EXPDATE = EXPDATE;
	}


}
