package controller;

import java.sql.SQLException;
import java.util.Scanner;

import DataSource.*;


public class AccountCreateController {
	public Account create(String LoginID,SQL sql) throws SQLException{
		Account newAccount=null;
		boolean flag=true;
		 do{
			 System.out.println("Please enter the type of Account that you would love to open(we currently have saving and checking account):");
			 Scanner in = new Scanner(System.in);
			 String input =in.nextLine();
			 if(input.equalsIgnoreCase("saving")){
				 newAccount = new Credit();
				 newAccount.setAccountType(1);
			 }
			 else if(input.equalsIgnoreCase("checking")){
				 newAccount = new Debit();
				 newAccount.setAccountType(2);
			 }
			 else{
				 System.err.println("Invalid input");
				 System.exit(0);
				 flag =false;
			 }
		 }while(flag == false);
	 		newAccount.setAccountNo(newAccount.accountNoGenerator());
			 newAccount.setLoginID(LoginID);
			 newAccount.create(sql.DbConnector());
			 sql.DbConnector().close();
		return newAccount;
		
	}
}
