package DataSource;
/**
 * @author felix
 *
 */
public class Debit extends Account {
	private static final int DEBIT =2;
	public Debit() {
		accountType = DEBIT;
	}

	public Debit(Account original) {
		super(original);
	}

	public Debit(String input) {
		
		accountType = DEBIT;
	}

}
