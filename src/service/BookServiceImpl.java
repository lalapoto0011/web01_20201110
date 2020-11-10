package service;

import java.util.List;

import dao.BookDAO_MariaDB;
import vo.BookVO;

public class BookServiceImpl implements BookService {
	
	private BookDAO_MariaDB dao = null; //얘가 있어야 디비연동 가능
	//주소가 들어가줘야 함?
	
	
	
	@Override
	public List<BookVO> bookList() {
		return dao.BookList();
	}

	public BookServiceImpl() {
//		super();	//지워도 자동으로 호출 됨
	}

	public BookServiceImpl(BookDAO_MariaDB dao) {
//		super();
		this.dao = dao;
	}

	public BookDAO_MariaDB getDao() {
		return dao;
	}

	public void setDao(BookDAO_MariaDB dao) {
		this.dao = dao;
	}

	@Override
	public void bookAdd(BookVO vo) { //insert
		dao.BookAdd(vo);
	}

	@Override
	public void bookDelete(int bookno) {
		dao.BookDelete(bookno);
	}

	@Override
	public void bookUpdate(BookVO vo) {
		dao.BookUpdate(vo);
	}

	@Override
	public BookVO getBook(int bookno) {
		return dao.getBook(bookno);
	}

	@Override
	public List<BookVO> searchBook(String condition, String keyword) {
		return dao.BookSearch(condition, keyword);
	}

}
