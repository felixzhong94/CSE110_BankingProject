package controller;

public class ErrorChecking {
	
	private static final String LOGIN_PATTERN ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,12})";
	private static final String LETTERS_ONLY_PATTERN = "^[a-zA-Z]*$";
	private static final String FIVE_DIGITS_PATTERN="\\d{5}";
	private static final String EMAIL_PATTERN=".*[@].*";
	private static final String TWELVE_DIGITS_PATTERN="\\d{0,12}";

	public String getLOGIN_PATTERN(){
		return LOGIN_PATTERN;
	}
	public String getLETTERS_ONLY_PATTERN(){
		return LETTERS_ONLY_PATTERN;
	}
	public String getFIVE_DIGITS_PATTERN(){
		return FIVE_DIGITS_PATTERN;
	}
	public String getEMAIL_PATTERN(){
		return EMAIL_PATTERN;
	}
	public String getFIFTEEN_DIGITS_PATTERN(){
		return TWELVE_DIGITS_PATTERN;
	}
	public boolean Checking(String userInput,String pattern){
		return userInput.matches(pattern);
	}
	public static void main(String[] args) {
		ErrorChecking test =new ErrorChecking();
		System.out.println(test.Checking("12345",test.getFIFTEEN_DIGITS_PATTERN()));
		
		
	}
}
