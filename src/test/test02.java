package test;

import dao.BookDAO_MariaDB;
import vo.BookVO;

public class test02 {

	public static void main(String[] args) {
		BookDAO_MariaDB b = new BookDAO_MariaDB();
		
//		BookVO vo = new BookVO();  //add작업
//		vo.setBookno(5);
//		vo.setTitle("Spring");
//		vo.setPublisher("명지출판사");
//		vo.setPrice(20000);
		
		// b.BookAdd(vo); //add값 -> 등록할 북개체 있어야 함 --> 실행하면 add작업이 되므로 delete할 때 필요없어서 주석처리 해둠
		
		//getBook 부분
		BookVO book = b.getBook(1); 
		if(book != null) {
			System.out.println(book); // 1번인 걸 조회를 함
			book.setPrice(book.getPrice()*2); // 이 객체에서만 데이터가 바뀜..
			b.BookUpdate(book); //DB에 업데이트?
			
			//다시 셀렉트
			BookVO book2 = b.getBook(book.getBookno()); //다시 1번인걸 셀렉트
			System.out.println(book2);
			b.BookDelete(book2.getBookno());
			System.out.println(b.getBook(book2.getBookno()));
			}
		
		
		
		
		b.BookList().forEach(i->{System.out.println(i);}); //여기 디비 내용이 들어와야함?
		//리스트 뽑음
		
		b.BookDelete(2); //bookno 2번을 삭제
		System.out.println("=========목록===========");
		b.BookList().forEach(i->{System.out.println(i);});
		
		
		
		System.out.println("=========검색===========");
		b.BookSearch("title", "j").forEach(i -> {System.out.println(i);}); //(기준, 키워드) & 결과값 리스트-콜렉션->ForEach
		b.BookSearch("title", "광광").forEach(i -> {System.out.println(i);}); //(기준, 키워드) & 결과값 리스트-콜렉션->ForEach
		b.BookSearch("publisher", "한").forEach(i -> {System.out.println(i);}); //(기준, 키워드) & 결과값 리스트-콜렉션->ForEach

	}
		

}
