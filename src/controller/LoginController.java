package controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;

import java.util.Scanner;

import DataSource.User;

public class LoginController {
	public 	boolean loginController(User user,Password password,String query,SQL sql) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, SQLException{
			Scanner in = new Scanner(System.in);
			 System.out.println("Please enter your Login ID:");
			 user.setLoginID(in.nextLine());
			 System.out.println("Please enter your PassWord:");
			 user.setPassword (password.passwordConvertor(in.nextLine()));
			 User loginUser =sql.LoginCheck(user,query);
			 if(loginUser != null){
					 System.out.println(loginUser.getLoginID());
					 System.out.println(loginUser.getPassword());
					 System.out.println(loginUser.getAddress());
					 System.out.println(loginUser.getFirst());
					 System.out.println(loginUser.getMiddle());
					 System.out.println(loginUser.getLast());
					 System.out.println(loginUser.getGender());
					 System.out.println(loginUser.getEmail());
					 System.out.println(loginUser.getPassword());
					 System.out.println(loginUser.getPhone());
					 System.out.println(loginUser.getState());
					 System.out.println(loginUser.getZip());
					 System.out.println(loginUser.getSocialSecurity ());
					 System.out.println(loginUser.getDOB ());
					 System.out.println(loginUser.getCreateDate ());
					 System.out.println(loginUser.getTimeStamp ());
					 return true;
			 }
			 else{
				 System.out.println("User doesn't exist");
				 return false;
			 }
			 
		}
}
