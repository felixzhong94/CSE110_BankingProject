/**
 * Filename: PenaltyController.java
 * Description: Controller that is in charge of penalty calculation for user accounts.
 */

package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import DataSource.Account;
import DataSource.Record;

/**
 * Class Name: PenaltyController
 * Description: Controls the penalty calculation operation. Implements the Controller interface.
 */
public class PenaltyController implements Controller{
	private final static int BANK =-1; // variable for setting the account's authority
	private final static int PENALTY =5; // variable for keeping track of the operation type

	/**
     * Control method specifically to operate penalty calculation.
     * @param accounts This is the list of accounts.
     * @param sql This is the database that stores account information.
     * @return true if the operation succeeded, false otherwise
     */
	@Override
	public boolean control(ArrayList<Account> accounts, SQL sql) {

		//System.out.println("Please input the account number that you want to show records:");
		//Scanner in =new Scanner(System.in);
		//int accountNo=in.nextInt();

		for(int i = 0;i<accounts.size();i++){ // for each account
			try {
				ArrayList<Record> records=accounts.get(i).ThirtyDaysRecords(sql.DbConnector());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double amount = accounts.get(i).compute(); // calculate penalty
			if(amount!=0){
				Record record =new Record();
				record.setAccountNo(accounts.get(i).getAccountNo()); // reset account number
				record.setDebit(amount); // set debit to debit the user
				record.setBalance(accounts.get(i).getBalance()); // reset the balance
				record.setAuthority(BANK); // set to bank authority
				record.setType(PENALTY); // type is "penalty"
				try {
					accounts.get(i).update(sql.DbConnector()); // update the database
					record.insertRecord(sql.DbConnector());

				} catch (SQLException e) { // quit current calculation operations
					System.err.println("Can't connect to Database to finish Interest Computing");
					e.printStackTrace();
					return false;
				}
			
			}
			
		}
		
		return true;
	}

}
