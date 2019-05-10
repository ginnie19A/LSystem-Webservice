package dao;

import java.util.List;
import domain.Transaction;

public interface TransactionDao {
public List<Transaction> findAll();
	
	public Transaction find(Integer TRANSID);
	
	public List<Transaction> findByName(String TRANSDESC, String CUSTID);
	
	public void add(Transaction Transaction);
	
	public void update(Transaction Transaction);
	
	
	

}
