package Rules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataSource.Account;
import DataSource.User;

public class CheckUserRule {
	public boolean check(Connection conn,int accountNo,Account account,User user){
		String query = "select * from Account,User where AccountNo = ? AND Account.UserLoginID =User.LoginID ";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			statement.setInt(1, accountNo);
			ResultSet table = statement.executeQuery();
			if(table.next()){
		        user.setFirst( table.getString("User.FirstName"));
		        user.setMiddle(  table.getString("User.MiddleName"));
		        user.setLast( table.getString("User.LastName"));
		        user.setPhone(table.getString("User.Phone"));
		        user.setEmail(table.getString("User.Email"));
		        account.setAccountNo( table.getInt("Account.AccountNo"));
		        account.setBalance( table.getDouble("Account.Balance"));
		        account.setAccountStauts( table.getInt("Account.AccountStatus"));
		        
		        return true;
			}
			else{
				System.out.println("2");
				return false;
			}
			}catch (SQLException e) {
			// TODO Auto-generated catch block
				System.out.println("3");
			e.printStackTrace();
			return false;
			}       

	}
	public int checkAccountType(Connection conn,int accountNo){
		String query = "select AccountType from Account,User where AccountNo = ? ";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(query);
			statement.setInt(1, accountNo);
			//statement.setString(1,Phone);
			ResultSet table = statement.executeQuery();
			if(table.next()){
		       /* user.setFirst( table.getString("User.FirstName"));
		        user.setMiddle(  table.getString("User.MiddleName"));
		        user.setLast( table.getString("User.LastName"));
		        account.setAccountNo( table.getInt("Account.AccountNo"));
		        account.setBalance( table.getDouble("Account.Balance"));
		        account.setAccountStauts( table.getInt("Account.AccountStatus"));*/
		        return table.getInt("AccountType");
			}
			else{
				System.out.println("2");
				return -1;
			}
			}catch (SQLException e) {
			// TODO Auto-generated catch block
				System.out.println("3");
			e.printStackTrace();
			return -1;
			}       

	}
}
