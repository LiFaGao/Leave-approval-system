<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/12/27
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>登录</title>
    <link rel = "stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/index_css.css">
    <script src = "js/jquery.min.js"></script>
    <script src = "js/bootstrap.min.js"></script>
</head>
<body class="mybg">
<div class="myall">
    <div class="myhead">
        <p>请假审批系统</p>
    </div>
    <div class="mybody">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户</a></li>
            <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">管理员</a></li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="home">
                <div class = "item">
                    <form action="${pageContext.request.contextPath}/userLogalServlet" method="post" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="firstname1" class="col-sm-2 control-label mybox2">用户名</label>
                            <div class="col-sm-10 mybox">
                                <input type="text" name="username" class="form-control" id="firstname1" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname1" class="col-sm-2 control-label mybox2">密码</label>
                            <div class="col-sm-10 mybox">
                                <input type="password" name="password" class="form-control" id="lastname1" placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10 ">
                                <button type="submit" class="btn btn-default btn-lg">登录</button>
                                <a href="${pageContext.request.contextPath}/register.html"><button type="button" class="btn btn-default btn-lg mybox3">注册</button></a>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="profile">
                <div class = "item">
                    <form action="${pageContext.request.contextPath}/adminLogalServlet" method="post" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="firstname2" class="col-sm-2 control-label mybox4">管理员用户名</label>
                            <div class="col-sm-10 mybox">
                                <input type="text" name="username" class="form-control" id="firstname2" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastname2" class="col-sm-2 control-label mybox4">管理员密码</label>
                            <div class="col-sm-10 mybox">
                                <input type="password" name="password" class="form-control" id="lastname2" placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10 ">
                                <button type="submit" class="btn btn-default btn-lg">登录</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <div class="mytail">
            <p>
                ${logal_msg}
            </p>
        </div>
    </div>
</div>
</body>
</html>
