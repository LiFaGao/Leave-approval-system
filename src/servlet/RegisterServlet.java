package servlet;

import pojo.User;
import service.UserService;
import service.UserServiceimp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装数据
        UserService us = new UserServiceimp();
        User user = us.getUserByMap(map);
        //写入数据库
        boolean flag = us.addUser(user);
        //页面跳转
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(flag){
            out.print("<script type='text/javascript'>");
            out.print("alert('注册成功!');");
            out.print("window.location='index.jsp';");
            out.print("</script>");
        }else{
            out.print("<script type='text/javascript'>");
            out.print("alert('注册失败!');");
            out.print("window.location='index.jsp';");
            out.print("</script>");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
