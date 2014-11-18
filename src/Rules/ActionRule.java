package Rules;

import DataSource.Account;

public class ActionRule {
	
	private static final int ACTIVE = 1;
	private static final int FROZEN = 0;
	private static final int CLOSED = -1;

	private Account account;
	
	public ActionRule(Account account) {
		// TODO Auto-generated constructor stub
		this.account = account;
	}
	
	public boolean CanLogin() {
		return (account.getAccountStatus() != CLOSED);
	}
	
	public boolean CanCredit(double amount, TransactionRules transactionrule) {
		return (account.getAccountStatus() == ACTIVE && transactionrule.canCredit(amount));
	}
	
	public boolean CanDebit(double amount, TransactionRules transactionrule) {
		return (account.getAccountStatus() == ACTIVE && transactionrule.canDedit(amount));
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

	public void ApplyInterest(InterestRules interestrule) {
		// TODO Auto-generated method stub
		if(account.getAccountStatus() == ACTIVE)
			interestrule.ApplyInterest();
	}

}
