package Rules;

import DataSource.Account;

public class CreditAccountTransactionRule implements TransactionRules{

	private double minimumBalance = 0;
	private Account account;
	
	public CreditAccountTransactionRule(Account account, double minimumbalance) {
		// TODO Auto-generated constructor stub
		this.account = account;
		this.minimumBalance = minimumbalance;
	}

	@Override
	public Boolean canCredit(double amount) {
		// TODO Auto-generated method stub
		if (amount > 0)
			return true;
		else{
			System.out.println("Incorrect Amount");
			return false;
		}
	}

	@Override
	public Boolean canDedit(double amount) {
		// TODO Auto-generated method stub
		if (amount <= 0){
			System.out.println("Incorrect Amount");
			return false;
		}
		else if(account.getBalance() - minimumBalance < amount){
			System.out.println("Insufficient funds");
			return false;
		}
		return true;

	}

}
