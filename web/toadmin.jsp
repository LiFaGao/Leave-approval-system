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
    <link rel = "stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/toadmin_css.css">
    <script src = "js/jquery.min.js"></script>
    <script src = "js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $("#adUsername").blur(function () {
                var username = $(this).val();
                $.post("judgeAdUsernameServlet",{username:username},function (date) {
                    var span = $("#msg");
                    if(date.userExsit){
                        span.css("color","red");
                        span.html(date.msg);
                    }else{
                        span.css("color","green");
                        span.html(date.msg);
                    }
                },"json");
            })
        });
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
            <li><a href="#"><span class="glyphicon"></span>${admin.adUsername}</a></li>
            <li><a href="#" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-user"></span>新增管理员</a></li>
<%--            <li><a href="${pageContext.request.contextPath}/exitServlet"><span class="glyphicon glyphicon-log-in"></span> 退出</a></li>--%>
            <li><a id="eixt" href=""><span onclick="exitConfime('${user.name}','${pageContext.request.contextPath}/exitServlet')" class="glyphicon glyphicon-log-in" style="cursor: default"> 退出</span></a></li>
        </ul>
    </div>
</nav>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="${pageContext.request.contextPath}/addAdminServlet" method="post" class="form-horizontal" role="form">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">新增管理员</h4>
            </div>
            <div class="modal-body">

                    <div class="form-group" style="width: 500px;margin-left: 100px">
                        <label for="adUsername" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" name="adUsername" class="form-control" id="adUsername" style="width: 200px" placeholder="请输入用户名">
                            <span id="msg"></span>
                        </div>
                    </div>
                    <div class="form-group" style="width: 500px;margin-left: 100px">
                        <label for="adPassword" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="text" name="adPassword" class="form-control" id="adPassword" style="width: 200px" placeholder="请输入密码">
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
        管理员界面
    </p>


</div>
<div class="all">
    <div class="myfirst">
        <div class="myradio">
            <form action="${pageContext.request.contextPath}/adminPageServlet" method="post">
                <label class="radio-inline">
                    <select name="condition" class="form-control" style="width: 180px" size="1">
                        <option value="*">全部请假申请</option>
                        <option value="审批">已审批请假申请</option>
                        <option value="未审批">未审批请假申请</option>
                    </select>
                </label>
                <label>
                    <button type="submit" class="btn btn-default mybutton">查询</button>
                </label>
            </form>
        </div>
        <div class="myright">
            <a href="${pageContext.request.contextPath}/maintainUserServlet"><button type="button" class="btn btn-default btn-lg">用户管理</button></a>
        </div>

    </div>
    <div class="mysecond">
        <div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>请假原因</th>
                    <th>时长(天)</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="i" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${i.name}</td>
                    <td>${i.reason}</td>
                    <td>${i.timeDay}</td>
                    <td>${i.state}</td>
                    <c:if test="${i.state eq '未审批'}">
                    <td>
                        <a href="${pageContext.request.contextPath}/adminUpdateStateServlet?id=${i.id}"><button type="button" class="btn btn-default btn-sm">审批</button></a>
                    </td>
                    </c:if>
                    <c:if test="${i.state ne '未审批'}">
                        <td><button type="button" class="btn btn-default btn-sm" disabled>审批</button></td>
                    </c:if>
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
                <a href="${pageContext.request.contextPath}/adminPageServlet?condition=${condition}&currentPage=${page.currentPage-1}&row=5">&laquo;</a>
            </li>
            <c:forEach begin="1" end="${page.totalPagenum}" var="i">
                <c:if test="${page.currentPage==i}">
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/adminPageServlet?condition=${condition}&currentPage=${i}&row=5">${i}</a>
                    </li>
                </c:if>
                <c:if test="${page.currentPage!=i}">
                    <li><a href="${pageContext.request.contextPath}/adminPageServlet?condition=${condition}&currentPage=${i}&row=5">${i}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${page.currentPage==page.totalPagenum}">
            <li class="disabled">
                </c:if>
                <c:if test="${page.currentPage!=page.totalPagenum}">
            <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/adminPageServlet?condition=${condition}&currentPage=${page.currentPage+1}&row=5">&raquo;</a>
            </li>
        </ul>
        <span class="box">共${page.totalnumber}条记录</span>
    </div>
</div>
</body>
</html>