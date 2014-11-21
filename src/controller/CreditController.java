package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;
import DataSource.Record;
import Rules.ActionRule;

public class CreditController implements Controller{
	private final static int BANK =-1;
	private final static int DEPOSITE =1;
	@Override
	public boolean control(ArrayList<Account> accounts,SQL sql) {
		Record record =new Record();
		if(accounts==null){
			System.out.println("No account for credit");
			return false;
		}
		Scanner in =new Scanner(System.in);
		System.out.println("Please input the account number that you want to credit to:");
		int accountNo=in.nextInt();
		
		for(int i = 0;i<accounts.size();i++){
			if(accounts.get(i).getAccountNo()==accountNo){
				System.out.println("Please input the amount that you want to credit:");
				double amount = in.nextDouble();
				ActionRule actionRule = new ActionRule(accounts.get(i));
				//accounts.get(i);
				//validation checking
				if(actionRule.CheckFreezeAccount()){
					System.out.println("debit transaction denied, your account has been frozen");
					return false;
				}
				if(actionRule.CheckClosedAccount()){
					System.out.println("Account has been closed");
					continue;
				}
				else{
					accounts.get(i).credit(amount);
					record.setAccountNo(accountNo);
					record.setCredit(amount);
					record.setBalance(accounts.get(i).getBalance());
					record.setAuthority(BANK);
					record.setType(DEPOSITE);
				try {
					accounts.get(i).update(sql.DbConnector());
					record.insertRecord(sql.DbConnector());
					
				} catch (SQLException e) {
					System.err.println("Can't connect to Database to finish Credit");
					e.printStackTrace();
				}
				
				return true;
			}
			}
		}
		System.out.println("Account No doesn't exist");
		
		
		return false;
	}
		
}


