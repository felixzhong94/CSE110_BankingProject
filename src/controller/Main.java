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
		 UserRegistration registration = new UserRegistration();
		 User newUser =new User();
		 Admin admin =new Admin();
		 Scanner in = new Scanner(System.in);
		 boolean validOption=false;
		 System.out.println("Welcome to out Account registration page");
		 do{
			 System.out.println("Input 1 for login in, 2 for registrating user,3 for registrating admin, 4 for changing password 5 for emailing password");
			 String choice = in.nextLine();
			 if(Integer.parseInt(choice)==1){
				 boolean flag;
				 System.out.println("Input 1 for login in as an user. Input 2 for login as an admin.");
				 String input = in.nextLine();
				 switch(input){
				 case "1":
					 	do{
					 		 flag=login.UserLloginController(newUser,password, sql); 
					 		
					 	}while(flag==false);
					 	
					 	accountControl.UsercontrolAccount(newUser, sql);
					 	break;
				 case "2":
					 	do{
					 		 flag=login.AdminLoginController(admin,password, sql); 
					 		
					 	}while(flag==false);
					 	
					 	accountControl.AdmincontrolAccount(admin, sql);
					 	break;
				 }
				
					 
			 }

		   
		 
			 else if(Integer.parseInt(choice)==2){

				 boolean validation =true;
				 
				 do{
					 
						 newUser = registration.UserController(newUser,password,checking);
						 validation=newUser.create(sql.DbConnector());

						 sql.DbConnector().close();

	
					
				 }while(validation ==false);
				 validation = false;
				 String body =newUser.getFirst()+" "+newUser.getMiddle()+" "+newUser.getLast()+"\n"+"Thanks for registrating at the xxx online bank. Here is your log in detail:\n"+"LoginID: "+newUser.getLoginID()+"\n";
				 sender.send(newUser.getEmail(),body,"Registration Confirmation");
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
				 boolean validation =true;
				 
				 do{
					 
						 admin = registration.AdminController(admin,password,checking);
						 validation=admin.create(sql.DbConnector());

						 sql.DbConnector().close();

	
					
				 }while(validation ==false);
				 validation = false;
				 String body =admin.getFirst()+" "+admin.getMiddle()+" "+admin.getLast()+"\n"+"Thanks for registrating at the xxx online bank. Here is your log in detail:\n"+"LoginID: "+admin.getLoginID()+"\n";
				 sender.send(admin.getEmail(),body,"Registration Confirmation");
				 System.out.println("Registration completed, an email has been sent to your email address");
				/* do{
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
				//sender.send("williamlin59@outlook.com","test","password reset");*/
				
			 }
			 
			 else if(Integer.parseInt(choice)==4){
				 ResetPassword resetpassword = new ResetPassword(password, sql);
			}
			 
			 
			 else if(Integer.parseInt(choice)==5){
				 ForgetPassword forgetpassword = new ForgetPassword(newUser,password, sql);
			 }
			 
			 
			 
			 
			 else{
				 System.err.println("Invalid Option");
				 
			 }
			 newUser=new User();
		 }while(validOption ==false);
	 }
}
