<%@page import="magic.board.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class="magic.board.BoardBean" id="board"></jsp:useBean>
<jsp:setProperty property="*" name="board"/>
<%
	BoardDBBean db = BoardDBBean.getInstance();
	int re = db.editBoard(board);
	
	if (re == 1) { // 수정 성공
		response.sendRedirect("list.jsp");
	} else if (re == 0) { // 비밀번호 불일치
		%>
			<script>
				alert("비밀번호가 맞지 않습니다.");
				history.go(-1);
			</script>
		<%
	} else if (re == -1) { // 수정 실패
		%>
			<script>
				alert("수정에 실패하였습니다..");
				history.go(-1);
			</script>
		<%
	}
%>