package DataSource;

import java.sql.Connection;
import java.sql.Date;

public interface Account {
	
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

}

