package DataSource;

import java.sql.Date;
import java.util.ArrayList;
import java.sql.Connection;

public interface AccountHolder {
	
	public String getLoginID ();
	public void setLoginID (String input);
	
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

}
