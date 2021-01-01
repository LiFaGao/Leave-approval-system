package servlet;

import org.apache.commons.beanutils.BeanUtils;
import pojo.Req;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/updateReqServlet")
public class UpdateReqServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装数据
        Req req = new Req();
        req.setId(Integer.parseInt(request.getParameter("id")));
        req.setUsername(request.getParameter("username"));
        req.setReason(request.getParameter("reason"));
        req.setTimeDay(Integer.parseInt(request.getParameter("timeDay")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(request.getParameter("startTime"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        req.setStartTime(date);
        HttpSession session = request.getSession();
        String condition = (String) session.getAttribute("condition");
        if("%".equals(condition))
            condition = "*";
        //更新数据库数据
        UserService us = new UserServiceimp();
        if(us.updateReqMsg(req)) {
            //页面重定向
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script type='text/javascript'>");
            out.print("alert('修改成功!');");
            out.print("window.location='"+request.getContextPath()+"/findUserByPageServlet?condition=" +condition+"';");
            out.print("</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
