package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.*;


public class DebitController implements Controller{

	public boolean control(ArrayList<Account> accounts,SQL sql)  {
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
					accounts.get(i).debit(amount);
					try {
						accounts.get(i).update(sql.DbConnector());
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
