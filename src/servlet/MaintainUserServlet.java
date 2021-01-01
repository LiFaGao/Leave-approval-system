package servlet;

import pojo.Page;
import pojo.User;
import service.AdminService;
import service.AdminServiceimp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/maintainUserServlet")
public class MaintainUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取条件数据
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String currentPage = request.getParameter("currentPage");
        String row = request.getParameter("row");
        if(currentPage == null || "".equals(currentPage))
            currentPage = "1";
        if(row == null||"".equals(row))
            row = "5";
        //更具条件从数据库里获取Page对象
        AdminService as = new AdminServiceimp();
        Page<User> page = as.getPageForUser(username,name,currentPage,row);
        //保存数据
        request.setAttribute("page",page);
        request.setAttribute("username",username);
        request.setAttribute("name",name);
        //页面跳转
        request.getRequestDispatcher("QueryUser.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
