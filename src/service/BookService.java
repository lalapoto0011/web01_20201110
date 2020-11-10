package service;

import java.util.List;

import vo.BookVO;

public interface BookService { //미완성이라 객체 생성 불가능 (추상 메소드라서)
	//public 안 써도 자동으로 public임, 안 쓰면 생략됨
	
	//추상메소드 만들어둠
	//abstract 생략됨
	public List<BookVO> bookList(); //메소드 리턴타입에 대해 언급
				
	public void bookAdd(BookVO vo); //데이터를 넘겨주면서 add 요청해야 함
							//BookVO 객체로 넘겨라
	
	public void bookDelete(int bookno); //뭐 삭제할건지 정보 하나 넘겨줘(괄호 안 내용)

	public void bookUpdate(BookVO vo); //데이터 여러개 넘어올 수 있으니까 BookVO vo 로
	
	public BookVO getBook(int bookno); //BookVO 타입을 리턴, bookno로 특정지음
	
	public List<BookVO> searchBook(String condition, String keyword); //파라미터 두개가 필요해(괄호 안 내용)
	//리턴타입이 List<BookVO> ?? 아마 리스트가 리턴타입인듯..
}