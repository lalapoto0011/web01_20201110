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

@WebServlet("/bookSearch.do")
public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) //public, protected 다 허용
			throws ServletException, IOException { //없는거 붙이면 에러, 있는거 빼도 문제없음 - throw~ 빼도 됨
		
		request.setCharacterEncoding("UTF-8"); // 이거 안 하면 한글 깨짐
		response.setContentType("text/html;charset=utf-8");
		
		//이 데이터를 기준으로 검색을 함
		String condition = request.getParameter("condition");
		String keyword = request.getParameter("keyword");
				
		BookDAO_MariaDB dao = new BookDAO_MariaDB();
		BookService service = new BookServiceImpl(dao);
		List<BookVO> list = service.searchBook(condition, keyword); //데이터 박아줌 ???
		
		//문제없이 검색 가능~!
		request.setAttribute("bookList", list); //list에 데이터가 담겨 있다?
		String page = "/bookList.jsp";

		getServletContext().getRequestDispatcher("/bookList.jsp").forward(request, response); 
		
	
	
	
	
	}

}
