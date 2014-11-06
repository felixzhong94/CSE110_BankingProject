package DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;


/**
 * @author felix
 * 
 *Implemented constructors accessors and modifiers
 *Need to change permission as project finalizes.
 */
public abstract class Account {
	
	int accountNo = 0; 
	double balance = 0.0;
	String  loginID ;
	int accountType ;
	Date createDate,timeStamp;
	private java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	public Account() {}
	
	public Account (Account original) {
		loginID = original.loginID;	
		accountType = original.accountType;
		accountNo = original.accountNo;
		balance = original.balance;
	}
	
	/**
	 * For creating Account from String inputs formatted in:
	 * "[first]\n[middle]\n[last]\n[address]\n[loginID]\n[pin]\0"
	 * @param input
	 */
//	public Account (String input) {
//		this.stringToFields(input);
//		
//	}

	public int getAccountNo () {
		return accountNo;
	}
	
	public void setAccountNo (int input) {
		accountNo = input;
	}
	
	
	public double getBalance () {
		return balance;
	}
	
	public void setBalance (double input) {
		balance = input;
	}
	
	
	
	public String getLoginID () {
		
		return loginID;
	}
	
	public void setLoginID (String input) {
		loginID = input;
	}
	
	public int getAccountType () {
		return accountType;
	}
	
	public void setAccountType (int input) {
		accountType = input;
	}
	public int accountNoGenerator(){
		int n = (int)Math.floor( Math.random() * 100000 + 1 );
		NumberFormat formatter = new DecimalFormat("00000");
		String number = formatter.format(n);
		System.out.println("Number with lading zeros: " + number);
		return Integer.parseInt(number);
	}
	public Date getCreateDate () {
		return createDate;
	}
	
	public void setcreateDate (Date date)  {
		
		createDate= date;
	}
	public Date getTimeStamp () {
		return timeStamp;
	}
	
	public void setTimeStamp (Date date)  {
		
		timeStamp= date;
	}
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
			preparedStmt.setTimestamp(7, null);
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
