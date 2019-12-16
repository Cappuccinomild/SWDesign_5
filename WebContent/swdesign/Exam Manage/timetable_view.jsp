<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head><link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'> <link href='https://fonts.googleapis.com/css?family=Do+Hyeon' rel='stylesheet'> <link href='https://fonts.googleapis.com/css?family=Poor+Story' rel='stylesheet'> <link href='https://fonts.googleapis.com/css?family=Sunflower:300' rel='stylesheet'><meta charset='utf-8'/>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css'>
<link rel='stylesheet' type='text/css' href='./timetable.css'/>
</head>
<body>
<fieldset style='width: 73.8%; margin-left: auto; margin-right: auto; border-style: none;'><div id='container'>

<nav id='topbar'>

</nav>
      

<article>
<jsp:useBean id="MUI" class="swdesign.ManageUI" scope = "application"/>
<jsp:setProperty name = "MUI" property="*"/>
	
	<%
		String Lname = request.getParameter("location_name");
		String date = request.getParameter("day");
		
		
		for(String T : MUI.getETime(Lname, date))
			out.println(T);
	%>

</article>
</div></fieldset>
</body>
</html>