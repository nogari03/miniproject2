package project;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/userInfo")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        DAO dao = new DAO();

        String command = request.getParameter("command");
        String user_num = request.getParameter("user_num");
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");
        int temp;
        VO vo;

        List<VO> list = null;
        System.out.println(command);

        if("search".equals(command)) {
            list = dao.searchUser(id);
        }else if("insert".equals(command)){
            vo = new VO(id, pwd, name);
            dao.insertUser(vo);
        }else if("update".equals(command)){
            vo = new VO(user_num, id, pwd, name);
            dao.updateUser(vo);
        }else if("prev".equals(command)){
            temp = Integer.parseInt(user_num);
            temp = temp - 1;
            list = dao.searchUserByNum(temp);
        }else if("next".equals(command)){
            temp = Integer.parseInt(user_num);
            temp = temp + 1;
            list = dao.searchUserByNum(temp);
        }

        String json = new Gson().toJson(list);
        out.print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/index.html");
    }
}
