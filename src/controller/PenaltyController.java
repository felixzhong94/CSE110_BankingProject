package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import DataSource.Account;
import DataSource.Record;

public class PenaltyController implements Controller{
	private final static int BANK =-1;
	private final static int PENALTY =5;
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
			double amount = accounts.get(i).compute();
			if(amount!=0){
				Record record =new Record();
				record.setAccountNo(accounts.get(i).getAccountNo());
				record.setDebit(amount);
				record.setBalance(accounts.get(i).getBalance());
				record.setAuthority(BANK);
				record.setType(PENALTY);
				try {
					accounts.get(i).update(sql.DbConnector());
					record.insertRecord(sql.DbConnector());
			
				} catch (SQLException e) {
					System.err.println("Can't connect to Database to finish Interest Computing");
					e.printStackTrace();
					return false;
				}
			
			}
			
		}
		
		return true;
	}

}
