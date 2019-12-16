<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="MUI" class="swdesign.ManageUI" scope = "application"/>
<jsp:setProperty name = "MUI" property="*"/>

<%

	String course_number = request.getParameter("course_number");
	String day = request.getParameter("day");
	String class_room = request.getParameter("class_room");
	String location = request.getParameter("location");
	String stime = request.getParameter("stime");
	String ftime = request.getParameter("ftime");
	
	
	MUI.setExam(course_number, class_room, stime, ftime, day);
	
	if(MUI.submit())
		out.println("<script>alert('success!');</script>");
	else{
		out.println("<script>alert('겹치는 시간');</script>");
	}

%>
</body>
</html>