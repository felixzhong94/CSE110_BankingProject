package controller;
import java.sql.Connection;
import DataSource.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

//import com.mysql.jdbc.PreparedStatement;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!



public class SQL {
	private static final String DRIVER_NAME ="com.mysql.jdbc.Driver";
	private static final String DB_ADDRESS ="jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/ad_7435525978ed9df";
	private static final String LOGIN_INFO= "?user=bf42eb7b32d79e&password=d1068231";
	private Connection conn=null;
	public Connection DbConnector() throws SQLException{
		try {
            Class.forName(DRIVER_NAME).newInstance();
        } catch (Exception ex) {
            System.err.println("can't connect to internet\n");
        }
        
        try {
            conn = DriverManager.getConnection(DB_ADDRESS +LOGIN_INFO);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
	}
	
	
    public static void main(String[] args) throws SQLException {

    	SQL sql =new SQL();
    	sql.DbConnector();
    	//sql.CreateAccount();
        //sql.selectTable();
        sql.conn.close();
       // }
    }
}
