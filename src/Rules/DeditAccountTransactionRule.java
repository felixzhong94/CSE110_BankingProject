package Rules;

import DataSource.Account;

public class DeditAccountTransactionRule implements TransactionRules{

	private Account account;
	
	public DeditAccountTransactionRule(Account account) {
		this.account = account;
	}


	public Boolean canCredit(double amount) {
		if (amount > 0)
			return true;
		else{
			System.out.println("Incorrect Amount");
			return false;
		}
	}

	@Override
	public Boolean canDedit(double amount) {
		if (amount <= 0){
			System.out.println("Incorrect Amount");
			return false;
		}
		else if(account.getBalance() - account.getMinimumBalance() < amount){
			System.out.println("Insufficient funds");
			return false;
		}
		return true;

	}

}
