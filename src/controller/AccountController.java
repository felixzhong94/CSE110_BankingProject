package controller;

import java.sql.SQLException;

import DataSource.*;
import DataSource.User;

public class AccountController {
	public void control(User newUser,SQL sql) throws SQLException{
	 	int noOfAccounts=newUser.viewAccount(sql.DbConnector());
	 	sql.DbConnector().close();
	 	System.out.println("Welcome to xxx bank, "+newUser.getFirst());
	 	System.out.println("Please enter the command");
	 	System.out.println("1.");
	 	if(noOfAccounts ==0){
	 		System.out.println("No account available");
	 	}
	 	else{
	 		
	 	}
	}
}
