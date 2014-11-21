package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;
import DataSource.Record;
import Rules.ActionRule;

public class CloseAccountController implements Controller {
	private final static int BANK =-1;
	private final static int CLOSE =-1;
	@Override
	public boolean control(ArrayList<Account> accounts, SQL sql) {
			Record record =new Record();
			if(accounts==null){
				System.out.println("No account for close");
				return false;
			}
			Scanner in =new Scanner(System.in);
			System.out.println("Please input the account number that you want to close:");
			int accountNo=in.nextInt();
			
			for(int i = 0;i<accounts.size();i++){
				
				if(accounts.get(i).getAccountNo()==accountNo){
					ActionRule actionRule = new ActionRule(accounts.get(i));
					if(actionRule.CheckFreezeAccount()){
						System.out.println("The account has been frozon, please defrost the account first");
						return false;
					}
					if(actionRule.CheckClosedAccount()){
						System.out.println("Account has been closed");
						continue;
					}
					//accounts.get(i);
					//validation checking
					/*if(! accounts.get(i).CanCredit(amount)){
						System.out.println("Cannot complete credit:");
						return false;
					}*/
					else{
						accounts.get(i).setAccountStauts(CLOSE);
						record.setAccountNo(accountNo);
					//record.setCredit(amount);
						record.setBalance(0);
						record.setAuthority(BANK);
						record.setType(CLOSE);
					}
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
			System.out.println("Account No doesn't exist");
			
			
			return false;
	}

}
