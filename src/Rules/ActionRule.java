package Rules;

import DataSource.Account;

public class ActionRule {
	
	private static final int ACTIVE = 1;
	private static final int FROZEN = 0;
	private static final int CLOSED = -1;

	private static final int CREDIT = 1;
	private static final int DEBIT = 2;
	
	private Account account;
	private TransactionRules transaction;
	private InterestRules interest;
	
	public ActionRule(Account account) {
		this.account = account;
		switch(account.getAccountType()){
			case CREDIT: 
				transaction = new CreditAccountTransactionRule(account);
				interest = new CheckingAccountInterestRule(account);
				break;
			case DEBIT: 
				transaction = new DeditAccountTransactionRule(account);
				interest = new CheckingAccountInterestRule(account);
				break;
		}
	}
	
	public boolean CanViewBalance() {
		return (account.getAccountStatus() != CLOSED);
	}
	
	public boolean CanCredit(double amount) {
		if(account.getAccountStatus() != ACTIVE){
			System.out.println("Inactive Account");
			return false;
		}
		else{
			return transaction.canCredit(amount);
		}	
	}
	
	public boolean CanDebit(double amount) {
		if(account.getAccountStatus() != ACTIVE){
			System.out.println("Inactive Account");
			return false;
		}
		else{
			return transaction.canDedit(amount);
		}
	}
	
	public void ApplyInterest() {
		if(account.getAccountStatus() == ACTIVE)
			interest.ApplyInterest();
	}
	
	
	public boolean FreezeAccount(){
		if(account.getAccountStatus() != FROZEN){
			account.setAccountStauts(FROZEN);
			return true;
		}
		else
			return false;
	}
	
	public boolean CloseAccount(){
		if(account.getAccountStatus() != CLOSED){
			account.setAccountStauts(CLOSED);
			return true;
		}
		else
			return false;
	}
	
	public boolean ReactivateAccount(){
		if(account.getAccountStatus() != ACTIVE){
			account.setAccountStauts(ACTIVE);
			return true;
		}
		else
			return false;
	}

}
