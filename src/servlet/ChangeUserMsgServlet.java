package servlet;

import daoUtils.Dao;
import daoUtils.DaoImp;
import org.apache.commons.beanutils.BeanUtils;
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
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/changeUserMsgServlet")
public class ChangeUserMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装数据数
        User newUser = new User();
        try {
            BeanUtils.populate(newUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //获取登录的用户信息
        UserService us = new UserServiceimp();
        HttpSession session = request.getSession();
        User oldUser = (User) session.getAttribute("user");
        if(oldUser==null){
            //管理员界面的操作
            String username = request.getParameter("username");
            System.out.println(username+"username");
            oldUser = us.getUserByName(username);
            System.out.println(oldUser);
            if(oldUser.equals(newUser)){
                response.sendRedirect(request.getContextPath() + "/maintainUserServlet");
            }else{
                //更新信息
                us.updateUserMsg(newUser);
                out.print("<script type='text/javascript'>");
                out.print("alert('修改成功!');");
                out.print("window.location='" + request.getContextPath() + "/maintainUserServlet';");
                out.print("</script>");
            }
        }else {
            newUser.setUsername(oldUser.getUsername());
            String condition = (String) session.getAttribute("condition");
            if ("%".equals(condition))
                condition = "*";
            //判读是否有改动
            if (oldUser.equals(newUser)) {
                response.sendRedirect(request.getContextPath() + "/findUserByPageServlet?condition=" + condition);
            } else {
                //有改动写回到数据库
                us.updateUserMsg(newUser);
                //更新session里的用户信息
                session.setAttribute("user", newUser);
                out.print("<script type='text/javascript'>");
                out.print("alert('修改成功!');");
                out.print("window.location='" + request.getContextPath() + "/findUserByPageServlet?condition=" + condition + "';");
                out.print("</script>");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
