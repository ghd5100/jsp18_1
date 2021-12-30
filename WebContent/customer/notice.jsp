<%@page import="customer.vo.Notice"%>

<%-- 노티스 클래스 오류나면 이거 직접 넣어줘야함
<%@page import="customer.vo.Notice"%>
 --%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function claerVal() { 
	//이 도큐먼트...가 헷갈리네..물어봐야겠따.
	var inputVal=document.getElementById("q");
	inputVal.value='';
}

</script>
</head>
<body>
<h3>Notice.jsp</h3>


<!-- 버튼눌러서 주소창 보면... 주소가 중복되서 들어오므로 customer/notice.do하면 에러남 보니까 
customer/customer/notice.do로 들어와서 중복됨 그래서 그냥  notice.do 로 해줘야함
결과 주소창을보면 알수있음
/루트가 사라지면 /는 뺴줘야하고 
 -->


<form action="notice.do" method="get">
	<!-- 내용이냐 타이틀이냐? -->
	<select name="f" >
	<!-- 
	제목을 카테고리하고 검색하면 f에 title이 들어감
	내용을 카테고리하고 검색하면 f에 content이 들어감
	즉 뭘 선택하냐를 구분할수가있음.
	f가 파람값으로 전달되니까 전달되는 페이지에서 받아줄수있음
	 -->
	 <!-- 삼항연산자로 검색했던거 계속 유지되게해줌 ""는 아무것도 안함 -->
		<option ${param.f=="title"?"selected":"" } value="title">제목</option>
		<option ${param.f=="content"?"selected":"" } value="content">내용</option>
	</select>
	<input type="text" name="q" value="${query }" id="q" onclick="clearVal();" />
	<!-- 쿼리는 위에것을로 받을예정  id="q" 와 온클릭은 검색후 내용지울때 목적으로 멩금  -->
	<input type="submit" value="검색" />
</form>
<br />


<table width="500" border="1">

<tr> 				<!-- tr행 -->
	<td>번호</td>    <!-- td열 -->
	<td>제목</td>
	<td>작성자</td>
	<td>작성일</td>
	<td>조회수</td>
	<td>내용</td>
</tr>

<%-- <% 

/***  <!-- http://localhost:8700/jspCrypt6/login/Loginprocaa.do?id=blue&pw=
위에서 .do?id=blue&pw= 물음표 뒤에 이부분을 봐랑 &이것도 어디에 붙는지 봐둬라. 이거를 이해하고 이 값들이 어디로 움직이는지를 파악하긔 이 프로젝트의 핵심중점.
? 뒤에가 id 니까 이거 받으려면 
 -->
 */
 
 //자바코드를 사용 할 수 있음
 /* 여기 list 는 setattribete에서 명명해준 list로  이름이 같아야한다. */
List<Notice> list=(List<Notice>)request.getAttribute("list");

for(Notice n:list){
	pageContext.setAttribute("n", n);
%>	
 --%>

<!-- 코어테그를 써보겠다 -->
<!-- 노티스에서 리스트 내용 하나하꺼내서 넣어줌 -->
<!-- 참고: jstlsample/jstl2_foreach 파일 -->
<!-- n이 객체니까. 객체속에 필드이름으로 가져온다 -->
<!-- java015_bo2에서 carTest.java 참고 -->


<c:forEach var="n" items="${list }" >

<tr> <!-- 두번쨰 행 -->
	<td>${n.seq }</td>
	<!-- noticeDetail.jsp를 do로 바꿔줘야함 -->
	<td><a href="noticeDetail.do?c=${n.seq }">${n.title }</a></td>
	<!-- 노티스 게시판에서 타이틀을 눌러야 .do를 통해 디스패쳐페이지로가고  seq도 받아갈수있다 -->
	<!-- 동적으로 title1에 가져다대면 2번으로 가고 title2에 가져다대면 1번으로 간다. --> <!-- 파람이름이 c임 . 물음표 뒤에 있는걸로 파람으로 받아줘야함. -->
	<td>${n.writer }</td>
	<td>${n.regdate }</td>
	<td>${n.hit }</td>
	<td>${n.content }</td>
</tr>
</c:forEach>


<%-- 
<%
}
%>

 --%>
</table>

<a href="noticeReg.do">write</a>

</body>
</html>

<!-- 실행은 index.jsp로 부터 들어가야함 -->
