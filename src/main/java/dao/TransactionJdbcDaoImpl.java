package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dao.TransactionDao;
import domain.Transaction;

public class TransactionJdbcDaoImpl implements TransactionDao{
	private static TransactionJdbcDaoImpl INSTANCE;
	private String url = "jdbc:mysql://localhost:3306/mydb1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";
	Connection con = null;
	PreparedStatement stmt = null;

	static public TransactionJdbcDaoImpl getInstance() {

		TransactionJdbcDaoImpl instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new TransactionJdbcDaoImpl();
			INSTANCE = instance;
		}

		return instance;
	}

	private TransactionJdbcDaoImpl() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return findByName(null, null);

	}

	public Transaction find(Integer TRANSID) {
		// TODO Auto-generated method stub
		Transaction Transaction = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (TRANSID != null) {
			String sql = "SELECT TRANSID, TRANSDESC, POINTSAMT, TRANSDATE, CUSTID FROM TRANSACTIONTBL where TRANSID = ?";
			try (Connection con = DriverManager.getConnection(url, user, password);
					PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setInt(1, TRANSID.intValue());
				ResultSet results = ps.executeQuery();

				if (results.next()) {
					Transaction = new Transaction(results.getInt("TRANSID"), results.getString("TRANSDESC"),
							results.getFloat("POINTSAMT"), results.getDate("TRANSDATE"), results.getString("CUSTID"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return Transaction;
	}

	public List<Transaction> findByName(String TRANSDESC, String CUSTID) {
		// TODO Auto-generated method stub
		List<Transaction> Transaction = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		 String sql = "SELECT * FROM TRANSACTIONTBL WHERE TRANSDESC LIKE ? AND CUSTID LIKE ?";
		// LIKE ? AND LNAME LIKE ?";
		//String sql = "SELECT * FROM transactiontbl";

		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = con.prepareStatement(sql)) {

			 ps.setString(1, createSearchValue(TRANSDESC));
			 ps.setString(2, createSearchValue(CUSTID));
			
			//Statement stmt = con.createStatement();
			//ResultSet results = stmt.executeQuery(sql);

			 ResultSet results = ps.executeQuery();

			while (results.next()) {
				Transaction Transaction1 = new Transaction(results.getInt("TRANSID"), results.getString("TRANSDESC"),
						results.getFloat("POINTSAMT"), results.getDate("TRANSDATE"), results.getString("CUSTID"));
				Transaction.add(Transaction1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return Transaction;

	}

	private String createSearchValue(String string) {

		String value;

		if (StringUtils.isBlank(string)) {
			value = "%";
		} else {
			value = string;
		}

		return value;
	}

	public void add(Transaction Transaction) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "INSERT INTO TRANSACTIONTBL (TRANSDESC, POINTSAMT, TRANSDATE, CUSTID) VALUES (?, ?, ?, ?)";
		
        System.out.println(sql);
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, Transaction.getTRANSDESC());
			ps.setFloat(2, Transaction.getPOINTSAMT());
			ps.setDate(3, Transaction.getTRANSDATE());
			ps.setString(4, Transaction.getCUSTID());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void update(Transaction Transaction) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "UPDATE TRANSACTIONTBL SET TRANSDESC = ?, POINTSAMT = ?, TRANSDATE = ?, CUSTID = ? WHERE TRANSID = ?";

		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, Transaction.getTRANSDESC());
			ps.setFloat(2, Transaction.getPOINTSAMT());
			ps.setDate(3, Transaction.getTRANSDATE());
			ps.setString(4, Transaction.getCUSTID());
			ps.setInt(5, Transaction.getTRANSID());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	
}
