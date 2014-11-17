package DataSource;

import java.sql.Connection;

public class Record {
	private int AccountNo;
	private double credit;
	private double debit;
	private double balance;
	private int authority;
	private int type;
	
	public void setAccountNo(int input){
		AccountNo= input;
	}
	public int getAccountNo(){
		return AccountNo;
	}
	
	public void setDebit(double input){
		debit = input;
	}
	public double getDebit(){
		return debit;
	}
	public void setCredit(double input){
		credit = input;
	}
	public double getCredit(){
		return credit;
	}
	public void setBalance(double input){
		balance = input;
	}
	public double getBalance(){
		return balance;
	}
	public void setAuthority(int input){
		authority = input;
	}
	public int getAuthority(){
		return authority;
	}
	public void setType(int input){
		type = input;
	}
	public int getType(){
		return type;
	}
	public void InsertRecord(Connection conn){
		
	}
}
