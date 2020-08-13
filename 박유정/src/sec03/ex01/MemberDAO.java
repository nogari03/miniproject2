package sec03.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> listMember(String _id) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member ";
			if (_id != null && _id.length() != 0) {
				query += "where id=?";
			}
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from t_member ";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String saveMember(MemberVO m, String fun) {
		String rst= "";
		try {
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String cId = m.getcId();
			String query = "";
			if (fun.equals("add")) {
				con = dataFactory.getConnection();
				query = "insert into t_member";
				query += " (id,pwd,name)";
				query += " values(?,?,?)";
				System.out.println("prepareStatememt: " + query);
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				pstmt.setString(3, name);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
				rst= "ok";
			}	else if (fun.equals("edit")) {
					con = dataFactory.getConnection();
					query = "update t_member set id=?, pwd=?, name=? where id=?" ;
					System.out.println("prepareStatememt: " + query);
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, id);
					pstmt.setString(2, pwd);
					pstmt.setString(3, name);
					pstmt.setString(4, cId);
					pstmt.executeUpdate();
					pstmt.close();
					con.close();
					rst="yes";
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst ;
	} 

}
