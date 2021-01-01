package servlet;

import com.sun.org.apache.xpath.internal.operations.Bool;
import pojo.Req;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addReqServlet")
public class AddReqServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //要从request里获取数据所以设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String username = request.getParameter("username");
        String timeDay = request.getParameter("timeDay");
        String reason = request.getParameter("reason");
        String startTime = request.getParameter("startTime");
        //封装数据的时候要用到用户的的信息，所以操作session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //封装req
        Req req = new Req();
        req.setReason(reason);
        req.setState("未审批");
        req.setTimeDay(Integer.parseInt(timeDay));
        req.setUsername(user.getUsername());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(request.getParameter("startTime"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        req.setStartTime(date);
        //写入数据库
        UserService us = new UserServiceimp();
        Boolean flag = us.addReq(req);
        //页面跳转
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String condition = (String) session.getAttribute("condition");
        if("%".equals(condition))
            condition = "*";
        if(flag){
            out.print("<script type='text/javascript'>");
            out.print("alert('申请成功!');");
            out.print("window.location='"+request.getContextPath()+"/findUserByPageServlet?condition="+condition+"';");
            out.print("</script>");
        }else{
            out.print("<script type='text/javascript'>");
            out.print("alert('申请失败!');");
            out.print("window.location='"+request.getContextPath()+"/findUserByPageServlet?condition="+condition+"';");
            out.print("</script>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
