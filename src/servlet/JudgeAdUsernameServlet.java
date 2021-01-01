package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.Admin;
import service.AdminService;
import service.AdminServiceimp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/judgeAdUsernameServlet")
public class JudgeAdUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String username = request.getParameter("username");
        //从数据库里获取admin对象
        AdminService as = new AdminServiceimp();
        Admin ad = as.getAdminByUsername(username);
        //判断
        Map<String,Object> map = new HashMap<String,Object>();
        if(ad==null){
            map.put("userExsit",false);
            map.put("msg","用户名可用");
        }else{
            map.put("userExsit",true);
            map.put("msg","用户名已存在");
        }
        ObjectMapper om = new ObjectMapper();
        response.setContentType("text/html;charset=utf-8");
        om.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
