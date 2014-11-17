package DataSource;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

public interface AccountHolder {
	
	public String getLoginID ();
	public void setLoginID (String input);
	
	public String getGender ();
	public void setGender (String input);
	
	public String getPhone ();
	public void setPhone (String input);
	
	public String getFirst();
	public void setFirst(String input);

	
	public String getMiddle();
	public void setMiddle(String input);
	
	public String getLast();
	public void setLast(String input);
	
	public String getPassword ();
	public void setPassword (String input);
	public Date getCreateDate ();
	public void setcreateDate (Date date);
	
	public Date getTimeStamp ();
	public void setTimeStamp (Date date);
	
	public void setAccounts(ArrayList<Account> accounts);
	public ArrayList<Account> getAccounts();
	
	public boolean update(Connection conn);
	public boolean create(Connection conn);
	public boolean view(Connection conn);
	public int viewAccount(Connection conn);
	public String getEmail();
	public void setEmail(String input);
	
	public void InsertRecord(Connection conn,double amount);

}
