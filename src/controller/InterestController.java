package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;
import DataSource.Record;
import Rules.ActionRule;

public class InterestController implements Controller{
	private final static int BANK =-1;
	private final static int INTEREST =4;
	@Override
	public boolean control(ArrayList<Account> accounts, SQL sql) {

		
		//System.out.println("Please input the account number that you want to show records:");
		//Scanner in =new Scanner(System.in);
		//int accountNo=in.nextInt();
		
		for(int i = 0;i<accounts.size();i++){
			try {
				ArrayList<Record> records=accounts.get(i).ThirtyDaysRecords(sql.DbConnector());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double amount = accounts.get(i).calculateInterest();
			if(amount!=0){
				Record record =new Record();
				record.setAccountNo(accounts.get(i).getAccountNo());
				record.setCredit(amount);
				record.setBalance(accounts.get(i).getBalance());
				record.setAuthority(BANK);
				record.setType(INTEREST);
				try {
					accounts.get(i).update(sql.DbConnector());
					record.insertRecord(sql.DbConnector());
			
				} catch (SQLException e) {
					System.err.println("Can't connect to Database to finish Interest Computing");
					e.printStackTrace();
				}
			
			}
			
		}
		
		return true;
	}

}
