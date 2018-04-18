<%@ page import="kr.co.bit.dao.GuestBookDAO" %>
<%@ page import="kr.co.bit.vo.GuestBookVO" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	int no = Integer.parseInt(request.getParameter("no"));
	GuestBookDAO dao = new GuestBookDAO();
	GuestBookVO vo = dao.searchContent(no);

	request.setAttribute("vo",vo);

%>
	<form action="Controller?cmd=delete&no=<%=vo.getNo()%>" method="post">
	<input type='hidden' name="id" value="">
	<table>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password"></td>
			<td><input type="submit" value="확인"></td>
			<td><a href="./list.jsp">메인으로 돌아가기</a></td>
		</tr>
	</table>
	</form>
</body>
</html>