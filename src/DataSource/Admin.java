package DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin implements AccountHolder{
	private String loginID,workingID;
	private String passWord;
	private String first;
	private String last;
	private String middle;
	private String gender;
	private String branch;
	private String department;
	private String phone;
	Date createDate, timeStamp;
	private String email;
	private java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	private ArrayList<Account> accounts=new ArrayList<Account>();
	
	@Override
	public String getLoginID() {
		// TODO Auto-generated method stub
		return loginID;
	}

	@Override
	public void setLoginID(String input) {
		loginID = input;
		
	}

	@Override
	public String getGender() {
		// TODO Auto-generated method stub
		return gender;
	}

	@Override
	public void setGender(String input) {
		gender = input;
		
	}

	@Override
	public String getPhone() {
		// TODO Auto-generated method stub
		return phone;
	}

	@Override
	public void setPhone(String input) {
		phone = input;
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passWord;
	}

	@Override
	public void setPassword(String input) {
		passWord = input;
		
	}

	@Override
	public Date getCreateDate() {
		// TODO Auto-generated method stub
		return createDate;
	}

	@Override
	public void setcreateDate(Date date) {
		// TODO Auto-generated method stub
		createDate =date;
	}

	@Override
	public Date getTimeStamp() {
		// TODO Auto-generated method stub
		return timeStamp;
	}

	@Override
	public void setTimeStamp(Date date) {
		// TODO Auto-generated method stub
		timeStamp = date;
	}

	@Override
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts =accounts;
		
	}

	@Override
	public ArrayList<Account> getAccounts() {
		// TODO Auto-generated method stub
		return accounts;
	}
	

	@Override
	public boolean update(Connection conn) {
		String query = "UPDATE Admin SET FirstName = ?, MiddleName = ?, LastName=?, Password= ?,TimeStamp=?,Gender=?,Branch=?,Department=?,Phone=?,WorkingID=?,WHERE  LoginID = ? AND Password =? ";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, this.getFirst());
			preparedStmt.setString (2, this.getMiddle());
			preparedStmt.setString  (3, this.getLast());
			preparedStmt.setString(4,this.getPassword());
			preparedStmt.setTimestamp(5, date);
			preparedStmt.setTimestamp(6, date);
			preparedStmt.setString(7, this.getGender());
			preparedStmt.setString(8, this.getBranch());
			preparedStmt.setString(9,this.getDepartment());
			preparedStmt.setString(10,this.getPhone());
			preparedStmt.setString(11, this.getWorkingID());
			preparedStmt.execute();

			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Login ID has been registered");
			return false;
		}
		return true;	

	}

	@Override
	public boolean create(Connection conn)  {
		String query = " insert into Admin (FirstName,MiddleName, LastName, LoginID,Password,CreateDate,TimeStamp,Gender,Branch,Department,Phone,WorkingID,Email)"
			       + " values (?, ?, ?, ?, ?,?, ?,?,?, ?, ?, ?,?)";
				PreparedStatement preparedStmt;
				try {
					preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString (1, this.getFirst());
					preparedStmt.setString (2, this.getMiddle());
					preparedStmt.setString  (3, this.getLast());
					preparedStmt.setString(4, this.getLoginID());
					preparedStmt.setString(5,this.getPassword());
					preparedStmt.setTimestamp(6, date);
					preparedStmt.setTimestamp(7, date);
					preparedStmt.setString(8, this.getGender());
					preparedStmt.setString(9, this.getBranch());
					preparedStmt.setString(10,this.getDepartment());
					preparedStmt.setString(11,this.getPhone());
					preparedStmt.setString(12, this.getWorkingID());
					preparedStmt.setString(13, this.getEmail());
					preparedStmt.execute();

					preparedStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println("Login ID has been registered");
					return false;
				}
				return true;	

	}

	@Override
	public boolean view(Connection conn) {
		String query = "select * from Admin where LoginID = ? and Password =?";
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
		        this.setLoginID( table.getString("LoginID"));
		        this.setPassword( table.getString("Password"));
		        this.setcreateDate( table.getDate("CreateDate"));
		        this.setTimeStamp(table.getDate("TimeStamp"));
		        this.setGender(table.getString("Gender"));
		        this.setDepartment( table.getString("Department"));
		        this.setPhone( table.getString("Phone"));
		        this.setBranch (table.getString("Branch"));
		        this.setWorkingID( table.getString("WorkingID"));
		        this.setEmail( table.getString("Email"));
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

	@Override
	public int viewAccount(Connection conn) {
		String query="select * from Account ";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
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
	
	public String getBranch() {
		// TODO Auto-generated method stub
		return branch;
	}

	
	public void setBranch(String input) {
		branch = input;
		
	}
	
	public String getDepartment() {
		// TODO Auto-generated method stub
		return branch;
	}

	
	public void setDepartment(String input) {
		department = input;
		
	}
	
	public String getWorkingID() {
		// TODO Auto-generated method stub
		return workingID;
	}

	
	public void setWorkingID(String input) {
		workingID = input;
		
	}

	@Override
	public String getFirst() {
		// TODO Auto-generated method stub
		return first;
	}

	@Override
	public void setFirst(String input) {
		first = input;
		
	}

	@Override
	public String getMiddle() {
		// TODO Auto-generated method stub
		return middle;
	}

	@Override
	public void setMiddle(String input) {
		middle = input;
		
	}

	@Override
	public String getLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLast(String input) {
		last = input;
		
	}
	public void accountsCleaner(){
		accounts.clear();
	}

	@Override
	public String getEmail() {
		
		return email;
	}

	@Override
	public void setEmail(String input) {
		email = input;
		
	}

	@Override
	public void InsertRecord(Connection conn,double amount) {
		/*String query = " insert into Records (AccountNo,Credit, Debit, Balance,Authority,TimeStamp,Type)"
			       + " values (?, ?, ?, ?, ?,?,?)";
				PreparedStatement preparedStmt;
				//try {
					preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString (1, this.getFirst());
					preparedStmt.setString (2, this.getMiddle());
					preparedStmt.setString  (3, this.getLast());
					preparedStmt.setString(4, this.getLoginID());
					preparedStmt.setString(5,this.getPassword());
					preparedStmt.setTimestamp(6, date);
					preparedStmt.setTimestamp(7, date);
					preparedStmt.setString(8, this.getGender());
					preparedStmt.setString(9, this.getBranch());
					preparedStmt.setString(10,this.getDepartment());
					preparedStmt.setString(11,this.getPhone());
					preparedStmt.setString(12, this.getWorkingID());
					preparedStmt.setString(13, this.getEmail());
					preparedStmt.execute();

					preparedStmt.close();
				/*} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println("Login ID has been registered");
					return false;
				}*/
	}
	

}
