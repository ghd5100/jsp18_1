package customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// 어디서 실행해서 .do해서 들어오냐에 따라 uri와 conpath가 달라짐
		/*
		 * 나는지금 notice.jsp에서 들어와서 경로가 이렇게 나오는것임. uri:/jspwebm10/customer/notice.do
		 * conpath:/jspwebm10
		 */
		
		System.out.println("uri:" + uri);
		System.out.println("conpath:" + conPath);
		System.out.println("com :" + com);

		//모두 인터페이스 상속받았으니까 무도 타입으로 넣어주기
	
/*		
		try {
			if (com.equals("/customer/noticeDetail.do")) {
			}
		} catch (Exception e) {

		}
		*/
		
	}

}
