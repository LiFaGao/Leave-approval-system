package servlet;

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

@WebServlet("/userLogalServlet")
public class UserLogalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //通过用户名获取数据库里的用户数据
        UserService us = new UserServiceimp();
        User user = us.getUserByName(username);
        //判读获取的数据是否真的存在，如果存在判读密码。
        if(user == null){
            request.setAttribute("logal_msg","用户名不存在");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else{
            if(password.equals(user.getPassword())){
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                response.sendRedirect(request.getContextPath()+"/findUserByPageServlet?condition=*");
            }else{
                request.setAttribute("logal_msg","密码错误");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
