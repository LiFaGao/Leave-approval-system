package servlet;

import org.apache.commons.beanutils.BeanUtils;
import pojo.User;
import service.AdminService;
import service.AdminServiceimp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String username = request.getParameter("username");
        //更改数据库信息
        AdminService ad = new AdminServiceimp();
        boolean flag = ad.deleteUser(username);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(flag){
                //都删除了
                out.print("<script type='text/javascript'>");
                out.print("alert('删除成功!');");
                out.print("window.location='" + request.getContextPath() + "/maintainUserServlet';");
                out.print("</script>");
        }else{
            //删除失败
            out.print("<script type='text/javascript'>");
            out.print("alert('删除失败!');");
            out.print("window.location='" + request.getContextPath() + "/maintainUserServlet';");
            out.print("</script>");
        }
        //判断
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
