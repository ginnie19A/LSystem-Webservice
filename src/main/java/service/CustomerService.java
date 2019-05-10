package service;


import java.util.List;

import domain.Customer;

public interface CustomerService {

public List<Customer> findAll();
	
	public Customer find(Integer CUSTID);
	
	public List<Customer> findByName(String FNAME, String MNAME, String LNAME);
	
	public void add(Customer Customer);
	
	public void upsert(Customer Customer);
	
	public void updateBal(Customer Customer);
	
	public void updateBal1(Customer Customer);
	
	
}
