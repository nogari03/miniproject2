package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/jsonPractice")
public class JsonServletPractice extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dohandle(request, response);

	}

	private void dohandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		MemberDAO dao = new MemberDAO();
		JSONObject totaObject = new JSONObject();
		JSONArray memberArray = new JSONArray();
		JSONObject memberInfo;
		String action = request.getParameter("action");

		if (action.contentEquals("process.do")) {
			String id = request.getParameter("id");
			System.out.println(id);
			List<MemberVO> list = dao.listMember(id);
			for (MemberVO vo : list) {
				memberInfo = new JSONObject();
				memberInfo.put("id", vo.getId());
				memberInfo.put("pwd", vo.getPwd());
				memberInfo.put("name", vo.getName());
				memberArray.add(memberInfo);
			}
			totaObject.put("members", memberArray);
			String JsonInfo = totaObject.toJSONString();
			writer.print(JsonInfo);
		} else if (action.contentEquals("save.do")) {
			String fun = request.getParameter("fun");
			String cId = request.getParameter("id");
			String id2 = request.getParameter("id2");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			System.out.println(id2 + "---" + pwd + "---" + name);
			MemberVO vo = new MemberVO(id2, pwd, name, cId);
			String JsonInfo = dao.saveMember(vo, fun);
			System.out.println(JsonInfo);
			writer.print(JsonInfo);
		} else if (action.contentEquals("prev.do") || action.contentEquals("next.do")) {
			List<MemberVO> list = dao.listMembers();
			for (MemberVO vo : list) {
				memberInfo = new JSONObject();
				memberInfo.put("id", vo.getId());
				memberInfo.put("pwd", vo.getPwd());
				memberInfo.put("name", vo.getName());
				memberArray.add(memberInfo);
			}
			totaObject.put("members", memberArray);
			String JsonInfo = totaObject.toJSONString();
			writer.print(JsonInfo);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dohandle(request, response);

	}

}
