package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.*;

public class AccountController {
	public void controlAccount(User newUser,SQL sql) throws SQLException{
	 	int noOfAccounts=newUser.viewAccount(sql.DbConnector());
	 	ArrayList<Account> userAccounts = null;
	 	sql.DbConnector().close();
	 	AccountCreateController account = new AccountCreateController();
	 	DebitController debit = new DebitController();
	 	CreditController credit = new CreditController();
	 	ShowBalanceController balance = new ShowBalanceController();
	 	Scanner in =new Scanner(System.in);
	 	boolean flag =true;
	 	do{
	 	System.out.println("Welcome to xxx bank, "+newUser.getFirst());
	 	System.out.println("Here is your accounts detail:");
	 	if(noOfAccounts ==0){
	 		System.out.println("You haven't opened any accountyet");
	 	}
	 	else{
	 		userAccounts =newUser.getUserAccounts();
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
	 		System.out.println("2. credit cash");
	 		System.out.println("3. debit cash");
	 		System.out.println("4. show balance");
	 		System.out.println("5. Back to main menu");
	 		String userInput = in.nextLine();
	 		switch(userInput){
	 			case "1":
	 				account.create(newUser.getLoginID(),sql);
	 		 		newUser.accountsCleaner();
	 				newUser.viewAccount(sql.DbConnector());
	 				userAccounts =newUser.getUserAccounts();
	 				break;
	 			case "2":

	 				credit.control(userAccounts,sql);
	 				
	 				break;
	 			case "3":
	 				 debit.control(userAccounts,sql);
	 				 
	 				break;
	 			case "4":

	 				 balance.control(userAccounts,sql);
	 				break;
	 			case "5":
	 				flag=false;
	 				break;
	 			default:
	 				System.err.println("Invalid input");
	 				
	 		}
	 		
	 	}while(flag ==true);
	 	
	 	

	}
}
