package service;

import java.util.List;
import domain.Transaction;

public interface TransactionService {
public List<Transaction> findAll();
	
	public Transaction find(Integer CUSTID);
	
	public List<Transaction> findByName(String TRANSDESC, String CUSTID);
	
	public void add(Transaction Transaction);
	
	public void upsert(Transaction Transaction);
	

}
