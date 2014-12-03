package DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import Rules.CheckingAccountInterestRule;
import Rules.CreditAccountTransactionRule;
import Rules.InterestRules;
import Rules.TransactionRules;

public interface Account {
	
	//public boolean CanCredit (double amount);
	//public boolean CanDedit (double amount);
	

	
	public int getAccountNo ();
	public void setAccountNo (int input);
	
	public double getBalance ();
	public void setBalance (double input);
	
	public String getLoginID ();
	public void setLoginID (String input);
	
	public int getAccountType ();
	public void setAccountType (int input);
	
	public Date getCreateDate ();
	public void setcreateDate (Date date);
	
	public Date getTimeStamp ();
	public void setTimeStamp (Date date);
	
	public int accountNoGenerator();
	
	public void update(Connection conn);
	public void create(Connection conn);
	public boolean checkBalance(double amount);
	public double credit(double amount);
	public double debit(double amount);
	
	public int getAccountStatus ();
	public void setAccountStauts (int input);
	public ArrayList<Record> viewRecords(Connection conn);
	public ArrayList<Record> getRecords();
	public ArrayList<Record> ThirtyDaysRecords(Connection conn);
	public double calculateInterest();
	public double compute();

}

