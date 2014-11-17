package controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Scanner;

import DataSource.Admin;
import DataSource.User;

public class UserRegistration implements RegistrationController{
	public User UserController(User newUser,Password password,ErrorChecking checking) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException{
		 Scanner in =new Scanner (System.in);
		 String loginID;
		 String inputPassword;
		 String firstName;
		 String lastName;
		 String address;
		 String email;
		 String phoneNo;
		 String cell;
		 String zip;
		 
		 
		 
		 do{
		 System.out.println("(Manditory) Please enter your Login ID (At least 8 characters mixed with upper lower case and digits):");
		 loginID=in.nextLine();
		 }while(!loginID.matches(checking.getLOGIN_PATTERN()));
		 newUser.setLoginID(loginID);
		 
		 do{
			 System.out.println("(Manditory) Please enter your PassWord (At least 8 characters mixed with upper lower case and digits):");
			 inputPassword =in.nextLine();
		 }while(!inputPassword.matches(checking.getLOGIN_PATTERN()));
		 newUser.setPassword (password.passwordConvertor(inputPassword));
		 
		 do{
			 System.out.println("(Manditory) Please enter your FirstName (No digits):");
			 firstName =in.nextLine();
		 }while(!firstName.matches(checking.getLETTERS_ONLY_PATTERN()));
		 newUser.setFirst(firstName);
		 
			 System.out.println("Please enter your MiddleName:");
			 newUser.setMiddle(in.nextLine());
		 do{
			 System.out.println("(Manditory) Please enter your LastName (No digits):");
			 lastName =in.nextLine();
		 }while(!lastName.matches(checking.getLETTERS_ONLY_PATTERN()));
		 newUser.setLast(lastName);
		 
		 do{
			 System.out.println("(Manditory) Please enter your home address:");
			 address =in.nextLine();
		 }while(address.length()>500||address.isEmpty());
		 newUser.setAddress(address);
		 
		 System.out.println("Please enter your gender:");
		 newUser.setGender(in.nextLine());
		 do{
			 System.out.println(" (Manditory) Please enter your Email:");
			 email =in.nextLine();
		 }while(!email.matches(checking.getEMAIL_PATTERN()));
		 newUser.setEmail (email);
		 
		 do{
			 System.out.println("(Manditory) Please enter your Phone No:");
			 phoneNo=in.nextLine();
		 }while(!phoneNo.matches(checking.getFIFTEEN_DIGITS_PATTERN()));
		 newUser.setPhone(phoneNo);
		 
		 do{
			 System.out.println(" (Manditory) Please enter your Cell:");
			 cell=in.nextLine();
		 }while(!cell.matches(checking.getFIFTEEN_DIGITS_PATTERN()));
		 newUser.setCell(cell);
		 
		 
		 System.out.println("Please enter your Country:");
		 newUser.setCountry(in.nextLine());
		 System.out.println("Please enter your State:");
		 newUser.setState(in.nextLine());
		 
		 do{
			 System.out.println("(Manditory) Please enter your State ZipCode:");
			 zip=in.nextLine();
		 }while(!zip.matches(checking.getFIVE_DIGITS_PATTERN()));
		 newUser.setZip(zip);
		 
		 System.out.println("Please enter your Social Security No:");
		 newUser.setSocialSecurity(in.nextLine());
		 System.out.println("Please enter your Date of Birth:");
		 newUser.setDOB( new java.sql.Date(new java.util.Date().getTime()));
		 return newUser;
		
	}



	@Override
	public Admin AdminController(Admin admin, Password password,
			ErrorChecking checking) throws NoSuchAlgorithmException,
			GeneralSecurityException, UnsupportedEncodingException {
		Scanner in =new Scanner (System.in);
		 String loginID;
		 String inputPassword;
		 String firstName;
		 String lastName;
		 String branch;
		 String department;
		 String phoneNo;
		 String workingID;
		 String email;
		 
		 
		 do{
		 System.out.println("(Manditory) Please enter your Login ID (At least 8 characters mixed with upper lower case and digits):");
		 loginID=in.nextLine();
		 }while(!loginID.matches(checking.getLOGIN_PATTERN()));
		 admin.setLoginID(loginID);
		 
		 do{
			 System.out.println("(Manditory) Please enter your PassWord (At least 8 characters mixed with upper lower case and digits):");
			 inputPassword =in.nextLine();
		 }while(!inputPassword.matches(checking.getLOGIN_PATTERN()));
		 admin.setPassword (password.passwordConvertor(inputPassword));
		 
		 do{
			 System.out.println("(Manditory) Please enter your FirstName (No digits):");
			 firstName =in.nextLine();
		 }while(!firstName.matches(checking.getLETTERS_ONLY_PATTERN()));
		 admin.setFirst(firstName);
		
			 System.out.println("Please enter your MiddleName:");
			 admin.setMiddle(in.nextLine());
		 do{
			 System.out.println("(Manditory) Please enter your LastName (No digits):");
			 lastName =in.nextLine();
		 }while(!lastName.matches(checking.getLETTERS_ONLY_PATTERN()));
		 admin.setLast(lastName);
		 
		 do{
			 System.out.println("(Manditory) Please enter your branch:");
			 branch =in.nextLine();
		 }while(branch.length()>500||branch.isEmpty());
		 admin.setBranch(branch);
		 
		 System.out.println("Please enter your gender:");
		 admin.setGender(in.nextLine());

		 
		 do{
			 System.out.println("(Manditory) Please enter your Phone No:");
			 phoneNo=in.nextLine();
		 }while(!phoneNo.matches(checking.getFIFTEEN_DIGITS_PATTERN()));
		 admin.setPhone(phoneNo);
		 
		 do{
			 System.out.println(" (Manditory) Please enter your department:");
			 department=in.nextLine();
		 }while(department.length()>500||department.isEmpty());
		 admin.setDepartment(department);
		 do{
			 System.out.println(" (Manditory) Please enter your Email:");
			 email =in.nextLine();
		 }while(!email.matches(checking.getEMAIL_PATTERN()));
		 admin.setEmail (email);
		
			 System.out.println(" (Manditory) Please enter your workingID:");
			 workingID=in.nextLine();
		
			 admin.setWorkingID(workingID);
		 

		 return admin;
	}



}

