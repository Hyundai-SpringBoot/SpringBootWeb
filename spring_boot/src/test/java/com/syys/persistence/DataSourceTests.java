/*************************************************************
파일명: DataSourceTests.java
작성자: 진영서
*************************************************************/
package com.syys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class DataSourceTests {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}//end static
	
	@Test
	public void TestConnection() {
		try {
			Connection con = 
					DriverManager.getConnection(
							"jdbc:oracle:thin:@edudb_high?TNS_ADMIN=C:/dev64/Wallet_edudb"
							,"boot","Spring1234");
			System.out.println(con);
		} catch (SQLException e) {
			System.out.println(e.getMessage());			
		}//end try			
	}//end test

}
