package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/bookList.do") //이 이름으로 요청이 들어오면 해당 서비스 메소드가 실행됨, get post 상관없이
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setCharacterEncoding("UTF-8"); // 이거 안 하면 한글 깨짐
		response.setContentType("text/html;charset=utf-8");
			
		//컨트롤 시프트 o (import) / jsp에서 옮김 -- 데이터 꺼내옴
		BookDAO_MariaDB dao = new BookDAO_MariaDB();
		BookService service = new BookServiceImpl(dao);
		List<BookVO> list = service.bookList(); 
		
		//데이터 출력을 해봅시다 ...
//		PrintWriter out = response.getWriter();
//		list.forEach(i -> { System.out.println(i); }); //콘솔창에 찍힘
//		list.forEach(i -> { out.println("<h3>"+i+"</h3>"); }); //out : 웹브라우저를 의미. -> 웹브라우저에 뿌려짐.
		
		//데이터 공유
		request.setAttribute("bookList", list); //list에 데이터가 담겨 있다?
		String page = "/bookList.jsp";

		getServletContext().getRequestDispatcher("/bookList.jsp").forward(request, response); 
		
		
		
	
	}

}
