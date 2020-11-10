package test;

import java.sql.Connection;

import util.JDBCUtil;

public class test01 {

	public static void main(String[] args) {
		System.out.println("Connection test");
		Connection con = JDBCUtil.getConnection();
		System.out.println(con); //제대로 되나 con 찍어보기
		JDBCUtil.close(con, null, null);
		
	}

}
