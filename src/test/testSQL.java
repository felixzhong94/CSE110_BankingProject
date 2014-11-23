package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataSource.User;

public class testSQL {
	public void testUserSQL(Connection conn,String Query, User user){
		User testUser = new User();
		String query = Query;
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			ResultSet table = statement.executeQuery();
			if(table.next()){
				testUser.setFirst( table.getString("FirstName"));
				testUser.setMiddle(  table.getString("MiddleName"));
				testUser.setLast( table.getString("LastName"));
				testUser.setAddress( table.getString("Address"));
				testUser.setLoginID( table.getString("LoginID"));
				testUser.setPassword( table.getString("Password"));
				testUser.setcreateDate( table.getDate("CreateDate"));
				testUser.setTimeStamp(table.getDate("TimeStamp"));
				testUser.setGender(table.getString("Gender"));
				testUser.setEmail( table.getString("Email"));
				testUser.setPhone( table.getString("Phone"));
				testUser.setCell (table.getString("Cell"));
				testUser.setCountry( table.getString("Country"));
				testUser.setState( table.getString("State"));
				testUser.setZip( table.getString("ZipCode"));
				testUser.setSocialSecurity( table.getString("SocialSecurity"));
				testUser.setDOB (table.getDate("DateOfBirth"));

		        
			}
			else{
				System.out.println("user doesn't exist in database");
				
			}
			}catch (SQLException e) {
			// TODO Auto-generated catch block
				System.out.println("cannot connect to database");
			
			
			} 
		
			System.out.println("First Name:");
			System.out.println(user.getFirst());
			System.out.println(testUser.getFirst());
			
			System.out.println("MiddleName");
			System.out.println(user.getMiddle());
			System.out.println(testUser.getMiddle());
			
			System.out.println("LastName");
			System.out.println(user.getLast());
			System.out.println(testUser.getLast());
			
			System.out.println("Address");
			System.out.println(user.getAddress());
			System.out.println(testUser.getAddress());
			
			System.out.println("LoginID");
			System.out.println(user.getLoginID());
			System.out.println(testUser.getLoginID());
			
			System.out.println("Password");
			System.out.println(user.getPassword());
			System.out.println(testUser.getPassword());
			
			System.out.println("CreateDate");
			System.out.println(user.getCreateDate());
			System.out.println(testUser.getCreateDate());
			
			System.out.println("TimeStamp");
			System.out.println(user.getTimeStamp());
			System.out.println(testUser.getTimeStamp());
			
			System.out.println("Gender");
			System.out.println(user.getGender());
			System.out.println(testUser.getGender());
			
			System.out.println("Email");
			System.out.println(user.getEmail());
			System.out.println(testUser.getEmail());
			
			System.out.println("Phone");
			System.out.println(user.getPhone());
			System.out.println(testUser.getPhone());
			
			System.out.println("Cell");
			System.out.println(user.getCell());
			System.out.println(testUser.getCell());
			
			System.out.println("Country");
			System.out.println(user.getCountry());
			System.out.println(testUser.getCountry());
			
			System.out.println("State");
			System.out.println(user.getState());
			System.out.println(testUser.getState());
			
			System.out.println("ZipCode");
			System.out.println(user.getZip());
			System.out.println(testUser.getZip());
			
			System.out.println("SocialSecurity");
			System.out.println(user.getSocialSecurity());
			System.out.println(testUser.getSocialSecurity());
			
			System.out.println("DateOfBirth");
			System.out.println(user.getDOB());
			System.out.println(testUser.getDOB());
	

	}
}
