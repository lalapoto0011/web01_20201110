package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.BookDAO_MariaDB;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

class BookTest {

	BookService service = null; //service;는 service=null;이랑 같음..
	//주소가 들어가야되니까 null을 줌 ????
	
	@BeforeEach
	void setUp() throws Exception {
		
		BookDAO_MariaDB dao = new BookDAO_MariaDB(); 
		service = new BookServiceImpl(dao); //impl이 만들어짐
		
		//생성자를 통한 dao 주입 - 컨스트럭셔 인덱션
	}
	
	
	// 기능별로 테스트 가능
	
//	@Test
	void list() { //웹에서는 목록보기를 누르면 데이터가 웹브라우저 화면에 뿌려짐
	//	service.bookList().forEach(i -> {System.out.println(i);} ); //디비에 있는 북리스트 들어옴
		//여기 리턴되는게 리스트
		//Lambda식
		
		List<BookVO> list = service.bookList(); //bookList 결과값이 list로 들어감
		
		for(BookVO data : list) {
			System.out.println("------------------");
			System.out.printf("%s|%d|%s %n", data.getTitle(), data.getPrice(), data.getPublisher());
			System.out.println("------------------");
			//웹이라면 테이블 구조에 담음
		}
	}
	
	@Test
	void add() { //웹에서는 -> 데이터 등록폼에 있을 것. 등록버튼 누르면 DB에 insert되는 것
		BookVO vo = new BookVO(); //BookVO 객체 필요
//		vo.setBookno(10); //bookno는 자동으로 들어가주는 거니까 필요없음
		vo.setTitle("테스트중");
		vo.setPublisher("후");
		vo.setPrice(20000);
		
		service.bookAdd(vo); //DB에 해당 데이터 들어가야 함...
	}
	
//	@Test
	void getBook() { 
		System.out.println(service.getBook(8)); //8번 책을 출력해라
	}
	
//	@Test
	void delete() {
		BookVO vo = service.getBook(3);
		if (vo != null) {
			System.out.println(vo);
			service.bookDelete(vo.getBookno());
			System.out.println("===변경 후=+=");
			System.out.println(vo);
		}
		
		
	}
	
//	@Test
	void update() {
		BookVO vo = service.getBook(8);
		if (vo != null) {
			System.out.println(vo); //먼저 8번 책정보 보여주기
			vo.setPrice(10); //가격 10원으로 설정
			service.bookUpdate(vo); //업데이트(가격 바꿈)
			System.out.println(service.getBook(vo.getBookno())); //바뀐 가격으로 정보 업데이트되어 보여짐
		}
		
	}
	
//	@Test
	void search() {
		System.out.println("+++검색+++");
		
		List<BookVO> list = service.searchBook("title", "J"); //title에서 J가 포함된 걸 검색한다
		//검색결과가 list 안에 들어 있음...
		
//		list.forEach(i -> {System.out.println(i);} ); //lambda
		
		for(BookVO data : list) { //list 안에 들어간 데이터가 BookVO 타입이야
//			System.out.println(data.getTitle());
			System.out.printf("%s:%d:%s %n", data.getTitle(), data.getPrice(), data.getPublisher());
		}					//문자열:정수:문자열 줄바꿈 형태로 출력하겠다 - 가격이 정수형이니까 %d 써줌
	}

}
