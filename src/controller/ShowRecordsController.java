package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;
import DataSource.Record;
import Rules.ActionRule;

public class ShowRecordsController implements Controller {

	@Override
	public boolean control(ArrayList<Account> accounts, SQL sql) {
		
		ArrayList<Record> records;
		System.out.println("Please input the account number that you want to show records:");
		Scanner in =new Scanner(System.in);
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
				try {
					records=accounts.get(i).viewRecords(sql.DbConnector());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				for(int j=0;j<records.size();j++){
					System.out.println("Account No :"+records.get(j).getAccountNo());
					System.out.println("Credit :"+records.get(j).getCredit());
					System.out.println("Debit :"+records.get(j).getDebit());
					System.out.println("Balance :"+records.get(j).getBalance());
					System.out.println("Time:"+records.get(j).getTimeStamp());
					System.out.println("Authority :"+records.get(j).getAuthority());
					System.out.println("");
				}
				return true;
				}
			}
			
		}
		
		System.out.println("Account No doesn't exist");
		return false;
	}

}
