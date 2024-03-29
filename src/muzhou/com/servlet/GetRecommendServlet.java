package muzhou.com.servlet;


import muzhou.com.service.QuestionService;
import muzhou.com.service.serviceImpl.QuestionServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetRecommendServlet", urlPatterns = {"/GetRecommendServlet"})
public class GetRecommendServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");

        //JSON对象
        JSONObject jsonObject = new JSONObject();

        PrintWriter out = response.getWriter();

        QuestionService questionService = new QuestionServiceImpl();

        String ifLogin = request.getParameter("Login");
        if (ifLogin.equals("0"))
        {
            List<Map<String,Object>> qList = questionService.getUnLoginQuestions();
            jsonObject.accumulate("qList",qList);
        }
        else
        {
            int userid = Integer.parseInt(request.getParameter("userid"));

            List<Map<String,Object>> qList = questionService.getLoginQuestions(userid);
            jsonObject.accumulate("qList",qList);

        }



        out.print(jsonObject);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
