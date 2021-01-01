package servlet;

import pojo.Page;
import pojo.Req;
import pojo.User;
import service.UserService;
import service.UserServiceimp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码并获取数据
            request.setCharacterEncoding("utf-8");
            String currentPage = request.getParameter("currentPage");
            String row = request.getParameter("row");
            String condition = request.getParameter("condition");
            if("*".equals(condition)||"".equals(condition)||condition==null)
                condition = "%";
            if(currentPage == null||"".equals(currentPage)){
                currentPage = "1";
            }
            if(row == null||"".equals(row)){
                row = "5";
            }
//        System.out.println(condition+","+currentPage+","+row);
         //获取用户数据
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //根据获取的数据来获取相对应的Page对象
        UserService us = new UserServiceimp();
        Page<Req> page = null;
//        if("*".equals(condition))
//            page = us.findUserByPage(currentPage,row,user.getUsername());
//        else
            page = us.findUserByPage(currentPage,row,user.getUsername(),condition);
        //将获取的的Page对象存起来
        request.setAttribute("page",page);
        session.setAttribute("condition",condition);
        //页面跳转

        request.getRequestDispatcher("/touser.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
