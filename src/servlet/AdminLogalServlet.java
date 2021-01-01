package servlet;

import pojo.Admin;
import service.AdminService;
import service.AdminServiceimp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminLogalServlet")
public class AdminLogalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //去数据库按用户名查找并封装数据
        AdminService as = new AdminServiceimp();
        Admin ad = as.getAdminByUsername(username);
        //判读
        if(ad==null){
            request.setAttribute("logal_msg","用户名不存在");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else{
            if(password.equals(ad.getAdPassword())){
                HttpSession session = request.getSession();
                session.setAttribute("admin",ad);
                response.sendRedirect(request.getContextPath()+"/adminPageServlet");
            }else{
                request.setAttribute("logal_msg","密码错误");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
