<%@page import="java.net.InetAddress"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="magic.board.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class="magic.board.BoardBean" id="board"></jsp:useBean>
<jsp:setProperty property="*" name="board"/>
<%
	InetAddress address = InetAddress.getLocalHost(); // 자바 클래스 이용해서 ip 추가
	String ip = address.getHostAddress(); // getHostAddress() -> ip 주소 가져오는 메소드
	
	BoardDBBean db = BoardDBBean.getInstance();
	board.setB_date(new Timestamp(System.currentTimeMillis())); // 오늘 날짜 추가
// 	board.setB_ip(request.getRemoteAddr()); //ip 추가 0:0:0:0:0:0:0:1
	board.setB_ip(ip); // 192.168.0.119
	if(db.insertBoard(board) == 1) { // 글 쓰기가 정상적으로 완료
		out.print("글쓰기 성공");
	} else {
		out.print("글쓰기 실패");		
	}
%>