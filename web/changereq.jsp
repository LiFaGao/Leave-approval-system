<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/12/29
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel = "stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/changereq_css.css">
    <script src = "js/jquery.min.js"></script>
    <script src = "js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $("#startTime").blur(function (data) {
                var startTime = $(this).val();
                var msg = $("#msg");
                var patten = /([\d]{4})-([1][0-2]|[0][1-9])-([1-2][\d]|[0][1-9]|[3][0-1])/;
                if(patten.test(startTime)){
                    msg.css("color","green");
                    msg.html("格式正确");
                }else{
                    msg.css("color","red");
                    msg.html("格式不正确");
                }
            })
        })
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
            修改请假申请
        </P>
    </div>
    <div class="mytwo">
        <form action="${pageContext.request.contextPath}/updateReqServlet" method="post" class="form-horizontal">
            <div class="form-group mymarginTop">
                <input type="hidden" name="id" value="${req.id}">
                <label for="username" class="col-sm-2 control-label myLeftMargin">用户名</label>
                <div class="col-sm-10 mytext">
                    <input type="text" name="username" class="form-control" id="username" value="${req.username}">
                </div>
            </div>
            <div class="form-group mymarginTop">
                <label for="reason" class="col-sm-2 control-label myLeftMargin">请假原因</label>
                <div class="col-sm-10 mytext">
                    <input type="text" name="reason" class="form-control" id="reason" value="${req.reason}">
                </div>
            </div>
            <div class="form-group mymarginTop">
                <label for="timeDay" class="col-sm-2 control-label myLeftMargin">时间(天)</label>
                <div class="col-sm-10 mytext">
                    <input type="text" name="timeDay" class="form-control" id="timeDay" value="${req.timeDay}">
                </div>
            </div>
            <div class="form-group mymarginTop">
                <label for="timeDay" class="col-sm-2 control-label myLeftMargin">开始日期</label>
                <div class="col-sm-10 mytext">
                    <input type="text" name="startTime" class="form-control" id="startTime" value="${req.startTime}">
                </div>
                <span id="msg"></span>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10" style="margin-left: 200px;margin-top: 20px">
                    <button type="submit" class="btn btn-default btn-lg">申请</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>