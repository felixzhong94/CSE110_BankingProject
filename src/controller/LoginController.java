package controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.Scanner;

import DataSource.Admin;
import DataSource.User;

public class LoginController {
	public 	boolean UserLloginController(User user,Password password,SQL sql) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, SQLException{
			Scanner in = new Scanner(System.in);
			boolean login=false;
			 System.out.println("Please enter your Login ID:");
			 user.setLoginID(in.nextLine());
			 System.out.println("Please enter your PassWord:");
			 user.setPassword (password.passwordConvertor(in.nextLine()));
			 login =user.view(sql.DbConnector());
			 
			 if(login== true){
					 return login;
			 }
			 else{
				 System.out.println("User doesn't exist");
				 return false;
			 }
			 
		}
	public 	boolean AdminLoginController(Admin admin,Password password,SQL sql) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, SQLException{
		Scanner in = new Scanner(System.in);
		boolean login=false;
		 System.out.println("Please enter your Login ID:");
		 admin.setLoginID(in.nextLine());
		 System.out.println("Please enter your PassWord:");
		 admin.setPassword (password.passwordConvertor(in.nextLine()));
		 login =admin.view(sql.DbConnector());
		 
		 if(login== true){
				 return login;
		 }
		 else{
			 System.out.println("User doesn't exist");
			 return false;
		 }
		 
	}
}
