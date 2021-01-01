<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/12/29
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改个人信息</title>
    <link rel = "stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/changeuser_css.css">
    <script src = "js/jquery.min.js"></script>
    <script src = "js/bootstrap.min.js"></script>
    <script>
        function exitConfime(msg,address1) {
            if(confirm("你确定要退出吗，"+msg)){
                $("#eixt").attr("href",address1);
            }else{
                $("#eixt").attr("href","#");
            }
        }
    </script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">请假申请系统</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon"></span>欢迎你,${user.name}</a></li>
            <li><a href="${pageContext.request.contextPath}/changeuser.jsp"><span class="glyphicon glyphicon-user"></span>个人中心</a></li>
            <li><a id="eixt"><span onclick="exitConfime('${user.name}','${pageContext.request.contextPath}/exitServlet')" class="glyphicon glyphicon-log-in" style="cursor: default"> 退出</span></a></li>
        </ul>
    </div>
</nav>
<div class="mytable">
    <div class="myone">
        <P>
            修改个人信息
        </P>
    </div>
    <div class="mytwo">
        <form action="${pageContext.request.contextPath}/changeUserMsgServlet" method="post" class="form-horizontal">
            <div class="form-group mymarginTop">
                <label for="name" class="col-sm-2 control-label myLeftMargin">姓名</label>
                <div class="col-sm-10 mytext">
                    <input type="text" name="name" class="form-control" id="name" value="${user.name}">
                </div>
            </div>
            <div class="form-group mymarginTop">
            <label for="password" class="col-sm-2 control-label myLeftMargin">密码</label>
            <div class="col-sm-10 mytext">
                <input type="text" name="password" class="form-control" id="password" value="${user.password}">
            </div>
        </div>
            <div class="form-group">
                <label class="col-sm-2 control-label myLeftMargin">性别</label>
                <div class="col-sm-10 mytext">
                    <c:if test="${user.gender eq '男'}">
                    <label class="radio-inline">
                        <input type="radio" name="gender"  value="男" checked> 男
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="gender"  value="女"> 女
                    </label>
                    </c:if>
                    <c:if test="${user.gender eq '女'}">
                        <label class="radio-inline">
                            <input type="radio" name="gender" value="男" > 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="gender" value="女" checked> 女
                        </label>
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <label for="age" class="col-sm-2 control-label myLeftMargin">年龄</label>
                <div class="col-sm-10 mytext">
                    <input type="text" name="age" class="form-control " id="age" value="${user.age}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label myLeftMargin">邮箱</label>
                <div class="col-sm-10 mytext">
                    <input type="email" name="email" class="form-control " id="inputEmail3" value="${user.email}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10" style="margin-left: 200px;margin-top: 20px">
                    <button type="submit" class="btn btn-default btn-lg">修改</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
