package customer.controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.controller.Controller;
import customer.dao.NoticeDao;
import customer.vo.Notice;

public class NoticeDetailController implements Controller {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("NoticeController~");

		
		String seq=request.getParameter("c");
		String hit=request.getParameter("h");
		
		NoticeDao dao=new NoticeDao();
		List<Notice> list=dao.noticeSelAll();
		Notice n=dao.getNotice(seq,hit);
		 
	
		/*List<Notice>를 request에 저장해보쟈.***/
		//목적은: 검색하고나면 검색기록이 안사라지도록 하기위함이다.
		request.setAttribute("n", n);
		request.getRequestDispatcher("noticeDetail.jsp").forward(request, response);
		/*포워드 해주는 것들에는 list .query 가있따. 담아준만큼 다 보내준다*/
		
	}
}


