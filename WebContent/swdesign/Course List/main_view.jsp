<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head><link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'> <link href='https://fonts.googleapis.com/css?family=Do+Hyeon' rel='stylesheet'> <link href='https://fonts.googleapis.com/css?family=Poor+Story' rel='stylesheet'> <link href='https://fonts.googleapis.com/css?family=Sunflower:300' rel='stylesheet'><meta charset='utf-8'/>
<link rel='stylesheet' type='text/css' href='../style.css'/>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css'>
</head>
<body>
<fieldset style='width: 73.8%; margin-left: auto; margin-right: auto; border-style: none;'><div id='container'>

<nav id='topbar'>
<ul>
  <li><a href='../Login/logout.jsp'>�α׾ƿ�</a></li>
</ul>
</nav>
      <div id='logo'><a href = '../Course List/main_view.jsp'><img src='../logo.jpg' alt='��ϴ��б� ������� �ý���' title='logo' style='width: 500px; margin-bottom: 20px;' /></a></div>

<article>
<jsp:useBean id="main" class="swdesign.Main" scope = "application"/>
<jsp:setProperty name = "main" property="*"/>
<h1>���� ���</h1>
<table id = 'maintable'>
	<tr style=' border : 1px solid #808080; padding: 10px;' id=course_title>
		<th>����</th>
		<th>����</th>
		<th>���ǽ�</th>
		<th>���� �ð�</th>
		<th>���� �ð�</th>
		<th>����</th>
		<th></th>
	</tr>
	
	<%
		
		for(String course : main.GetData(1)){
			out.println(course);
		}
	if(main.user.equals("P"))
		out.println("<script>"+
					"var size = document.getElementsByName('manage').length;"+
				    "for(var i = 0; i < size; i++)"+
				    "    document.getElementsByName('manage')[i].style.display = 'block';</script>");
	%>
	
</table>
<h1>���� �ð�</h1>
<table id = 'maintable'>
    <tr style=' border : 1px solid #808080; padding: 10px;' id=course_title>
	    <th>���ǹ�ȣ</th>
	    <th>���Ǹ�</th>
	    <th>���ǽ�</th>
	    <th>���� �ð�</th>
	    <th>���� �ð�</th>
	    <th>����</th>
	</tr>
	
	<%
	for(String exam : main.GetData(2))
		out.println(exam);
	%>
	
</table>
<table id = 'maintable' width='100%' cellpadding='5' cellspacing='2' align='center' style='table-layout:fixed; word-break:break-all;'>
	<tr>
	  <td width = '6%'></td>
	  <td>��</td>
	  <td>��</td>
	  <td>ȭ</td>
	  <td>��</td>
	  <td>��</td>
	  <td>��</td>
	  <td>��</td>
	</tr>
	<%
		for (String table : main.GetData(3))//table ����
			out.println(table);
	%>
	
</table>
</article>
</div></fieldset>
</body>
</html>