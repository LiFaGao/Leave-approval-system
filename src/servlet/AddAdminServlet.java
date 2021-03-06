package servlet;

import org.apache.commons.beanutils.BeanUtils;
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
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addAdminServlet")
public class AddAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装数据
        Admin ad = new Admin();
        try {
            BeanUtils.populate(ad,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //添加到数据库
        AdminService as = new AdminServiceimp();
        boolean flag = as.addAdmin(ad);
        HttpSession session = request.getSession();
        String condition = (String) session.getAttribute("condition");
        //页面跳转
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(flag){
            out.print("<script type='text/javascript'>");
            out.print("alert('新增成功!');");
            out.print("window.location='"+request.getContextPath()+"/adminPageServlet?condition="+condition+"';");
            out.print("</script>");
        }else{
            out.print("<script type='text/javascript'>");
            out.print("alert('新增失败!');");
            out.print("window.location='"+request.getContextPath()+"/adminPageServlet?condition="+condition+"';");
            out.print("</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
