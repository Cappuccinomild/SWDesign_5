<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="main" class="swdesign.Main" scope = "application"/>
	<jsp:setProperty name = "main" property="*"/>
	<%
		main = null;
	%>
	<script>alert('�α׾ƿ� �Ǿ����ϴ�.');location.replace('../index.html')</script>
</body>
</html>