package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.*;
import Rules.ActionRule;


public class DebitController implements Controller{
	private final static int BANK =-1;
	private final static int WITHDRAW =2;
	public boolean control(ArrayList<Account> accounts,SQL sql)  {
		Record record =new Record();
		if(accounts==null){
			System.out.println("No account for Debit");
			return false;
		}
		Scanner in =new Scanner(System.in);
		System.out.println("Please input the account number that you want to debit to:");
		int accountNo=in.nextInt();
		for(int i = 0;i<accounts.size();i++){
			if(accounts.get(i).getAccountNo()==accountNo){
				System.out.println("Please input the amount that you want to debit:");
				double amount = in.nextDouble();
				if(accounts.get(i).checkBalance(amount)){
					
					//check rule class
					ActionRule rule = new ActionRule(accounts.get(i));
					if(! rule.CanDebit(amount)){
						System.out.println("Cannot complete credit:");
						return false;
					}
					
					accounts.get(i).debit(amount);
					record.setAccountNo(accountNo);
					record.setDebit(amount);
					record.setBalance(accounts.get(i).getBalance());
					record.setAuthority(BANK);
					record.setType(WITHDRAW);
					try {
						accounts.get(i).update(sql.DbConnector());
						record.insertRecord(sql.DbConnector());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.err.println("Can't connect to Database to finish Debit");
						e.printStackTrace();
					}
					return true;
				}
				else{
					System.out.println("Insufficient balance for credit transaction");
					return false;
				}
				
			}
		}
		System.out.println("Account No doesn't exist");
		return false;
	}
}
