package servlet;

import pojo.Page;
import pojo.Req;
import service.AdminService;
import service.AdminServiceimp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminPageServlet")
public class AdminPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String condition = request.getParameter("condition");
        String currentPage = request.getParameter("currentPage");
        String row = request.getParameter("row");
        //判读补充
        if(condition==null||"".equals(condition))
            condition = "*";
        if(currentPage==null||"".equals(currentPage))
            currentPage = "1";
        if(row==null||"".equals(row))
            row="5";
        //从数据库里读取数据
        AdminService as = new AdminServiceimp();
        Page<Req> page = as.getPage(currentPage,row,condition);
        //存储转发
        HttpSession session = request.getSession();
        session.setAttribute("condition",condition);
        request.setAttribute("page",page);
        request.getRequestDispatcher("toadmin.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
