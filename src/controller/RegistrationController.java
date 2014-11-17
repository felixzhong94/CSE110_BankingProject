package controller;




import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import DataSource.Admin;
import DataSource.User;

public interface RegistrationController {
	public User UserController(User newUser,Password password,ErrorChecking checking) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException;
	public Admin AdminController(Admin admin,Password password,ErrorChecking checking) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException; 
	
}
