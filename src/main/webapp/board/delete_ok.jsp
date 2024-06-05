<%@page import="magic.board.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int b_id = Integer.parseInt(request.getParameter("b_id"));
	String b_pwd = request.getParameter("b_pwd");
// 	글번호와 비밀번호를 받아 변수에 저장

	BoardDBBean db = BoardDBBean.getInstance();
	int re = db.deleteBoard(b_id, b_pwd);
// 	글삭제를 위해서 글번호와 비밀번호를 가지고 메소드 호출
	
	if (re == 1) { // 비밀번호 일치로 글목록 이동
		response.sendRedirect("list.jsp");
	} else if (re == 0) { // 비밀번호 불일치
		%>
			<script>
				alert("비밀번호가 맞지 않습니다.");
				history.go(-1);
			</script>
		<%
	} else if (re == -1) { // 삭제 실패
		%>
			<script>
				alert("삭제에 실패하였니다.");
				history.go(-1);
			</script>
		<%
	}
%>