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
		 SQLQueries query = new SQLQueries();
		 Password password =new Password();
		 ErrorChecking checking =new ErrorChecking();
		 LoginController login = new LoginController();
		 RegistrationController registration = new RegistrationController();
		  
		 Account newAccount=null;
		 User newUser =new User();
		 Scanner in = new Scanner(System.in);
		 boolean validOption=true;
		 System.out.println("Welcome to out Account registration page");
		 do{
			 System.out.println("Input 1 for login in, 2 for register,3 for sending you an email");
			 String choice = in.nextLine();
			 if(Integer.parseInt(choice)==1){
			 boolean  flag =false;
			 	do{
			 		sql.DbConnector();
			 		flag = login.loginController(newUser,password,query.LOGIN_CHECKING(), sql); 
			 	}while(flag==false);
			 
			 }
		 
			 else if(Integer.parseInt(choice)==2){
			 
				 System.out.println("Please enter the type of Account that you would love to open(we currently have credit and debit account):");
				 String input =in.nextLine();
				 if(input.equalsIgnoreCase("Credit")){
					 newAccount = new Credit();
					 newAccount.setAccountType(1);
				 }
				 else if(input.equalsIgnoreCase("Debit")){
					 newAccount = new Debit();
					 newAccount.setAccountType(2);
				 }
				 else{
					 System.err.println("Invalid input");
					 System.exit(0);
				 }

				 boolean validation =true;
				 do{
					 try{
						 newUser = registration.Registration(newUser,password,checking);
						 newAccount.setAccountNo(newAccount.accountNoGenerator());
						 newAccount.setLoginID(newUser.getLoginID());
						 sql.DbConnector();
						 sql.createUser(newUser,query.InsertUserQuery());
						 sql.createAccount(newAccount,query.InsertAccountQuery());
						 sender.send(newUser.getEmail(),"Congratulation, registration completed","Registration Confirmation");
						 System.out.println("Registration completed, an email has been sent to your email address");
					 }catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException a){
				 System.err.println("Login ID has been registered");
				 validation =false;
					 }
				 }while(validation ==false);
			 }
			 else if(Integer.parseInt(choice)==3){
				
				sender.send("williamlin59@outlook.com","test","password reset");
			 }
			 else{
				 System.err.println("Invalid Option");
				 validOption =false;
			 }
		 }while(validOption ==false);
	 }
}
