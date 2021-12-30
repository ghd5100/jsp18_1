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
		//db에서 notice의 테이블에 있는 내용 모두를 조회 해서 -> 이곳에 가져오기
		
		String field=request.getParameter("f");
		System.out.println("field: "+field);
		
	//처음게시판 들어가면.. field와 query가 널이들어와서 문제생김 -->
	//쿼리가 완성안됌.
		if(field==null||field.equals("")) {
			field="title";
		}
		
		
		String query=request.getParameter("q");
		System.out.println("query:"+query);
		if(query==null) {
			query="";
		}
		
		
		NoticeDao dao=new NoticeDao();
		 List<Notice> list=dao.noticeSelAll(field,query);
		
		 
		//여기에서 다시 nocideDetail.jsp로 seq를 보내보자. 역으로
		 
		/*List<Notice>를 request에 저장해보쟈.***/
		request.setAttribute("list", list);
		request.setAttribute("query", query);
		
		//리스트라는 이름표로 리스트 내용물을 보내준다
		//위에 담은걸 notice.jsp로 보내면 request도 함께 포워딩~
		/*포워딩하면 자료를 살려서 보내줄수있다. 샌드는 자료가 없어져서 안돼고*/
		
		
		request.getRequestDispatcher("notice.jsp").forward(request, response);
		/*포워드 해주는 것들에는 list .query 가있따. 담아준만큼 다 보내준다*/
	}
}


