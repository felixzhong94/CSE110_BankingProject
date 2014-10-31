package DataSource;
/**
 * @author felix
 *
 */
public class Credit extends Account {
	private static final int CREDIT =1;
	public Credit() {
		accountType = CREDIT;
	}

	public Credit(Account original) {
		super(original);
	}

	public Credit(String input) {
		
		accountType = CREDIT;
	}

}
