package service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dao.TransactionDao;
import dao.TransactionJdbcDaoImpl;
import domain.Transaction;
public class TransactionServiceImpl implements TransactionService {
	TransactionDao TransactionDao;

	public TransactionServiceImpl() {
		this.TransactionDao = TransactionJdbcDaoImpl.getInstance();
		//this.userDao = UserHashMapDaoImpl.getInstance();
	}

	@Override
	public List<Transaction> findAll() {
		return TransactionDao.findAll();
	}

	@Override
	public Transaction find(Integer TRANSID) {
		return TransactionDao.find(TRANSID);
	}

	@Override
	public List<Transaction> findByName(String TRANSDESC, String CUSTID) {
		return TransactionDao.findByName(TRANSDESC, CUSTID);
	}

	public void add(Transaction Transaction) {
		if (validate(Transaction)) {
			TransactionDao.add(Transaction);
		} else {
			throw new IllegalArgumentException("Fields TRANSDESC, POINTSAMT, TRANSDATE, and CUSTID cannot be blank.");
		}
	}

	public void upsert(Transaction Transaction) {
		if (validate(Transaction)) {
			if (Transaction.getTRANSID() != null && Transaction.getTRANSID() >= 0) {
				TransactionDao.update(Transaction);
			} else {
				TransactionDao.add(Transaction);
			}
		} else {
			throw new IllegalArgumentException("Fields TRANSDESC, POINTSAMT, TRANSDATE, and CUSTID cannot be blank.");
		}
	}

	private boolean validate(Transaction Transaction) {
		return !StringUtils.isAnyBlank(Transaction.getTRANSDESC(), Transaction.getCUSTID());
		
	}

	

}
