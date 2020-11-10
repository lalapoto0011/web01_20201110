package dao; //DB 연동하는 코드를 가지고 있음

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.BookVO;

public class BookDAO_MariaDB {

	// 아래 sql 처리하는 메소드
	public List<BookVO> BookList() { // 이 메소드를 호출할 때 정말 테이블 구조 내용이 나오는지 확인해야 함
		List<BookVO> list = new ArrayList<BookVO>();
		String sql = "select * from book order by bookno desc"; // 얘를 처리하고 싶음..
		// 이 sql구문 처리한 결과가 테이블 내용
		// book테이블에 어떤 내용이 있는지? - sql구문 처리할 수 있는 메소드 등장해야함 - 나름대로 메소드 이름 결정하기, 조원들끼리.

		// DB연동 코드 - 연결할 변수들?
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; // 결과값 처리할 객체

		try { // sql구문 처리하는 부분 들어감
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); // 테이블 내용을 ??

			while (rs.next()) { // 커서가 메타태그를 가리키고 있음, rs.next하면 밑으로 내려감, 데이터를 꺼내서 자바북브이오에 객체화시킴???
				BookVO vo = new BookVO(); // vo객체가 만들어졌지만 비어있음
				vo.setBookno(rs.getInt("bookno"));
				vo.setPrice(rs.getInt("price"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setTitle(rs.getString("title")); // 테이블의 데이터들을 꺼내서 부유 객체에 넣어줌
				list.add(vo); // vo객체를 add해주세요?
			}

		} catch (Exception e) {
			System.out.println("error" + e);
		} finally { // 무조건 자원 반납
			JDBCUtil.close(con, ps, rs);
		}

		return list;
		// resultset을 자바로 뭐 어쩐다고????
		// 리턴타입 -> 레코드가 하나가 아니라 여러개니까 -> 무조건 배열 아니면 컬렉션
	}

	public void BookAdd(BookVO vo) { // int도 가능 //메소드 이름 다시 정의
		// 괄호 안 내용 : 물음표로 정의되는 값들은 다 BookVO 안에 있다 --> 객체화. vo 라는 객체로 객체화한것인듯?

		String sql = "insert into book (bookno,title,publisher,price) values ((select nvl(max(bookno),0)+1 from book),?,?,?)";		// 얘를 처리한 다음에 리턴타입을 어떻게 할 것인지..??

		// DB연동 할 때 필요한 변수들
		Connection con = null; // 로컬변수니까 널로 초기화
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0; // int타입 쓸 때 필요?

		try {
			con = JDBCUtil.getConnection(); // JDBCUtil에서 커넥션 얻어옴, 얘가 무조건 커넥션 만듦
			ps = con.prepareStatement(sql); // sql관리해주는 ps생성됨
			// ? 값 세팅
			// ? 4개의 값을 셋팅 - 파라미터로 받아야 함.. 매개변수로 넘겨받아야 함... bookno, title, publisher, price
			// 를 다 가지고 있는 객체가 BookVO
		//	ps.setInt(1, vo.getBookno());
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getPublisher());
			ps.setInt(3, vo.getPrice());

			// sql구문 실행 - 둘 중에 하나 골라서 실행한다.
			// ps.executeQuery(); //select
			// ps.executeUpdate(); //insert, update, delete
			row = ps.executeUpdate(); // add는 insert니까 이걸 실행, ..

			// 결과값 처리
			if (row == 0) {// row가 0일 경우 인서트가 안 되었다 (인서트 값이 없다 - 등록실패)
				throw new Exception("등록 실패"); // 예외 객체 생성
				// 예외는 던져지는 거니까 앞에 throw 붙임?
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납! 반드시 해줘야 함.

		}

	}

	public void BookDelete(int bookno) { // int도 가능 //메소드 이름 다시 정의
		int row = 0;

		String sql = "delete from book where bookno = ?"; // bookno는 PK
		// 얘를 처리한 다음에 리턴타입을 어떻게 할 것인지..??

		// DB연동 할 때 필요한 변수들
		Connection con = null; // 로컬변수니까 널로 초기화
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = JDBCUtil.getConnection(); // JDBCUtil에서 커넥션 얻어옴, 얘가 무조건 커넥션 만듦
			ps = con.prepareStatement(sql); // sql관리해주는 ps생성됨
			// ? 값 세팅
			ps.setInt(1, bookno); // 값 1개니까, ?자리에 셋팅

			// sql구문 실행 - 둘 중에 하나 골라서 실행한다.
			// ps.executeQuery(); //select
			// ps.executeUpdate(); //insert, update, delete
			row = ps.executeUpdate();

			// 결과값 처리 - 여기선 필요없어서 넘어감.. 1이면 삭제니까?

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납! 반드시 해줘야 함.

		}

	}

	
	  public void BookUpdate(BookVO vo) { //int도 가능 //메소드 이름 다시 정의 int row = 0;
	  
	  String sql = "UPDATE book set price = ? where bookno = ?"; //price만 값이 바뀐다
	  //얘를 처리한 다음에 리턴타입을 어떻게 할 것인지..?? //UPDATE book set price = price*0.8 where
	  //bookno = 1 예로 cmd 테스트 - 업데이트 되는지?

		// DB연동 할 때 필요한 변수들
		Connection con = null; // 로컬변수니까 널로 초기화
		PreparedStatement ps = null;
		ResultSet rs = null;
	  
	  try { 
		  con = JDBCUtil.getConnection(); //JDBCUtil에서 커넥션 얻어옴, 얘가 무조건 커넥션 만듦 
		  ps = con.prepareStatement(sql); //sql관리해주는 ps생성됨 // ? 값 세팅 
		  ps.setInt(1, vo.getPrice()); //setInt : price가 인트 타입이니까 
		  ps.setInt(2, vo.getBookno());
		  //ps.setString(3, vo.getTitle()); 
		  //ps.setString(4, vo.getPublisher());
	  
		  // sql구문 실행 - 둘 중에 하나 골라서 실행한다. // ps.executeQuery(); //select //
		  ps.executeUpdate(); //insert, update, delete ps.executeUpdate();
	  
		  //결과값 처리
	  	} catch (Exception e) { 
	  		e.printStackTrace(); 
	  	} finally { 
	  		JDBCUtil.close(con, ps, rs); //자원반납! 반드시 해줘야 함.
	  	} 
	  }
	 

	public List<BookVO> BookSearch(String condition, String keyword) { // condition 자리에 타이틀, 퍼블리셔 들어갈 수 있음
		// 검색 기능
		// 여러개일 수 있으니까 List - 리턴타입 핸들링 ->

		String sql = "select * from book where " + condition + " like ? order by bookno desc"; // ?양 옆의 %처리는 다른 방법으로 함
		// ? 에는 keyword 들어감??
//		select * from book where title like '%자바%'
//		select * from book where publisher like '%소%'

		// DB연동 할 때 필요한 변수들
		Connection con = null; // 로컬변수니까 널로 초기화
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookVO> list = new ArrayList<BookVO>(); //북 리스트 할 때도 이런 구조 썼었음
		//ArrayList는 List의 관계 -> is a 관계 ★ 굉장히 많이 씀. 
		//왼쪽이 부모, 오른쪽이 자식. 오른쪽엔 List의 자식 모두 허용 - 어레이, 벡터 등...

		try {
			con = JDBCUtil.getConnection(); // JDBCUtil에서 커넥션 얻어옴, 얘가 무조건 커넥션 만듦
			ps = con.prepareStatement(sql); // sql관리해주는 ps생성됨
			// ? 값 세팅
			ps.setString(1, "%" + keyword + "%"); //앞뒤 %처리 해줘야 포함 키워드로 검색됨

			// sql구문 실행 - 둘 중에 하나 골라서 실행한다.
			rs = ps.executeQuery(); //select //실행만 하는 거니까
			// ps.executeUpdate(); //insert, update, delete - 데이터베이스를 '수정'하는거

			// 결과값 처리
			while (rs.next()) { //데이터가 있을 때 = 커서가 내려갈 때
				BookVO vo = new BookVO();
				
				//rs에 있는 객체 꺼내서 vo에 박아줌? ==> BookList에서 썼던 거랑 동일
				vo.setBookno(rs.getInt("bookno"));
				vo.setPrice(rs.getInt("price"));
				vo.setPublisher(rs.getString("publisher"));
				vo.setTitle(rs.getString("title")); // 테이블의 데이터들을 꺼내서 부유 객체에 넣어줌
				list.add(vo); // vo객체를 add해주세요?
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납! 반드시 해줘야 함.

		} return list; //리턴
	}

	public BookVO getBook(int bookno) { // 레코드 하나 셀렉트 - 상세보기 (책 1개에 대한 정보 상세보기 ☞게시판 상세보기 view랑 똑같)
		// BookVO 객체 리턴??

		String sql = "SELECT * FROM book where bookno = ? "; // bookno : PK ? - 변수바인딩 ...?? 물음표로 바인딩하는게 성능, 보안에 좋음
		// 얘를 처리한 다음에 리턴타입을 어떻게 할 것인지..??
		// bookno 5번을 넘겨주면 5번에 해당하는 게 select됨 ==> ResultSet이라 부름 ...

		// DB연동 할 때 필요한 변수들
		Connection con = null; // 로컬변수니까 널로 초기화
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		BookVO vo = null;

		try {
			con = JDBCUtil.getConnection(); // JDBCUtil에서 커넥션 얻어옴, 얘가 무조건 커넥션 만듦
			ps = con.prepareStatement(sql); // sql관리해주는 ps생성됨
			// ? 값 세팅
			ps.setInt(1, bookno);

			// sql구문 실행 - 둘 중에 하나 골라서 실행한다.
			// ps.executeQuery(); //select
			// ps.executeUpdate(); //insert, update, delete
			rs = ps.executeQuery(); // 위에 sql구문 select라서~ / ResultSet이라서??

			// 결과값 처리
			while (rs.next()) { // rs.next가 된다면 데이터를 vo객체에 집어넣을거임
				vo = new BookVO();
				vo.setBookno(rs.getInt("bookno")); // rs에서 데이터 꺼냄
				vo.setPrice(rs.getInt("price"));
				vo.setTitle(rs.getString("title"));
				vo.setPublisher(rs.getString("publisher"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs); // 자원반납! 반드시 해줘야 함.
		}
		return vo;

	}
}
