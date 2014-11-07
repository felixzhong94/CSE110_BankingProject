package controller;
import java.io.IOException;
import java.security.GeneralSecurityException;

import DataSource.*;

import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	 public static void main(String[] args) throws SQLException, GeneralSecurityException, NoSuchProviderException, IOException {
		 SQL sql = new SQL();
		 EmailSender sender=new EmailSender();
		 Password password =new Password();
		 ErrorChecking checking =new ErrorChecking();
		 LoginController login = new LoginController();
		 Account newAccount = null;
		 AccountCreateController account = new AccountCreateController();
		 AccountController accountControl = new AccountController();
		 RegistrationController registration = new RegistrationController(); 
		 User newUser =new User();
		 Scanner in = new Scanner(System.in);
		 boolean validOption=false;
		 System.out.println("Welcome to out Account registration page");
		 do{
			 System.out.println("Input 1 for login in, 2 for register,3 for sending you an email");
			 String choice = in.nextLine();
			 if(Integer.parseInt(choice)==1){
				 boolean flag;
			 	do{
			 		 flag=login.loginController(newUser,password, sql); 
			 		
			 	}while(flag==false);
			 	
			 	accountControl.controlAccount(newUser, sql);
		   }
		 
			 else if(Integer.parseInt(choice)==2){

				 boolean validation =true;
				 
				 do{
					 
						 newUser = registration.Registration(newUser,password,checking);
						 validation=newUser.create(sql.DbConnector());

						 sql.DbConnector().close();

	
					
				 }while(validation ==false);
				 validation = false;
				 sender.send(newUser.getEmail(),"Congratulation, registration completed","Registration Confirmation");
				 System.out.println("Registration completed, an email has been sent to your email address");
				 do{
					 System.out.println("Do you want to open an account?(y for yes, n for no)");
					 String option = in.nextLine();
					 switch(option){
					 	case "y":
					 		account.create(newUser.getLoginID(),sql);
					 		validation = true;
					 		break;
					 	case "n":
					 		validation =true;
					 		break;
					 	default:
					 		System.out.println("Invalid Input");
					 		break;
					 }
					
				 }while(validation == false);
			 }
			 else if(Integer.parseInt(choice)==3){
				
				sender.send("williamlin59@outlook.com","test","password reset");
				
			 }
			 else{
				 System.err.println("Invalid Option");
				 
			 }
			 newUser=new User();
		 }while(validOption ==false);
	 }
}
