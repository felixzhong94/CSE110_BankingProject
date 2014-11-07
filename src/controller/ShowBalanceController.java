package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;

public class ShowBalanceController implements Controller{

	public boolean control(ArrayList<Account> accounts,SQL sql) {
		if(accounts==null){
			System.out.println("No account for showing balance");
			return false;
		}
		Scanner in =new Scanner(System.in);
		System.out.println("Please input the account number that you want to show the balance to:");
		int accountNo=in.nextInt();
		for(int i = 0;i<accounts.size();i++){
			if(accounts.get(i).getAccountNo()==accountNo){
				System.out.println("Account "+accounts.get(i).getAccountNo()+"'s balanace is "+accounts.get(i).getBalance());
				return true;
			}
		}
		System.out.println("Account No doesn't exist");
		return false;
	}
}
