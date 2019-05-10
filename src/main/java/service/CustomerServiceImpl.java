package service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dao.CustomerDao;
import dao.CustomerJdbcDaoImpl;
import domain.Customer;

public class CustomerServiceImpl implements CustomerService {
	CustomerDao CustomerDao;

	public CustomerServiceImpl() {
		this.CustomerDao = CustomerJdbcDaoImpl.getInstance();
		//this.userDao = UserHashMapDaoImpl.getInstance();
	}

	@Override
	public List<Customer> findAll() {
		return CustomerDao.findAll();
	}

	@Override
	public Customer find(Integer CUSTID) {
		return CustomerDao.find(CUSTID);
	}

	@Override
	public List<Customer> findByName(String FNAME, String MNAME, String LNAME) {
		return CustomerDao.findByName(FNAME, MNAME, LNAME);
	}

	@Override
	public void add(Customer Customer) {
		if (validate(Customer)) {
			CustomerDao.add(Customer);
		} else {
			throw new IllegalArgumentException("Fields firstName, middlename, lastName, gender, birthdate, expdate, and totalbal cannot be blank.");
		}
	}

	@Override
	public void upsert(Customer customer) {
		if (validate(customer)) {
			if (customer.getCUSTID() != null && customer.getCUSTID() >= 0) {
				CustomerDao.update(customer);
			} else {
				CustomerDao.add(customer);
			}
		} else {
			throw new IllegalArgumentException("Fields firstName, middlename, lastName, gender, birthdate, expdate, and totalbal cannot be blank.");
		}
	}
	
	@Override
	public void updateBal(Customer customer) {
		System.out.println("hi");
		if (validate(customer)) {
			if (customer.getCUSTID() != null && customer.getCUSTID() >= 0) {
				CustomerDao.update(customer);
			}
			
		}
	}
	
	@Override
	public void updateBal1(Customer customer) {
		if (validate(customer)) {
			if (customer.getCUSTID() != null && customer.getCUSTID() >= 0) {
				CustomerDao.update(customer);
			}
		}
	}

	

	private boolean validate(Customer Customer) {
		return !StringUtils.isAnyBlank(Customer.getFNAME(), Customer.getMNAME(), Customer.getFNAME());
	}

}
