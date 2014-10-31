package controller;

public class SQLQueries {
	private static final String SELECT_ACCOUNT= "Select * FROM Account";
	private static final String SELECT_USER= "Select * FROM User" ;
	private static final String INSERT_ACCOUNT = " insert into Account (UserLoginID,AccountNo, AccountType, Balance, OpenDate,timeStamp,Currency)"
	        									+ " values (?, ?, ?, ?, ?,?,?)";
	private static final String INSERT_USER = " insert into User (FirstName,MiddleName, LastName,Address, LoginID,Password,CreateDate,TimeStamp,Gender,Email,Phone,Cell,Country,State,ZipCode,SocialSecurity,DateOfBirth)"
		        							+ " values (?, ?, ?, ?, ?,?, ?,?,?, ?, ?, ?, ?,?, ?,?,?)";
	private static final String LOGIN_CHECKING ="select * from User where LoginID = ? and Password =?";
	public String SelectAccountQuery(){
		return SELECT_ACCOUNT;
	}
	public String SelectUserQuery(){
		return SELECT_USER;
	}
	public String InsertAccountQuery(){
		return INSERT_ACCOUNT;
	}
	public String InsertUserQuery(){
		return INSERT_USER;
	}
	public String LOGIN_CHECKING(){
		return LOGIN_CHECKING;
	}

}
