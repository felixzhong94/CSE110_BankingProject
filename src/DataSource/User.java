package DataSource;


import java.sql.Date;


public class User {
	String first,middle,last,address,loginID,password,gender;
	String email,phone,cell,country,state,zip,socialSecurity;
	Date createDate, timeStamp,DOB;
	
	
	public String getFirst () {
		return first;
	}
	
	public void setFirst (String input) {
		first = input;
	}
	
	public String getMiddle () {
		return middle;
	}
	
	public void setMiddle (String input) {
		middle = input;
	}
	
	public String getLast () {
		return last;
	}
	
	public void setLast (String input) {
		last = input;
	}
	
	public String getAddress () {
		return address;
	}
	
	public void setAddress (String input) {
		address = input;
	}
	public String getLoginID () {
		return loginID;
	}
	
	public void setLoginID (String input) {
		loginID = input;
	}
	
	public String getPassword () {
		return password;
	}
	
	public void setPassword (String input) {
		password = input;
	}
	
	public String getGender () {
		return gender;
	}
	
	public void setGender (String input) {
		gender = input;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String input) {
		email = input;
	}
	
	
	public String getPhone () {
		return phone;
	}
	
	public void setPhone (String input) {
		phone = input;
	}
	
	public String getCell () {
		return cell;
	}
	
	public void setCell (String input) {
		cell = input;
	}
	
	public String getCountry () {
		return last;
	}
	
	public void setCountry (String input) {
		country = input;
	}
	
	public String getState () {
		return state;
	}
	
	public void setState (String input) {
		state = input;
	}
	public String getZip () {
		return zip;
	}
	
	public void setZip (String input) {
		zip = input;
	}
	
	public String getSocialSecurity () {
		return socialSecurity;
	}
	
	public void setSocialSecurity (String input) {
		socialSecurity = input;
	}
	
	public Date getDOB () {
		return DOB;
	}
	
	public void setDOB (Date date) {
		DOB = date;
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
