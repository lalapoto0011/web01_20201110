package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO_MariaDB;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

@WebServlet("/addBook.do")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 이거 안 하면 한글 깨짐, 항상 들어가줘야 함
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=utf-8");
			
		//컨트롤 시프트 o (import) / jsp에서 옮김 -- 데이터 꺼내옴
		BookDAO_MariaDB dao = new BookDAO_MariaDB();
		BookService service = new BookServiceImpl(dao);
		
		//bookVO에 있는 객체들이랑 이름 동일하게 ~~
		String title = request.getParameter("title");
		String publisher = request.getParameter("publisher");
		int price = Integer.parseInt(request.getParameter("price"));
		
		BookVO vo = new BookVO(); //이 객체에 request에 있는 데이터 다 담아야 함
		vo.setPrice(price);
		vo.setTitle(title);
		vo.setPublisher(publisher);
		
		service.bookAdd(vo);
		//등록이 된 다음에 다시 리스트로?
		response.sendRedirect("bookList.do"); //bookList.do로 이동
	
	}

}
