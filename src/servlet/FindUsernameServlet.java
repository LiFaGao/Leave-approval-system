package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.User;
import service.UserService;
import service.UserServiceimp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findUsernameServlet")
public class FindUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Object> map = new HashMap<String,Object>();
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String username = request.getParameter("username");
        //判读空字符串
        if(username==null||"".equals(username)){
            map.put("userExsit",true);
            map.put("msg","用户名不可用");
        }else {
            //从数据库里查找并封装数据
            UserService us = new UserServiceimp();
            User user = us.getUserByName(username);
            //判断

            if (user == null) {
                map.put("userExsit", false);
                map.put("msg", "用户名可用");
            } else {
                map.put("userExsit", true);
                map.put("msg", "用户名已存在");
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("text/html;charset=utf-8");
        mapper.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          this.doPost(request,response);
    }
}
