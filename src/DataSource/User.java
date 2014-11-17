package DataSource;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class User implements AccountHolder{
	String first,middle,last,address,loginID,password,gender;
	String email,phone,cell,country,state,zip,socialSecurity;
	Date createDate, timeStamp,DOB;
	private ArrayList<Account> accounts=new ArrayList<Account>();
	private java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	
	public String getFirst () {
		return first;
	}
	
	public void setFirst (String input) {
		first = input;
	}
	
	public String getMiddle () {
		return middle;
	}
	
	public void setMiddle (String input) {
		middle = input;
	}
	
	public String getLast () {
		return last;
	}
	
	public void setLast (String input) {
		last = input;
	}
	
	public String getAddress () {
		return address;
	}
	
	public void setAddress (String input) {
		address = input;
	}
	public String getLoginID () {
		return loginID;
	}
	
	public void setLoginID (String input) {
		loginID = input;
	}
	
	public String getPassword () {
		return password;
	}
	
	public void setPassword (String input) {
		password = input;
	}
	
	public String getGender () {
		return gender;
	}
	
	public void setGender (String input) {
		gender = input;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String input) {
		email = input;
	}
	
	
	public String getPhone () {
		return phone;
	}
	
	public void setPhone (String input) {
		phone = input;
	}
	
	public String getCell () {
		return cell;
	}
	
	public void setCell (String input) {
		cell = input;
	}
	
	public String getCountry () {
		return last;
	}
	
	public void setCountry (String input) {
		country = input;
	}
	
	public String getState () {
		return state;
	}
	
	public void setState (String input) {
		state = input;
	}
	public String getZip () {
		return zip;
	}
	
	public void setZip (String input) {
		zip = input;
	}
	
	public String getSocialSecurity () {
		return socialSecurity;
	}
	
	public void setSocialSecurity (String input) {
		socialSecurity = input;
	}
	
	public Date getDOB () {
		return DOB;
	}
	
	public void setDOB (Date date) {
		DOB = date;
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
	public void setAccounts(ArrayList<Account> accounts){
		this.accounts =accounts;
	}
	public ArrayList<Account> getAccounts(){
		return accounts;
	}
	public boolean update(Connection conn){
	
		String query = "UPDATE User SET FirstName = ?, MiddleName = ?, LastName=?,Address = ?, Password= ?,TimeStamp=?,Gender=?,Email=?,Phone=?,Cell=?,Country=?,State=?,ZipCode=? ,SocialSecurity=?,DateOfBirth=?WHERE  LoginID = ? AND Password =? ";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, this.getFirst());
			preparedStmt.setString (2, this.getMiddle());
			preparedStmt.setString  (3, this.getLast());
			preparedStmt.setString(4, this.getAddress());
			preparedStmt.setString(5,this.getPassword());
			preparedStmt.setTimestamp(6, date);
			preparedStmt.setString(7, this.getGender());
			preparedStmt.setString(8, this.getEmail());
			preparedStmt.setString(9,this.getPhone());
			preparedStmt.setString(10, this.getCell());
			preparedStmt.setString(11, this.getCountry());
			preparedStmt.setString(12,this.getState());
			preparedStmt.setString(13, this.getZip());
			preparedStmt.setString(14, this.getSocialSecurity());
			preparedStmt.setDate(15,this.getDOB());
			preparedStmt.setString(16,this.getLoginID());
			preparedStmt.setString(17,this.getPassword());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean create(Connection conn){
		String query = " insert into User (FirstName,MiddleName, LastName,Address, LoginID,Password,CreateDate,TimeStamp,Gender,Email,Phone,Cell,Country,State,ZipCode,SocialSecurity,DateOfBirth)"
	       + " values (?, ?, ?, ?, ?,?, ?,?,?, ?, ?, ?, ?,?, ?,?,?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, this.getFirst());
			preparedStmt.setString (2, this.getMiddle());
			preparedStmt.setString  (3, this.getLast());
			preparedStmt.setString(4, this.getAddress());
			preparedStmt.setString(5, this.getLoginID());
			preparedStmt.setString(6,this.getPassword());
			preparedStmt.setTimestamp(7, date);
			preparedStmt.setTimestamp(8, date);
			preparedStmt.setString(9, this.getGender());
			preparedStmt.setString(10, this.getEmail());
			preparedStmt.setString(11,this.getPhone());
			preparedStmt.setString(12, this.getCell());
			preparedStmt.setString(13, this.getCountry());
			preparedStmt.setString(14,this.getState());
			preparedStmt.setString(15, this.getZip());
			preparedStmt.setString(16, this.getSocialSecurity());
			preparedStmt.setDate(17,this.getDOB());
			preparedStmt.execute();

			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Login ID has been registered");
			return false;
		}
		return true;	
	}
	
	
	public boolean view(Connection conn){
		String query = "select * from User where LoginID = ? and Password =?";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			statement.setString(1, this.getLoginID());
			statement.setString(2, this.getPassword());
			ResultSet table = statement.executeQuery();
			if(table.next()){
		        this.setFirst( table.getString("FirstName"));
		        this.setMiddle(  table.getString("MiddleName"));
		        this.setLast( table.getString("LastName"));
		        this.setAddress( table.getString("Address"));
		        this.setLoginID( table.getString("LoginID"));
		        this.setPassword( table.getString("Password"));
		        this.setcreateDate( table.getDate("CreateDate"));
		        this.setTimeStamp(table.getDate("TimeStamp"));
		        this.setGender(table.getString("Gender"));
		        this.setEmail( table.getString("Email"));
		        this.setPhone( table.getString("Phone"));
		        this.setCell (table.getString("Cell"));
		        this.setCountry( table.getString("Country"));
		        this.setState( table.getString("State"));
		        this.setZip( table.getString("ZipCode"));
		        this.setSocialSecurity( table.getString("SocialSecurity"));
		        this.setDOB (table.getDate("DateOfBirth"));
		        System.out.println("1");
		        return true;
			}
			else{
				System.out.println("2");
				return false;
			}
			}catch (SQLException e) {
			// TODO Auto-generated catch block
				System.out.println("3");
			e.printStackTrace();
			return false;
			}    

	        

	}
	public int viewAccount(Connection conn){
		String query="select * from Account where UserLoginID = ?";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			statement.setString(1, getLoginID ());
			ResultSet table = statement.executeQuery();	
			while(table.next()){
				Account account = null;
				 if(table.getInt("AccountType") ==1){
					 account = new Credit();
				 }
				 else if ( table.getInt("AccountType") ==2){
					 account = new Debit();
				 }
				account.setAccountType(table.getInt("AccountType")); 
		        account.setLoginID(table.getString("UserLoginID"));
		        account.setAccountNo (table.getInt("AccountNo"));
		        
		        account.setBalance( table.getDouble("Balance"));
		        account.setcreateDate( table.getDate("OpenDate"));
		        account.setTimeStamp ( table.getDate("timeStamp"));
		        accounts.add(account);
			}
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
			}    

			return accounts.size();
		
	}
	public ArrayList<Account> getUserAccounts(){
		
		return accounts;
		
	}
	public void accountsCleaner(){
		accounts.clear();
	}


}

