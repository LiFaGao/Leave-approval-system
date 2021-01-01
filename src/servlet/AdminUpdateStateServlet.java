package servlet;

import service.AdminService;
import service.AdminServiceimp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/adminUpdateStateServlet")
public class AdminUpdateStateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String id = request.getParameter("id");
        //更新数据库
        AdminService as = new AdminServiceimp();
        boolean flag = as.updateReqState(id);
        //跳转页面
        HttpSession session = request.getSession();
        String condition = (String) session.getAttribute("condition");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(flag){
            out.print("<script type='text/javascript'>");
            out.print("alert('审批成功!');");
            out.print("window.location='"+request.getContextPath()+"/adminPageServlet?condition="+condition+"';");
            out.print("</script>");
        }else{
            out.print("<script type='text/javascript'>");
            out.print("alert('审批失败!');");
            out.print("window.location='"+request.getContextPath()+"/adminPageServlet?condition="+condition+"';");
            out.print("</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
