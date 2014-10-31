package controller;
import java.sql.Connection;
import DataSource.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
//import com.mysql.jdbc.PreparedStatement;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!



public class SQL {
	private static final String DRIVER_NAME ="com.mysql.jdbc.Driver";
	private static final String DB_ADDRESS ="jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/ad_7435525978ed9df";
	private static final String LOGIN_INFO= "?user=bf42eb7b32d79e&password=d1068231";

	private static final int INITIAL_BALANCE= 0;
	private Connection conn=null;
	private Statement stmt;
	private java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	public void DbConnector() throws SQLException{
		try {
            Class.forName(DRIVER_NAME).newInstance();
        } catch (Exception ex) {
            System.err.println("can't connect to internet\n");
        }
        
        try {
            conn = DriverManager.getConnection(DB_ADDRESS +LOGIN_INFO);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        stmt = conn.createStatement() ;
	}
	public ArrayList<Account> selectAccount(String accountQuery) throws SQLException {
		ResultSet table = stmt.executeQuery(accountQuery) ;
		//Account newAccount = new Account();
		ArrayList<Account> test = new ArrayList<Account>();
		Account account =null;
		 while (table.next())
	      {
			 int accountType = table.getInt("AccountType");
			 if(accountType ==1){
				 account = new Credit();
			 }
			 else if ( accountType == 2){
				 account = new Debit();
			 }
			account.setAccountType(accountType); 
	        account.setLoginID(table.getString("UserLoginID"));
	        account.setAccountNo (table.getInt("AccountNo"));
	        
	        account.setBalance( table.getDouble("Balance"));
	        account.setcreateDate( table.getDate("OpenDate"));
	        account.setTimeStamp ( table.getDate("timeStamp"));
	        //String Currency =table.getString("Currency");
	         test.add(account);
	        // print the results
	        //System.out.format("%s, \t%s, \t%s, \t%s, \t%s, \t%s, \t%s\n", id, AccountNo, AccountType, balance, openDate, timeStamp,Currency);
	        // account = new Account();
	      }
	      table.close();
		return test;
	    }
	public ArrayList<User> selectUser(String userQuery) throws SQLException{
		User user =new User();
		ResultSet table = stmt.executeQuery(userQuery) ;
		ArrayList<User> test = new ArrayList<User>();
		 while (table.next())
	      {
	        user.setFirst( table.getString("FirstName"));
	        user.setMiddle(  table.getString("MiddleName"));
	        user.setLast( table.getString("LastName"));
	        user.setAddress( table.getString("Address"));
	        user.setLoginID( table.getString("LoginID"));
	        user.setPassword( table.getString("Password"));
	        user.setcreateDate( table.getDate("CreateDate"));
	        user.setTimeStamp(table.getDate("TimeStamp"));
	        user.setGender(table.getString("Gender"));
	        user.setEmail( table.getString("Email"));
	        user.setPhone( table.getString("Phone"));
	        user.setCell (table.getString("Cell"));
	        user.setCountry( table.getString("Country"));
	        user.setState( table.getString("State"));
	        user.setZip( table.getString("ZipCode"));
	        user.setSocialSecurity( table.getString("SocialSecurity"));
	        user.setDOB (table.getDate("DateOfBirth"));
	        //String Currency =table.getString("Currency");
	        test.add(user); 
	        // print the results
	       // System.out.format("%s, \t%s, \t%s, \t%s, \t%s, \t%s, \t%s, \t%s\n", first, middle, last, address, loginID, pin,openDate,timeStamp);
	        user =new User();
	      }
	      table.close();
		return test;
	    }
	
	public void createAccount(Account account,String query) throws SQLException {
		//String query = " insert into Account (UserLoginID,AccountNo, AccountType, Balance, OpenDate,timeStamp)"
		      //  + " values (?, ?, ?, ?, ?,?)";
		 PreparedStatement preparedStmt=  conn.prepareStatement(query);
		 System.out.println(account.getLoginID());
	      preparedStmt.setString (1, account.getLoginID());
	      preparedStmt.setInt (2, account.getAccountNo());
	      preparedStmt.setInt  (3, account.getAccountType());
	      preparedStmt.setDouble(4, INITIAL_BALANCE);
	      preparedStmt.setTimestamp(5, date);
	      preparedStmt.setTimestamp(6, date);
	      preparedStmt.setTimestamp(7, null);
	      preparedStmt.execute();
	      preparedStmt.close();

	}
	public void createUser(User user,String query) throws SQLException{
		//String query = " insert into User (FirstName,MiddleName, LastName,Address, LoginID,Password,CreateDate,TimeStamp,Gender,Email,Phone,Cell,Country,State,ZipCode,SocialSecurity,DateOfBirth)"
		       // + " values (?, ?, ?, ?, ?,?, ?,?,?, ?, ?, ?, ?,?, ?,?,?)";
		 PreparedStatement preparedStmt=  conn.prepareStatement(query);
		 
	      preparedStmt.setString (1, user.getFirst());
	      preparedStmt.setString (2, user.getMiddle());
	      preparedStmt.setString  (3, user.getLast());
	      preparedStmt.setString(4, user.getAddress());
	      preparedStmt.setString(5, user.getLoginID());
	      preparedStmt.setString(6,user.getPassword());
	      preparedStmt.setTimestamp(7, date);
	      preparedStmt.setTimestamp(8, date);
	      preparedStmt.setString(9, user.getGender());
	      preparedStmt.setString(10, user.getEmail());
	      preparedStmt.setString(11,user.getPhone());
	      preparedStmt.setString(12, user.getCell());
	      preparedStmt.setString(13, user.getCountry());
	      preparedStmt.setString(14,user.getState());
	      preparedStmt.setString(15, user.getZip());
	      preparedStmt.setString(16, user.getSocialSecurity());
	      preparedStmt.setDate(17,user.getDOB());
	      preparedStmt.execute();
	      preparedStmt.close();
	}
	public User LoginCheck(User user,String query) throws SQLException{
		PreparedStatement statement = conn.prepareStatement(query);    
		statement.setString(1, user.getLoginID());
		statement.setString(2, user.getPassword());
		ResultSet table = statement.executeQuery();
		if(table.next()){
	        user.setFirst( table.getString("FirstName"));
	        user.setMiddle(  table.getString("MiddleName"));
	        user.setLast( table.getString("LastName"));
	        user.setAddress( table.getString("Address"));
	        user.setLoginID( table.getString("LoginID"));
	        user.setPassword( table.getString("Password"));
	        user.setcreateDate( table.getDate("CreateDate"));
	        user.setTimeStamp(table.getDate("TimeStamp"));
	        user.setGender(table.getString("Gender"));
	        user.setEmail( table.getString("Email"));
	        user.setPhone( table.getString("Phone"));
	        user.setCell (table.getString("Cell"));
	        user.setCountry( table.getString("Country"));
	        user.setState( table.getString("State"));
	        user.setZip( table.getString("ZipCode"));
	        user.setSocialSecurity( table.getString("SocialSecurity"));
	        user.setDOB (table.getDate("DateOfBirth"));
	        return user;
		}
		//return false;
		return null;
	}

	
    public static void main(String[] args) throws SQLException {

    	SQL sql =new SQL();
    	sql.DbConnector();
    	//sql.CreateAccount();
        //sql.selectTable();
        sql.conn.close();
       // }
    }
}
