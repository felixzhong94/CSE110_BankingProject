package controller;

import java.util.ArrayList;

import DataSource.Account;

public interface Controller {
	public boolean control(ArrayList<Account> accounts,SQL sql);
}
