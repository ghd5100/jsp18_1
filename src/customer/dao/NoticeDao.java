package customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import customer.db.DBCon;
import customer.vo.Notice;

public class NoticeDao {
	
	public List<Notice> noticeSelAll(String field, String query) throws Exception {
		//보따리 속에 또 보따리르 쌓아야함... 이 매소드는. 리스트에다가 클래스를 담아쥼요!
		
		Connection con=DBCon.getConnection();
		//String sql="select * from notices order by to_number(seq) desc";
		
		String sql="select * from notices where "+field+" like ? order by to_number(seq) desc";
		/*타이틀로 검색할건지 내용으로 검색할건지...field 가 title도되고 content도 되니까 이용*/
		
		//실행
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,"%"+query+"%");
		/*쿼리를 넣는데 대신 %자바%니까 ..%포함되게 */
		
		//결과
		ResultSet rs=pstmt.executeQuery();
		/*pstmt는 익스큐트쿼리괄호에 sql안들어감*/
		
		
		//리스트에 Notice 내용을 담기 *** 잘 이해해보기
		List<Notice> list=new ArrayList<Notice>();
		
		while (rs.next()) { //다음있을떄 까지 계속 돈다. if 절을 안에 만들필요는 없어... while문 자체가 참일떄 계속 도니까.
			Notice n=new Notice();
			n.setSeq(rs.getString("seq"));
			n.setContent(rs.getString("content"));
			n.setHit(rs.getInt("hit"));
			n.setWriter(rs.getString("writer"));
			n.setRegdate(rs.getDate("regdate"));
			n.setTitle(rs.getString("title"));
			list.add(n);
		}
		
		return list;
	}
	
	
	public int delete(String seq) throws Exception {
		
		String sql="delete from notices where seq=?";  //1을 변수인 seq로 넣어줌
		
/*		//dbcon
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="hr";
		String pw="123456";
		Connection con=DriverManager.getConnection(url,user,pw);
		*/
		
		Connection con=DBCon.getConnection(); 
		
		//실행
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, seq);
		pstmt.executeUpdate();

		int del=pstmt.executeUpdate();  
		
		System.out.println("del:"+del);
		
		pstmt.close();
		con.close();
		
		return del;
	}
	
	
	public int write(Notice nc) throws Exception {
		String sql="insert into notices values("
				+"(select max(to_number(seq))+1 from notices)"
				+",?,'cj',sysdate,0,?)";


/*				//dbconnect
				Class.forName("oracle.jdbc.driver.OracleDriver");

				String url="jdbc:oracle:thin:@localhost:1521:xe";
				String user="hr";
				String pw="123456";
				Connection con=DriverManager.getConnection(url,user,pw);
*/
				Connection con=DBCon.getConnection();
				
				//실행
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1, nc.getTitle());
				pstmt.setString(2, nc.getContent());
				int af=pstmt.executeUpdate(); //인서트 실행... 
	
				pstmt.close();
				con.close();
				
				return af;
	}
	
	public int update2(String s,String t,String c) throws Exception{
	//변수로 하나하나 받기
	
		String sql="update notices"
					+" set title=?,content=? where seq=?";
	
/*				 
					//dbconnect
					Class.forName("oracle.jdbc.driver.OracleDriver");
	
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user="hr";
					String pw="123456";
					Connection con=DriverManager.getConnection(url,user,pw);
	*/
					
					Connection con=DBCon.getConnection();
					
					//실행
	
					PreparedStatement pstmt=con.prepareStatement(sql);
					pstmt.setString(1, t);
					pstmt.setString(2, c);
					pstmt.setString(3, s);
					int af=pstmt.executeUpdate(); 
		
					pstmt.close();
					con.close();
		
		return af;
		
	}	
	
	
	
	public int update(Notice notice) throws Exception{
	//객체로 받기=오브젝트로 받기
	
		String sql="update notices"
					+" set title=?,content=? where seq=?";
	
				 
/*					//dbconnect
					Class.forName("oracle.jdbc.driver.OracleDriver");
	
					String url="jdbc:oracle:thin:@localhost:1521:xe";
					String user="hr";
					String pw="123456";
					Connection con=DriverManager.getConnection(url,user,pw);
	*/
					
					Connection con=DBCon.getConnection();
					
					
					//실행
	
					PreparedStatement pstmt=con.prepareStatement(sql);
					pstmt.setString(1, notice.getTitle());
					pstmt.setString(2, notice.getContent());
					pstmt.setString(3, notice.getSeq());
					int af=pstmt.executeUpdate(); 
		
					
					pstmt.close();
					con.close();
					
					return af;
		
		//리턴을 만나면 문장끝이되므로 리턴은 맨 마지막 라인에 써줘야해
		
	}	
	public Notice getNotice(String seq) throws Exception {

		String sql="select * from notices where seq="+seq;  //1을 변수인 seq로 넣어줌

/*		//dbcon
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="hr";
		String pw="123456";
		Connection con=DriverManager.getConnection(url,user,pw);*/

		Connection con=DBCon.getConnection();
		
		
		//실행
		Statement st=con.createStatement();

		ResultSet rs=st.executeQuery(sql);

		rs.next();
		
		
		/*Notice에 sql결과 rs를 담아보기*/
		Notice n=new Notice();
		n.setSeq(rs.getString("seq"));
		n.setWriter(rs.getString("writer"));
		n.setTitle(rs.getString("title"));
		n.setContent(rs.getString("content"));
		n.setRegdate(rs.getDate("regdate"));
		n.setHit(rs.getInt("hit"));
		
		
		//끊어줄떄 가장 마지막으로 역순으로 끊어주기
		rs.close();
		st.close();
		con.close();
		
		return n;
	}
}
