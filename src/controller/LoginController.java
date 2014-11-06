package controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.Scanner;

import DataSource.User;

public class LoginController {
	public 	User loginController(User user,Password password,SQL sql) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, SQLException{
			Scanner in = new Scanner(System.in);
			boolean login=false;
			 System.out.println("Please enter your Login ID:");
			 user.setLoginID(in.nextLine());
			 System.out.println("Please enter your PassWord:");
			 user.setPassword (password.passwordConvertor(in.nextLine()));
			 login =user.view(sql.DbConnector());
			 if(login== true){
					 System.out.println(user.getLoginID());
					 System.out.println(user.getPassword());
					 System.out.println(user.getAddress());
					 System.out.println(user.getFirst());
					 System.out.println(user.getMiddle());
					 System.out.println(user.getLast());
					 System.out.println(user.getGender());
					 System.out.println(user.getEmail());
					 System.out.println(user.getPassword());
					 System.out.println(user.getPhone());
					 System.out.println(user.getState());
					 System.out.println(user.getZip());
					 System.out.println(user.getSocialSecurity ());
					 System.out.println(user.getDOB ());
					 System.out.println(user.getCreateDate ());
					 System.out.println(user.getTimeStamp ());
					 return user;
			 }
			 else{
				 System.out.println("User doesn't exist");
				 return null;
			 }
			 sql.DbConnector().close();
			 
		}
}
