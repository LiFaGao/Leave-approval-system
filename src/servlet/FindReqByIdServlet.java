package servlet;

import pojo.Req;
import service.UserService;
import service.UserServiceimp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findReqByIdServlet")
public class FindReqByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String id = request.getParameter("id");
        //从数据库里获取id对应的req对象
        UserService us = new UserServiceimp();
        Req req = us.getReqById(id);
        //存储转发
//        System.out.println(req);
        request.setAttribute("req",req);
        request.getRequestDispatcher("changereq.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
