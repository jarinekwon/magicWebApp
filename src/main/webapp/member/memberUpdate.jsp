<%@page import="magic.member.MemberBean"%>
<%@page import="magic.member.MemberDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String uid = (String)session.getAttribute("uid");
	MemberDBBean manager = MemberDBBean.getInstance();
	MemberBean member = manager.getMember(uid);
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<table border="1" align="center">
		<form name="upd_frm" method="post" action="memberUpdateOk.jsp">
			<tr height="50">
				<td colspan="2" align="center">사용자 ID</td>
					<h1>회원 정보 수정</h1>
					'*' 표시 항목은 필수 입력 항목입니다.
				</td>
			</tr>
			<tr height="30">
				<td width="80">User ID</td>
				<td>
					<%= uid %>
				</td>
			</tr>
			<tr height="30">
				<td width="80">암호</td>
				<td>
					<input type="password" name="mem_pwd" size="20">*
				</td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td>
					<input type="password" name="pwd_check" size="20">*
				</td>
			</tr>
			<tr height="30">
				<td width="80">이	름</td>
				<td>
					<%= member.getMem_name() %>
				</td>
			</tr>
			<tr height="30">
				<td width="80">E-mail</td>
				<td>
					<input type="text" name="mem_email" size="30" value="<%= member.getMem_email() %>">*
				</td>
			</tr>
			<tr height="30">
				<td width="80">주	소</td>
				<td>
					<input type="text" name="mem_addr" size="20" value="<%= member.getMem_addr() %>">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정" onclick="update_check_ok()">			
					<input type="reset" value="다시입력">
					<input type="button" value="수정안함" onclick="location='main.jsp'">			
				</td>
			</tr>
		</form>
	</table>
</body>
</html>