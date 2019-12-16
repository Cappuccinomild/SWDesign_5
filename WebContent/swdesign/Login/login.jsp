<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head><link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'> <link href='https://fonts.googleapis.com/css?family=Do+Hyeon' rel='stylesheet'> <link href='https://fonts.googleapis.com/css?family=Poor+Story' rel='stylesheet'> <link href='https://fonts.googleapis.com/css?family=Sunflower:300' rel='stylesheet'>\n<meta charset='utf-8'/>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css'>
</head>
<body>
<fieldset style='width: 73.8%; margin-left: auto; margin-right: auto; border-style: none;'><div id='container'>

<nav id='topbar'>
<ul>
  <li><a href='../Login/logout.jsp'>로그아웃</a></li>
</ul>
</nav>
      <div id='logo'><a href = '/Course List/main_view.jsp'><img src='../logo.jpg' alt='경북대학교 시험관리 시스템' title='logo' style='width: 500px; margin-bottom: 20px;' /></a></div>

<article>

<jsp:useBean id="main" class="swdesign.Main" scope = "application"/>
<jsp:setProperty name = "main" property="*"/>

<%
	
	String user = request.getParameter("user");
	String ID = request.getParameter("login_id");
	String pwd = request.getParameter("login_passwd");

	if(user.equals("S")){//student
		if(main.Student_session(ID, pwd))
			out.println("<script>location.replace('../Course List/main_view.jsp');</script>");
		else
			out.println("<script>history.back();</script>");
	}else{
		if(main.Professor_session(ID, pwd))
			out.println("<script>location.replace('../Course List/main_view.jsp');</script>");		
		else
			out.println("<script>history.back();</script>");
	}

%>

</article>
</div></fieldset>
</body>
</html>