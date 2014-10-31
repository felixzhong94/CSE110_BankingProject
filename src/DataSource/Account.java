package DataSource;
import java.sql.Date;
import java.text.*;


/**
 * @author felix
 * 
 *Implemented constructors accessors and modifiers
 *Need to change permission as project finalizes.
 */
public abstract class Account {
	
	int accountNo = 0; 
	double balance = 0.0;
	String first = "", last = "", middle = "";
	String address = "", loginID , pin = "";
	int accountType ;
	Date createDate,timeStamp;
	String gender ="";
	
	public Account() {}
	
	public Account (Account original) {

		first = original.first;
		middle = original.middle;
		last = original.last;
		
		address = original.address;
		loginID = original.loginID;
		pin = original.pin;
		
		accountType = original.accountType;
		accountNo = original.accountNo;
		balance = original.balance;
	}
	
	/**
	 * For creating Account from String inputs formatted in:
	 * "[first]\n[middle]\n[last]\n[address]\n[loginID]\n[pin]\0"
	 * @param input
	 */
//	public Account (String input) {
//		this.stringToFields(input);
//		
//	}

	protected void stringToFields (String input) {
		int x = 0;
		int y = 0;
		
		for(;input.charAt(x) != '\n'; x++){}
		first = input.substring(y, x-1);
		y = x++;
		
		for(;input.charAt(x) != '\n'; x++){}
		if(y == x)
			middle = "";
		else
			middle = input.substring(y, x-1);
		y = x++;
		
		for(;input.charAt(x) != '\n'; x++){}
		last = input.substring(y, x-1);
		y = x++;
		
		for(;input.charAt(x) != '\n'; x++){}
		address = input.substring(y, x-1);
		y = x++;
		
//		for(;input.charAt(x) != '\n'; x++){}
//		loginID = input.substring(y, x-1);
//		y = x++;
//		
//		for(;input.charAt(x) != '\n'; x++){}
//		pin = input.substring(y, x-1);
//		y = x++;
	}

	public int getAccountNo () {
		return accountNo;
	}
	
	public void setAccountNo (int input) {
		accountNo = input;
	}
	
	
	public double getBalance () {
		return balance;
	}
	
	public void setBalance (double input) {
		balance = input;
	}
	
	
	
	public String getLoginID () {
		
		return loginID;
	}
	
	public void setLoginID (String input) {
		loginID = input;
	}
	
	public int getAccountType () {
		return accountType;
	}
	
	public void setAccountType (int input) {
		accountType = input;
	}
	public int accountNoGenerator(){
		int n = (int)Math.floor( Math.random() * 100000 + 1 );
		NumberFormat formatter = new DecimalFormat("00000");
		String number = formatter.format(n);
		System.out.println("Number with lading zeros: " + number);
		return Integer.parseInt(number);
	}
	public Date getCreateDate () {
		return createDate;
	}
	
	public void setcreateDate (Date date)  {
		
		createDate= date;
	}
	public Date getTimeStamp () {
		return timeStamp;
	}
	
	public void setTimeStamp (Date date)  {
		
		timeStamp= date;
	}

}
