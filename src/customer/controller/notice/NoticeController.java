package customer.controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.controller.Controller;
import customer.dao.NoticeDao;
import customer.vo.Notice;

public class NoticeController implements Controller {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("NoticeController~");

		
		//  search
		String field=request.getParameter("f");
		System.out.println("field : "+field);
		if (field==null || field.equals("")) 
			field="title";
		
		String query=request.getParameter("q");
		System.out.println("query : "+query);
		
		if (query==null)
			query="";
		
		
		NoticeDao dao=new NoticeDao();
		 List<Notice> list=dao.noticeSelAll();
		
	
		/*List<Notice>를 request에 저장해보쟈.***/
		//목적은: 검색하고나면 검색기록이 안사라지도록 하기위함이다.
		request.setAttribute("list", list);

		

		
		/*포워딩하면 자료를 살려서 보내줄수있다. 샌드는 자료가 없어져서 안돼고*/
		request.getRequestDispatcher("/WebContent/customer/upload/notice.jsp").forward(request, response);
		/*포워드 해주는 것들에는 list .query 가있따. 담아준만큼 다 보내준다*/
		
	}
}


