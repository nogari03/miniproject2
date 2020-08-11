package project;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection con;
    private PreparedStatement pstmt;
    private DataSource dataFactory;

    public DAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<VO> searchUser(String user_id) {
        List<VO> list = new ArrayList<>();
        try {
            con = dataFactory.getConnection();
            String query = "select * from UserInfo where id = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,user_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int user_num = rs.getInt("user_num");
                String id = rs.getString("id");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");
                VO vo = new VO();
                vo.setUser_num(Integer.toString(user_num));
                vo.setId(id);
                vo.setPwd(pwd);
                vo.setName(name);
                list.add(vo);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public void insertUser(VO vo){
        try
        {
            con = dataFactory.getConnection();
            String query = "insert into USERINFO values (userInfo_seq.nextval,?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,vo.getId());
            pstmt.setString(2,vo.getPwd());
            pstmt.setString(3,vo.getName());
            pstmt.executeUpdate();

            pstmt.close();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateUser(VO vo){
        System.out.println(vo.toString());
        try
        {
            con = dataFactory.getConnection();
            String query = "update USERINFO set USER_NUM=?,Id=?,PWD=?,NAME=? where USER_NUM = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,vo.getUser_num());
            pstmt.setString(2,vo.getId());
            pstmt.setString(3,vo.getPwd());
            pstmt.setString(4,vo.getName());
            pstmt.setString(5,vo.getUser_num());
            pstmt.executeUpdate();

            pstmt.close();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<VO> searchUserByNum(int number) {
        List<VO> list = new ArrayList<>();
        try {
            con = dataFactory.getConnection();
            String query = "select * from UserInfo where user_num = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,number);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int user_num = rs.getInt("user_num");
                String id = rs.getString("id");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");
                VO vo = new VO();
                vo.setUser_num(Integer.toString(user_num));
                vo.setId(id);
                vo.setPwd(pwd);
                vo.setName(name);
                list.add(vo);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
