package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;

public class CreditController implements Controller{
	@Override
	public boolean control(ArrayList<Account> accounts,SQL sql) {
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
				accounts.get(i).credit(amount);
				try {
					accounts.get(i).update(sql.DbConnector());
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
