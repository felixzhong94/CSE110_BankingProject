package controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.security.SecureRandom;
import java.math.BigInteger;

import controller.EmailSender;
import DataSource.User;

public class ForgetPassword {

	public ForgetPassword(User user,Password password,SQL sql) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, SQLException{
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your Login ID:");
		String login = in.nextLine();
		
		
		SecureRandom random = new SecureRandom();
		String newPW = new BigInteger(130, random).toString(32);
		String newPW1 = password.passwordConvertor(newPW);
		System.out.println(newPW);
		
		if(! user.updatePassword( sql.DbConnector() , newPW1 , login ) ){
			System.out.println("Account "+ login +" does not exist!");
			//System.out.println("Account "+ user.getLoginID());
			return;
		}
		
		EmailSender sender=new EmailSender();
		user.setLoginID(login);
		user.setPassword(newPW1);
		user.view(sql.DbConnector());
		
		String body =user.getFirst()+" "+user.getMiddle()+" "+user.getLast()+"\n"+"Your new password for:"+user.getLoginID()+" is: "+newPW
				+"\n"+"you can reset your password once you login\n";
		 sender.send(user.getEmail(),body,"Password Reset");
		 System.out.println("An email has been sent to your email address");
		
	}

}
