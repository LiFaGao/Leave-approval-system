<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/12/30
  Time: 8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/QueryUser_css.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $("#adUsername").blur(function () {
                var username = $(this).val();
                $.post("judgeAdUsernameServlet", {username: username}, function (date) {
                    var span = $("#msg");
                    if (date.userExsit) {
                        span.css("color", "red");
                        span.html(date.msg);
                    } else {
                        span.css("color", "green");
                        span.html(date.msg);
                    }
                }, "json");
            })
        });

        function exitConfime(msg, address1) {
            if (confirm("你确定要退出吗，" + msg)) {
                $("#eixt").attr("href", address1);
            } else {
                $("#eixt").attr("href", "#");
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
            <li><a href="#"><span class="glyphicon"></span>${admin.adUsername}</a></li>
            <li><a href="#" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-user"></span>新增管理员</a>
            </li>
            <%--            <li><a href="${pageContext.request.contextPath}/exitServlet"><span class="glyphicon glyphicon-log-in"></span> 退出</a></li>--%>
            <li><a id="eixt" href=""><span
                    onclick="exitConfime('${user.name}','${pageContext.request.contextPath}/exitServlet')"
                    class="glyphicon glyphicon-log-in" style="cursor: default"> 退出</span></a></li>
        </ul>
    </div>
</nav>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="${pageContext.request.contextPath}/addAdminServlet" method="post" class="form-horizontal"
              role="form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">新增管理员</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group" style="width: 500px;margin-left: 100px">
                        <label for="adUsername" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" name="adUsername" class="form-control" id="adUsername"
                                   style="width: 200px" placeholder="请输入用户名">
                            <span id="msg"></span>
                        </div>
                    </div>
                    <div class="form-group" style="width: 500px;margin-left: 100px">
                        <label for="adPassword" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="text" name="adPassword" class="form-control" id="adPassword"
                                   style="width: 200px" placeholder="请输入密码">
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">新增</button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal -->
</div>
<div class="mylabel">
    <p>
        用户信息表
    </p>


</div>
<div class="all">
    <div class="myfirst">
        <div class="inner">
            <form action="${pageContext.request.contextPath}/maintainUserServlet"class="form-inline" role="form">
                <div class="form-group">
                    <label class="sr-only" for="name">用户名</label>
                    <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名">
                </div>
                <div class="form-group">
                    <label class="sr-only" for="name">姓名</label>
                    <input type="text" name="name" id="name" class="form-control" placeholder="请输入名称">
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
        </div>
    </div>
    <div class="mysecond">
        <div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>年龄</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${page.list}" var="i" varStatus="s">
                    <tr>
                        <form role="form" action="${pageContext.request.contextPath}/changeUserMsgServlet" method="post">
                            <div class="form-group">
                                <td>${s.count}</td>
                            </div>
                            <div class="form-group">
                                <td><input name="name" type="text" value="${i.name}" class="form-control"></td>
                            </div>
                            <div class="form-group">
                                <td><input type="text" name="username" value="${i.username}" class="form-control"></td>
                            </div>
                            <div class="form-group">
                                <td><input type="text" name="password" value="${i.password}" class="form-control"></td>
                            </div>
                            <div class="form-group">
                                <td><input type="text" name="age" value="${i.age}" class="form-control"></td>
                            </div>
                            <div class="form-group">
                                <td><input type="text" name="gender" value="${i.gender}" class="form-control"></td>
                            </div>
                            <div class="form-group">
                                <td><input type="email" name="email" value="${i.email}" class="form-control"></td>
                            </div>
                            <div class="form-group">
                                <td>
                                    <a><button type="submit" class="btn btn-default btn-sm ">修改</button></a>
                                    <a href="${pageContext.request.contextPath}/deleteUserServlet?username=${i.username}"><button type="button" class="btn btn-default btn-sm ">删除</button></a>
                                </td>
                            </div>
                        </form>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="mythrid">
        <ul class="pagination">
            <c:if test="${page.currentPage==1}">
            <li class="disabled">
                </c:if>
                <c:if test="${page.currentPage!=1}">
            <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/maintainUserServlet?username=${username}&name=${name}&currentPage=${page.currentPage-1}&row=5">&laquo;</a>
            </li>
            <c:forEach begin="1" end="${page.totalPagenum}" var="i">
                <c:if test="${page.currentPage==i}">
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/maintainUserServlet?username=${username}&name=${name}&currentPage=${i}&row=5">${i}</a>
                    </li>
                </c:if>
                <c:if test="${page.currentPage!=i}">
                    <li>
                        <a href="${pageContext.request.contextPath}/maintainUserServlet?username=${username}&name=${name}&currentPage=${i}&row=5">${i}</a>
                    </li>
                </c:if>
            </c:forEach>
            <c:if test="${page.currentPage==page.totalPagenum}">
            <li class="disabled">
                </c:if>
                <c:if test="${page.currentPage!=page.totalPagenum}">
            <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/maintainUserServlet?username=${username}&name=${name}&currentPage=${page.currentPage+1}&row=5">&raquo;</a>
            </li>
        </ul>
        <span class="box">共${page.totalnumber}条记录</span>
    </div>
</div>
</body>
</html>