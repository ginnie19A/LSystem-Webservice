package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import org.apache.commons.lang3.StringUtils;

import dao.CustomerDao;
import domain.Customer;

public class CustomerJdbcDaoImpl implements CustomerDao {
	private static CustomerJdbcDaoImpl INSTANCE;
	private String url = "jdbc:mysql://localhost:3306/mydb1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";
	Connection con = null;
	PreparedStatement stmt = null;
	static public CustomerJdbcDaoImpl getInstance() {

		CustomerJdbcDaoImpl instance;
		if (INSTANCE != null) {
			instance = INSTANCE;
		} else {
			instance = new CustomerJdbcDaoImpl();
			INSTANCE = instance;
		}

		return instance;
	}

	private CustomerJdbcDaoImpl() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return findByName(null, null, null);

	}

	@Override
	public Customer find(Integer CUSTID) {
		// TODO Auto-generated method stub
		Customer Customer = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}

		if (CUSTID != null) {
			String sql = "SELECT CUSTID, FNAME, MNAME, LNAME, GENDER, BIRTHDATE, EXPDATE FROM CUSTOMERTBL where CUSTID = ?";
			try (Connection con = DriverManager.getConnection(url, user, password);
					PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setInt(1, CUSTID.intValue());
				ResultSet results = ps.executeQuery();

				if (results.next()) {
					Customer = new Customer(results.getInt("CUSTID"), results.getString("FNAME"),
							results.getString("MNAME"), results.getString("LNAME"), results.getString("GENDER"),
							results.getDate("BIRTHDATE"), results.getDate("EXPDATE"),results.getFloat("TOTALBAL"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		return Customer;
	}

	@Override
	public List<Customer> findByName(String FNAME, String MNAME, String LNAME) {
		// TODO Auto-generated method stub
		List<Customer> Customer = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}

		String sql = "SELECT * FROM CUSTOMERTBL WHERE FNAME LIKE ? AND MNAME LIKE ? AND LNAME LIKE ?";
//		String sql = "SELECT * FROM customertbl";

		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = con.prepareStatement(sql)) {

       ps.setString(1, createSearchValue(FNAME));
       ps.setString(2, createSearchValue(MNAME));
       ps.setString(3, createSearchValue(LNAME));
			
			//Statement stmt = con.createStatement();
			//ResultSet results = stmt.executeQuery(sql);

			ResultSet results = ps.executeQuery();

			while (results.next()) {
				Customer Customer1 = new Customer(results.getInt("CUSTID"), results.getString("FNAME"),
						results.getString("MNAME"), results.getString("LNAME"), results.getString("GENDER"),
						results.getDate("BIRTHDATE"), results.getDate("EXPDATE"),results.getFloat("TOTALBAL"));
				Customer.add(Customer1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return Customer;

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

	@Override
	public void add(Customer Customer) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}

		String sql = "INSERT INTO CUSTOMERTBL (FNAME, MNAME, LNAME, GENDER, BIRTHDATE, EXPDATE, TOTALBAL) VALUES (?, ?, ?, ?, ?, ?,?)";

		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, Customer.getFNAME());
			ps.setString(2, Customer.getMNAME());
			ps.setString(3, Customer.getLNAME());
			ps.setString(4, Customer.getGENDER());
			ps.setDate(5, Customer.getBIRTHDATE());
			ps.setDate(6, Customer.getEXPDATE());
			ps.setFloat(7, Customer.getTOTALBAL());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Customer Customer) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}

		String sql = "UPDATE CUSTOMERTBL SET FNAME = ?, MNAME = ?, LNAME = ?, GENDER = ?, BIRTHDATE = ?, EXPDATE = ?, TOTALBAL =? WHERE CUSTID = ?";

		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, Customer.getFNAME());
			ps.setString(2, Customer.getMNAME());
			ps.setString(3, Customer.getLNAME());
			ps.setString(4, Customer.getGENDER());
			ps.setDate(5, Customer.getBIRTHDATE());
			ps.setDate(6, Customer.getEXPDATE());
			ps.setFloat(7, Customer.getTOTALBAL());
			ps.setInt(8, Customer.getCUSTID());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@Override
	public void updateBal(Customer Customer) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		System.out.println("hello world");

		String sql = "UPDATE customertbl Set totalbal =(select sum(pointsamt) from transactiontbl where customertbl.CUSTID = transactiontbl.CUSTID)";

		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setFloat(1, Customer.getTOTALBAL());
			ps.setInt(2, Customer.getCUSTID());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void updateBal1(Customer Customer) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}

		String sql = "SELECT CUSTOMERTBL SET TOTALBAL = TOTALBAL - ? WHERE CUSTID = ?";
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setFloat(1, Customer.getTOTALBAL());
			ps.setInt(2, Customer.getCUSTID());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	

}
