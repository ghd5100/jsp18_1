<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>logoutProc.jsp</h3>

<%
//세션제거
//모든세션 한번에 다 끊기 인베리데이트매소드                   
session.invalidate();
response.sendRedirect("loginForm.jsp");
%>

</body>
</html>