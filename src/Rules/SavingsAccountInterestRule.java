package Rules;

import DataSource.Account;

public class SavingsAccountInterestRule implements InterestRules{
	
	private double rate = 0.01;
	private Account savingsAccount;
		
	public SavingsAccountInterestRule(Account account) {
		// TODO Auto-generated constructor stub
		savingsAccount = account;
	}

	@Override
	public void ApplyInterest() {
		// TODO Auto-generated method stub
		savingsAccount.setBalance(savingsAccount.getBalance() * (1 + rate));
		
	}

}
