package vo; //VO 대신 DTO, bookRDC가 들어가기도 함.. 같은 말임

//BoardVO, UserVO 등 만들 수 있겠다.
public class BookVO {
	private int bookno ; //int(4) PRIMARY KEY auto_increment,
	private String title ; //VARCHAR(40),
	private String publisher ; //VARCHAR(40),
	private int price ; //int(8)

	public BookVO() {
		super(); //생략해도 자동으로 들어가긴 함.
	} 
	
	public BookVO(int bookno, String title, String publisher, int price) { //bookno값은 int고 title은 String이니까
		super();
		this.bookno = bookno;
		this.title = title;
		this.publisher = publisher;
		this.price = price;
	}
	
	public int getBookno() {
		return bookno;
	}
	
	public void setBookno(int bookno) {
		this.bookno = bookno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	


	@Override
	public String toString() {
		return "BookVO [bookno=" + bookno + ", title=" + title + ", publisher=" + publisher + ", price=" + price + "]";
	}
	
	
	
	
}
