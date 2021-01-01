package filiter;

import pojo.Admin;
import pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class IsLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //设置编码
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        String key = split(request.getRequestURI());
        String[] togo = {"index","register","findUsernameServlet","adminLogalServlet","userLogalServlet","register_css"
                            ,"index_css"};
        boolean flag = false;
        for(String i :togo){
            if(i.equals(key)) {
                flag = true;
                chain.doFilter(req, resp);
            }
        }
        if(!flag) {
            System.out.println(key);
            //查看session里是否有相对应的用户或管理员信息
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Admin admin = (Admin) session.getAttribute("admin");

            //有
            if (user != null || admin != null) {
                chain.doFilter(req, resp);
            } else {
                //没有登录
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.print("<script type='text/javascript'>");
                out.print("alert('还没有登录，请登录!');");
                out.print("window.location='" + request.getContextPath() + "/index.jsp';");
                out.print("</script>");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }
    private String split(String uri){
        String temp1 = uri.substring(uri.lastIndexOf("/")+1);
        String temp2 = null;
        if(temp1.contains(".")){
            temp2 = temp1.substring(0,temp1.indexOf("."));
        }else{
            if(temp1.contains("?")){
                temp2 = temp1.substring(0,temp1.indexOf("?"));
            }else{
                temp2 = temp1;
            }
        }
        System.out.println(temp2);
        return temp2;
    }
}
