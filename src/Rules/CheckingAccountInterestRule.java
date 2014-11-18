package Rules;

import DataSource.Account;

public class CheckingAccountInterestRule implements InterestRules{

	private double rate = 0.0;
	private Account checkingAccount;
	
	public CheckingAccountInterestRule(Account account) {
		// TODO Auto-generated constructor stub
		checkingAccount = account;
	}

	@Override
	public void ApplyInterest() {
		// TODO Auto-generated method stub
		checkingAccount.setBalance(checkingAccount.getBalance() * (1 + rate));
		
	}

}
