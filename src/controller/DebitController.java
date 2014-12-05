package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.*;
import Rules.*;

public class DebitController implements Controller{
	private final static int BANK =-1;
	private final static int WITHDRAW =2;
	public boolean control(ArrayList<Account> accounts,SQL sql)   {
		
		
		if(accounts==null){
			System.out.println("No account for Debit");
			return false;
		}
		Scanner in =new Scanner(System.in);
		System.out.println("Please input the account number that you want to debit to:");
		int accountNo=in.nextInt();
		for(int i = 0;i<accounts.size();i++){
			if(accounts.get(i).getAccountNo()==accountNo){
				//need change the min value in future
				DeditAccountTransactionRule debitRule =new DeditAccountTransactionRule(accounts.get(i),0);
				ActionRule actionRule = new ActionRule(accounts.get(i));
				System.out.println("Please input the amount that you want to debit:");
				double amount = in.nextDouble();

				if(actionRule.CheckFreezeAccount()){
					System.out.println("debit transaction denied, your account has been frozen");
					return false;
				}
				if(actionRule.CheckClosedAccount()){
					System.out.println("Account has been closed");
					continue;
				}
				else if(!debitRule.canDedit(amount)){
					System.out.println("Insufficient balance for debit transaction");
					return false;
				} else
					try {
						if(!actionRule.amountLimits(sql.DbConnector(),accounts.get(i).getAccountNo())){
							System.out.println("It has debited over 10000 today");
							return false;
						}
						else{
							
							accounts.get(i).debit(amount);
							Record record =new Record();
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
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
			}
		}
		System.out.println("Account No doesn't exist");
		return false;
	}
}
