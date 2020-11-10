package util; //커넥션만 얻어와서 자원반납하는 역할

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection con = null;
		
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("D:/eunyoung/lib/dbinfo.txt")); //절대적인 주소로 바꿈
			
			//이러한 데이터를 기반으로 커넥션을 돌림
			String driver = p.getProperty("driver"); //파일에 있는 내용이 읽혀져들어옴
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String pw = p.getProperty("pw"); 
			
			Class.forName(driver);
			
			con = DriverManager.getConnection(url,user,pw);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void close(Connection con , Statement st , ResultSet rs ) { //반납해야 할 자원들

		//자원반납하는 기능
		try {
			if(rs != null) rs.close(); //rs가 널일 경우 안 된다고 함.. st con도 마찬가지
			if(st != null) st.close();
			if(con != null) con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}




