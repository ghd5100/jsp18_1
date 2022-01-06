package customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.controller.notice.NoticeController;
import customer.controller.notice.NoticeDetailController;

public class MyDispatcher extends HttpServlet {
	/* 디스패쳐 :중재자 */
	/* 목적 컨트롤러로 다 신호를 들어오게해서 조작하게하려고함 */

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("신호신호");
		/* .Do로 들어오면 여기로 무조건들어옴 http에대한 신호는 여기로 다 들어옴 */
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());

		System.out.println("uri:" + uri);
		System.out.println("conpath:" + conPath);
		System.out.println("com :" + com);

	
		Controller controller=null;
		
		
		
		 
		try {
			if (com.equals("/customer/notice.do")) {
				controller=new NoticeController();
			}else if(com.equals("/customer/noticeDetail.do")) {
				controller=new NoticeDetailController();
			
			}
			
			controller.execute(request, response);
		} catch (Exception e) {

		}
		
	}

}
