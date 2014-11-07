package DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author felix
 *
 */
public class Credit implements Account {
	
	private static final int CREDIT =1;
	
	int accountNo = 0; 
	double balance = 0.0;
	String  loginID ;
	int accountType = CREDIT;
	Date createDate,timeStamp;
	private java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

	@Override
	public int getAccountNo () {
		return accountNo;
	}
	
	@Override
	public void setAccountNo (int input) {
		accountNo = input;
	}
	
	@Override
	public double getBalance () {
		return balance;
	}
	
	@Override
	public void setBalance (double input) {
		balance = input;
	}
	
	@Override
	public String getLoginID () {
		
		return loginID;
	}
	
	@Override
	public void setLoginID (String input) {
		loginID = input;
	}
	
	@Override
	public int getAccountType () {
		return accountType;
	}
	
	@Override
	public void setAccountType (int input) {
		accountType = input;
	}
	
	@Override
	public int accountNoGenerator(){
		int n = (int)Math.floor( Math.random() * 100000 + 1 );
		NumberFormat formatter = new DecimalFormat("00000");
		String number = formatter.format(n);
		System.out.println("Number with lading zeros: " + number);
		return Integer.parseInt(number);
	}
	
	@Override
	public Date getCreateDate () {
		return createDate;
	}
	
	@Override
	public void setcreateDate (Date date)  {
		
		createDate= date;
	}
	
	@Override
	public Date getTimeStamp () {
		return timeStamp;
	}
	
	@Override
	public void setTimeStamp (Date date)  {
		
		timeStamp= date;
	}
	
	@Override
	public void update(Connection conn){
		
			String query = "UPDATE Account SET AccountType = ?, Balance = ?, timeStamp=? WHERE AccountNo = ? ";
			PreparedStatement preparedStmt;
			try {
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1,this.getAccountType());
				preparedStmt.setDouble(2,this.getBalance ());
				preparedStmt.setTimestamp(3, this.date);
				preparedStmt.setInt(4,this.getAccountNo());
				preparedStmt.executeUpdate();
				preparedStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}
	
	@Override
	public void create(Connection conn) {
		String query = " insert into Account (UserLoginID,AccountNo, AccountType, Balance, OpenDate,timeStamp)"
	       + " values (?, ?, ?, ?, ?,?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			System.out.println(this.getLoginID());
			preparedStmt.setString (1, this.getLoginID());
			preparedStmt.setInt (2, this.getAccountNo());
			preparedStmt.setInt  (3, this.getAccountType());
			preparedStmt.setDouble(4, this.getBalance ());
			preparedStmt.setTimestamp(5, this.date);
			preparedStmt.setTimestamp(6, this.date);
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public boolean checkBalance(double amount){
		return balance >=amount;
	}
	public double credit(double amount){
		balance = balance +amount;
		return balance;
	}
	public double debit(double amount){
		balance = balance -amount;
		return amount;
		
	}
}	
