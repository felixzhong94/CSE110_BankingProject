package controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.security.SecureRandom;
import java.math.BigInteger;

import controller.EmailSender;
import DataSource.Admin;
import DataSource.User;

public class ResetPassword {

	public ResetPassword(Password password,SQL sql) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, SQLException{
		Scanner in = new Scanner(System.in);
		LoginController login = new LoginController();
		User newUser =new User();
		Admin admin =new Admin();
		
		boolean flag;
		 System.out.println("Input 1 for login in as an user. Input 2 for login as an admin.");
		 String input = in.nextLine();
		 switch(input){
		 case "1":
			 	do{
			 		 flag=login.UserLloginController(newUser,password, sql); 
			 		
			 	}while(flag==false);
			 	
			 	break;
		 case "2":
			 	do{
			 		 flag=login.AdminLoginController(admin,password, sql); 
			 		
			 	}while(flag==false);
			 	
			 	break;
		 }
		
		 System.out.println("Please Enter Your New Password:");
		 String newPW = in.nextLine();
         String newPW1 = password.passwordConvertor(newPW);
		
		if(! newUser.updatePassword( sql.DbConnector() , newPW1 , newUser.getLoginID() ) ){
			System.out.println("Error: Account "+ login +" does not exist!");
			return;
		}
		
		System.out.println("Success: Password has been reset!!");
		return;
	}

}
