package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.*;

public class AccountController {
	public void AdmincontrolAccount(Admin admin,SQL sql) throws SQLException{
	 	int noOfAccounts=admin.viewAccount(sql.DbConnector());
	 	ArrayList<Account> userAccounts = null;
	 	sql.DbConnector().close();
	 	AccountCreateController account = new AccountCreateController();
	 	DebitController debit = new DebitController();
	 	CreditController credit = new CreditController();
	 	ShowBalanceController balance = new ShowBalanceController();
	 	Scanner in =new Scanner(System.in);
	 	boolean flag =true;
	 	do{
	 	System.out.println("Welcome to xxx bank, "+admin.getFirst());
	 	System.out.println("Here are all accounts in the system:");
	 	if(noOfAccounts ==0){
	 		//System.out.println("You haven't opened any accountyet");
	 		System.out.println("No accounts in the system yet.");
	 	}
	 	else{
	 		userAccounts =admin.getAccounts();
	 		for(int i=0;i<userAccounts.size();i++){
	 			System.out.println("Account Number: "+userAccounts.get(i).getAccountNo());
	 			System.out.println("Account Type: "+(userAccounts.get(i).getAccountType()==1?"saving":"checking"));
	 			System.out.println("Opening Date:"+userAccounts.get(i).getCreateDate ());
	 			System.out.println("Balance :"+userAccounts.get(i).getBalance());
	 			System.out.println("");
	 		}
	 	}
	 	//admin.viewRecords(null, noOfAccounts);

	 		System.out.println("Please enter the command");
	 		//System.out.println("1. create new acount");
	 		System.out.println("1. credit cash");
	 		System.out.println("2. debit cash");
	 		System.out.println("3. show balance");
	 		System.out.println("4. Back to main menu");
	 		String userInput = in.nextLine();
	 		switch(userInput){
	 			/*case "1":
	 				account.create(admin.getLoginID(),sql);
	 				admin.accountsCleaner();
	 				admin.viewAccount(sql.DbConnector());
	 				userAccounts =admin.getAccounts();
	 				break;*/
	 			case "1":

	 				credit.control(userAccounts,sql);
	 				
	 				break;
	 			case "2":
	 				 debit.control(userAccounts,sql);
	 				 
	 				break;
	 			case "3":

	 				 balance.control(userAccounts,sql);
	 				break;
	 			case "4":
	 				flag=false;
	 				break;
	 			default:
	 				System.err.println("Invalid input");
	 				
	 		}
	 		
	 	}while(flag ==true);
	 	
	 	

	}
	public void UsercontrolAccount(User user,SQL sql) throws SQLException{
	 	int noOfAccounts=user.viewAccount(sql.DbConnector());
	 	ArrayList<Account> userAccounts = null;
	 	sql.DbConnector().close();
	 	AccountCreateController account = new AccountCreateController();
	 	DebitController debit = new DebitController();
	 	CreditController credit = new CreditController();
	 	ShowBalanceController balance = new ShowBalanceController();
	 	Scanner in =new Scanner(System.in);
	 	boolean flag =true;
	 	do{
	 	System.out.println("Welcome to xxx bank, "+user.getFirst());
	 	System.out.println("Here are all accounts you have:");
	 	if(noOfAccounts ==0){
	 		System.out.println("You haven't opened any accountyet");
	 		//System.out.println("No accounts in the system yet.");
	 	}
	 	else{
	 		userAccounts =user.getAccounts();
	 		for(int i=0;i<userAccounts.size();i++){
	 			System.out.println("Account Number: "+userAccounts.get(i).getAccountNo());
	 			System.out.println("Account Type: "+(userAccounts.get(i).getAccountType()==1?"saving":"checking"));
	 			System.out.println("Opening Date:"+userAccounts.get(i).getCreateDate ());
	 			System.out.println("Balance :"+userAccounts.get(i).getBalance());
	 			System.out.println("");
	 		}
	 	}


	 		System.out.println("Please enter the command");
	 		System.out.println("1. create new acount");
	 		//System.out.println("2. credit cash");
	 		//System.out.println("3. debit cash");
	 		System.out.println("2. show balance");
	 		System.out.println("3. Back to main menu");
	 		String userInput = in.nextLine();
	 		switch(userInput){
	 			case "1":
	 				account.create(user.getLoginID(),sql);
	 				user.accountsCleaner();
	 				user.viewAccount(sql.DbConnector());
	 				userAccounts =user.getAccounts();
	 				break;
	 			/*case "2":

	 				credit.control(userAccounts,sql);
	 				
	 				break;
	 			case "3":
	 				 debit.control(userAccounts,sql);
	 				 
	 				break;*/
	 			case "2":

	 				 balance.control(userAccounts,sql);
	 				break;
	 			case "3":
	 				flag=false;
	 				break;
	 			default:
	 				System.err.println("Invalid input");
	 				
	 		}
	 		
	 	}while(flag ==true);
	 	
	 	

	}
}
