package magic.member;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDBBean {
//jsp 소스에서 MemberDBBEAN 객체 생성을 위한 참조변수
	private static MemberDBBean instance = new MemberDBBean();	
	
	public static MemberDBBean getInstance() {
//	MemberDBBean 객체 레퍼런스를 리턴하는 메소드
		return instance;
	}
	
	public Connection getConnection() throws Exception {
//	쿼리 작업에 사용할 커넥션 객체를 리턴하는 메소드(dbcp 기법)
		return ((DataSource)(new InitialContext().lookup("java:comp/env/jdbc/mysql"))).getConnection();
	}
	
//	public int insertMember(String uid) {
	public int insertMember(MemberBean member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int re = -1; // 초기값 -1, insert 정상적으로 실행되면 1
//		String sql = "insert into memberT values('abcd2', 'a1234', 'a222', 'abcd2@a.com', '서울')";
//		String sql = "insert into memberT values(?, 'a1234', 'a222', 'abcd2@a.com', '서울')";
		String sql = "insert into memberT values(?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
//			dbcp 기법의 연결 객체
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMem_uid());
			pstmt.setString(2, member.getMem_pwd());
			pstmt.setString(3, member.getMem_name());
			pstmt.setString(4, member.getMem_email());
			pstmt.setString(5, member.getMem_addr());
			re = pstmt.executeUpdate();
//			insert 문은 executeUpdate 메소드 호출
			
		} catch(Exception ex){
		 	ex.printStackTrace();
		} finally{
			try {
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}
			catch (Exception se){
				se.printStackTrace();
			}
		}
		return re;
	}
	
	public int confirmID(String id) {
//	회원 가입시 아이디 중복 확인할 때 사용하는 메소드

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int re = -1;
		String sql = "select mem_uid, mem_pwd, mem_name, mem_email, mem_addr from memberT where mem_uid=?";
//		매개변수로 받은 id를 ?인 쿼리 파라미터에 매핑
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
//			select 문은 executeQuery 메소드 호출
			
			if (rs.next()) { // 아이디가 일치하는 로우 존재
				re = 1;
			} else { // 해당 아이디가 존재하지 않음
				re = -1;
			}
		} catch(Exception ex){
		 	ex.printStackTrace();
		} finally{
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}
			catch (Exception se){
				se.printStackTrace();
			}
		}
		return re;
	}
	
	public int userCheck(String id, String pwd) {
//	사용자 인증시 사용하는 메소드
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int re = -1;
		String db_mem_pwd = "";
		String sql = "select mem_pwd from memberT where mem_uid=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
				
			if (rs.next()) {
				db_mem_pwd = rs.getString("mem_pwd");
				if (db_mem_pwd.equals(pwd)) { // 패스워드 일치
					re = 1;
				} else { // 패스워드 불일치
					re = 0;
				}
			} else { 
				re = -1;
			}
		} catch(Exception ex){
		 	ex.printStackTrace();
		} finally{
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}
			catch (Exception se){
				se.printStackTrace();
			}
		}
		return re;	
	}
	public MemberBean getMember(String id) {
//	아이디가 일치하는 멤버의 정보를 얻어오는 메소드
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int re = -1; // 초기값 -1, 비밀번호가 일치하면 1, 비밀번호가 불일치하면 0
		String sql = "select mem_uid, mem_pwd, mem_name, mem_email, mem_addr from memberT where mem_uid=?";
		MemberBean member = null;
			
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
				
			if (rs.next()) { 
				member = new MemberBean();
				member.setMem_uid(id);
				member.setMem_pwd(rs.getString("mem_pwd"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_addr(rs.getString("mem_addr"));
			} 
		} catch(Exception ex){
		 	ex.printStackTrace();
		} finally{
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}
			catch (Exception se){
				se.printStackTrace();
			}
		}
		return member;
	}
	
	public int updateMember(MemberBean member) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int re = -1; 
		String sql = "update memberT set mem_pwd=?, mem_email=?, mem_addr=? where mem_uid=?";

				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("@# getMem_pwd() =>" + member.getMem_pwd());
			System.out.println("@# getMem_email() =>" + member.getMem_email());
			System.out.println("@# getMem_addr() =>" + member.getMem_addr());
			System.out.println("@# getMem_uid() =>" + member.getMem_uid());
			
			pstmt.setString(1, member.getMem_pwd());
			pstmt.setString(2, member.getMem_email());
			pstmt.setString(3, member.getMem_addr());
			pstmt.setString(4, member.getMem_uid());
			re = pstmt.executeUpdate();
			} catch(Exception ex){
			 	ex.printStackTrace();
			} finally{
				try {
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}
			catch (Exception se){
				se.printStackTrace();
			}
		}
		return re;		
	}
}












