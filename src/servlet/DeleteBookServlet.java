package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO_MariaDB;
import service.BookService;
import service.BookServiceImpl;
import vo.BookVO;

@WebServlet("/bookDelete.do")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 이거 안 하면 한글 깨짐, 항상 들어가줘야 함
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=utf-8");
			
		//컨트롤 시프트 o (import) / jsp에서 옮김 -- 데이터 꺼내옴
		BookDAO_MariaDB dao = new BookDAO_MariaDB();
		BookService service = new BookServiceImpl(dao);
		
		//bookVO에 있는 객체들이랑 이름 동일하게 ~~
		//1개가 아니라 다중 삭제될 경우 --> request.getParameter 안 됨??? 똑같은 이름으로 여러개가 넘어오고 있네? 여러개는 무조건 >배열<
		String[] bookno = request.getParameterValues("bookno"); //bookList.jsp에서 name=bookno 로 넘어오는 것
		
		//여러개니까 for루프
		for(String no : bookno) {
			service.bookDelete(Integer.parseInt(no)); //no 객체는 스트링이니까 int로 바꿔줌
		}
		
		response.sendRedirect("bookList.do"); //항상 bookList.do로 이동
	}

}
