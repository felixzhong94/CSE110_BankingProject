package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DataSource.Account;
import DataSource.Credit;
import DataSource.Debit;
import DataSource.Record;
import DataSource.User;
import Rules.ActionRule;
import Rules.CheckUserRule;
import Rules.DeditAccountTransactionRule;

public class TransferController implements Controller{
	Account fromAccount;
	Account ToAccount;
	ActionRule actionRule;
	private final static int TRANSFER =3;
	@Override
	public boolean control(ArrayList<Account> accounts, SQL sql)  {
		
		//Record record =new Record();
		if(accounts==null){
			System.out.println("No account for transfer");
			return false;
		}
		if(accounts.size()==1){
			System.out.println("Only one account available in the system.");
		}
		Scanner in =new Scanner(System.in);
		System.out.println("Please input the account number that you want to transfer from:");
		int accountNo=in.nextInt();

			
		for(int i = 0;i<accounts.size();i++){
			if(accounts.get(i).getAccountNo()==accountNo){
				//System.out.println("Please input the amount that you want to credit:");
				//double amount = in.nextDouble();
				actionRule = new ActionRule(accounts.get(i));
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
					fromAccount = accounts.get(i);
					/*accounts.get(i).credit(amount);
					record.setAccountNo(accountNo);
					record.setCredit(amount);
					record.setBalance(accounts.get(i).getBalance());
					record.setAuthority(BANK);
					record.setType(DEPOSITE);*/
				}
			}
		}
		System.out.println("Please input the accountNo that you want to transfer money to:");
		accountNo =in.nextInt();
		User toUser=new User();
		
		ToAccount=new Credit();
		CheckUserRule checkUser = new CheckUserRule();
		
		try {
			int flag =checkUser.checkAccountType(sql.DbConnector(),accountNo);
			if(flag<0){
				System.out.println("Account No " + accountNo+" doesn't exist in the system");
				return false;
			}
			else if(flag==1){
				ToAccount=new Credit();
			}
			else if (flag==2){
				ToAccount=new Debit();
			}
			
			try {
				if(!checkUser.check(sql.DbConnector(),accountNo,ToAccount,toUser)){
					
					return false;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String option = null;
		boolean flag =true;
		do{
			System.out.println("Do you want to transfer to account: "+accountNo );
			System.out.println("Owned by: "+toUser.getFirst());
			System.out.println("input 1 for yes, 2 for no");
			option = in.nextLine();
		
			if(option.equals("1")){
				ActionRule actionRule = new ActionRule(fromAccount);
		//need to be modified in the future
				DeditAccountTransactionRule debitRule =new DeditAccountTransactionRule(fromAccount,0);
				System.out.println("Please input the amount of money that you want to transfer money to :");
				double amount = in.nextDouble();
				if(actionRule.CheckFreezeAccount()){
					System.out.println(" Transaction denied, your account has been frozen");
					return false;
				}
				if(actionRule.CheckClosedAccount()){
					System.out.println("Account has been closed");
					return false;
				}
				else if(! debitRule.canDedit(amount)){
					System.out.println("Insufficient balance for debit transaction");
					return false;
				}
				else{
					
					Record fromRecord = new Record();
					fromAccount.debit(amount);
					ToAccount.credit(amount);
				
					fromRecord.setAccountNo(fromAccount.getAccountNo());
					fromRecord.setDebit(amount);
					fromRecord.setBalance(fromAccount.getBalance());
					fromRecord.setAuthority(ToAccount.getAccountNo());
					fromRecord.setType(TRANSFER);
					Record ToRecord =new Record();
					ToRecord.setAccountNo(ToAccount.getAccountNo());
					ToRecord.setCredit(amount);
					ToRecord.setBalance(ToAccount.getBalance());
					ToRecord.setAuthority(fromAccount.getAccountNo());
					ToRecord.setType(TRANSFER);
					try {
						ToAccount.update(sql.DbConnector());
						fromAccount.update(sql.DbConnector());
						fromRecord.insertRecord(sql.DbConnector());
						ToRecord.insertRecord(sql.DbConnector());
					
					} catch (SQLException e) {
						System.err.println("Can't connect to Database to finish Credit");
						e.printStackTrace();
						return false;
					}
				
				return true;
				
				}
			}
			else if (option.equals("2")){
				flag =false;
			}
			else{
				System.out.println("Invalid input");
				flag = false;
			}
		}while(!flag);
		return true;
	
			
		

	}

}
