package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;
import Rules.ActionRule;

public class ShowBalanceController implements Controller{

	public boolean control(ArrayList<Account> accounts,SQL sql) {
		if(accounts==null){
			System.out.println("No account for close");
			return false;
		}
		Scanner in =new Scanner(System.in);
		System.out.println("Please input the account number that you want to show balance:");
		int accountNo=in.nextInt();
		for(int i = 0;i<accounts.size();i++){
			if(accounts.get(i).getAccountNo()==accountNo){
				ActionRule actionRule = new ActionRule(accounts.get(i));
				if(actionRule.CheckFreezeAccount()){
					System.out.println(" The account has been frozen");
					return false;
				}
				if(actionRule.CheckClosedAccount()){
					System.out.println("Account has been closed");
					continue;
				}
				else{
					System.out.println("Account "+accounts.get(i).getAccountNo()+"'s balanace is "+accounts.get(i).getBalance());
					return true;
				}
			}
		}
		System.out.println("Account No doesn't exist");
		return false;
	}
}
