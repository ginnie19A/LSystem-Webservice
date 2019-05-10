package dao;


import java.util.List;

import domain.Customer;

public interface CustomerDao {
	
public List<Customer> findAll();
	
	public Customer find(Integer CUSTID);
	
	public List<Customer> findByName(String FNAME, String MNAME, String LNAME);
	
	public void add(Customer Customer);
	
	public void update(Customer Customer);
	
	public void updateBal(Customer Customer);
	
	public void updateBal1(Customer Customer);
	
	
}
