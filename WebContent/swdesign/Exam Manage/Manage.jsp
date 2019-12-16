<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Do+Hyeon'
	rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Poor+Story'
	rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Sunflower:300'
	rel='stylesheet'>
<meta charset='utf-8' />
<link rel='stylesheet' type='text/css' href='../style.css' />
<meta charset="EUC-KR">
<title>Insert title here</title>

<script>
function send(){
	
    var day = document.getElementById('day');
    var location = document.getElementById('location');
    var classroom = document.getElementById("i"+String(location.value));
    var stime = document.getElementById('stime');
    var ftime = document.getElementById('ftime');
    var course = document.getElementById('course');
    var link = '';
   	
    link = 'course_number=' + course.value + '&day=' + day.options[day.selectedIndex].value + '&class_room=' + classroom.options[classroom.selectedIndex].value.replace(' ', '+')
    + '&location=' + location.options[location.selectedIndex].value + '&stime=' + stime.value
    + '&ftime=' + ftime.value;
	
    window.location.href = window.location.href.replace('Manage.jsp', 'submit.jsp?' + link);
}
</script>

<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css'>
</head>
<body>
	<fieldset
		style='width: 73.8%; margin-left: auto; margin-right: auto; border-style: none;'>
		<div id='container'>

			<nav id='topbar'>
				<ul>
					<li><a href='../Login/logout.jsp'>로그아웃</a></li>
				</ul>
			</nav>
			<div id='logo'>
				<a href='../Course List/main_view.jsp'><img src='../logo.jpg'
					alt='경북대학교 시험관리 시스템' title='logo'
					style='width: 500px; margin-bottom: 20px;' /></a>
			</div>

			<article>
				<jsp:useBean id="main" class="swdesign.Main" scope="application" />
				<jsp:setProperty name="main" property="*" />

				<iframe name='timetable'
					src='timetable_view.jsp?day=2&location_name=1&classroom=1+345'
					width='100%' height='200px' frameborder=0 framespacing=0
					marginheight=0 marginwidth=0 scrolling=no vspace=0
					style='margin-left: auto; margin-right: auto;'100%''></iframe>

				<jsp:useBean id="MUI" class="swdesign.ManageUI" />
				<jsp:setProperty name="MUI" property="*" />

				<form action='./timetable_view.jsp' target='timetable' method='GET'>
					<div>
						<p style='text-align: center;'>시험 일정 선택</p>
						<table
							style='width: 60%; background-color: #ffffff; margin-left: auto; margin-right: auto; border-radius: 5px; height: 500px; border-top: solid; border-bottom: solid;'>
							<tr>
								<td><label for='course_name'
									style='font-family: 휴먼모음T; font-size: 20px; color: #000000; float: left;'>강의명</label></td>

								<td class='text' style='margin-top: 10px;'>
									<input type = hidden id = 'course' value = '<%out.println(request.getParameter("course_name"));%>'> 
									
									<%
										out.println(request.getParameter("course_number"));
									%>
								</td>

							</tr>
							<tr>

								<td><label for='day'
									style='font-family: 휴먼모음T; font-size: 20px; color: #000000; float: left;'>요일</label></td>

								<td><select name='day' id='day'>
										<option value='1'>일</option>
										<option value='2'>월</option>
										<option value='3'>화</option>
										<option value='4'>수</option>
										<option value='5'>목</option>
										<option value='6'>금</option>
										<option value='7'>토</option>
								</select></td>
							</tr>

							<tr>

								<td><label for='location_name'
									style='font-family: 휴먼모음T; font-size: 20px; color: #000000; float: left;'>건물번호</label></td>
								<script>
									var bid = 1;
									function deepCopy(obj) {
										if (obj === null || typeof(obj) !== "object") {
											return obj;
										}
										    
										let copy = {};
										for(let key in obj) {
											copy[key] = deepCopy(obj[key]);
										}
										return copy;
									}
									function change(){
										
										var langSelect = document.getElementById("location");
										var id = langSelect.options[langSelect.selectedIndex].value;
										
										document.getElementById(id).style.display = "block";
										document.getElementById(bid).style.display = "none";
										
										bid = deepCopy(id);
										
									}
								</script>
								<td><select onchange='change()' name='location_name'
									id='location'>
										<%
											for (String L : MUI.getLocation())
												out.println(L);
										%>
								</select></td>

							</tr>

							<tr>

								<td><label for='classroom'
									style='font-family: 휴먼모음T; font-size: 20px; color: #000000; float: left;'>강의실</label></td>

								<td>
									<%
										for (String C : MUI.getClassRoom())
											out.println(C);
									%>
									<script>document.getElementById('1').style.display = "block";</script>
								</td>

							</tr>

							<tr>
								<td><label for='classroom'
									style='font-family: 휴먼모음T; font-size: 20px; color: #000000; float: left;'>시작시간</label></td>
								<td><input type='time' id='stime'></input></td>
							</tr>
							<tr>
								<td><label for='classroom'
									style='font-family: 휴먼모음T; font-size: 20px; color: #000000; float: left;'>종료시간</label></td>
								<td><input type='time' id='ftime'></input></td>

							</tr>

							<tr>
								<td><input type='submit' value='조회' id='submit-btn'
									style='width: 100%;' />
								</form></td>
								<td><input type='submit' value='예약하기' id='submit-btn'
									onclick='send()' style='width: 100%;' /></td>

							</tr>
						</table>
					</div>
			</article>
		</div>
	</fieldset>
</body>
</html>