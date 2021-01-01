<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/12/28
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请假系统</title>
    <link rel = "stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/touser_css.css">
    <script src = "js/jquery.min.js"></script>
    <script src = "js/bootstrap.min.js"></script>
    <script type="text/javascript">
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
<div class="mylabel">
    <p>
        欢迎你，${user.name}
    </p>

</div>
<div class="all">
    <div class="myfirst">
        <div class="myradio">
            <form action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
                <label class="radio-inline">
                    <select name= "condition"class="form-control" style="width: 180px" size="1">
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
            <a href="${pageContext.request.contextPath}/addreq.html"><button type="button" class="btn btn-default btn-lg">新增请假申请</button></a>
            <!--                 <button type="button" class="btn btn-default btn-lg">修改未审批申请</button>-->
<%--            <a href="${pageContext.request.contextPath}/changeuser.jsp"><button type="button" class="btn btn-default btn-lg">修改个人信息</button></a>--%>

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
                    <th>请假时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="req" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${req.name}</td>
                    <td>${req.reason}</td>
                    <td>${req.timeDay}</td>
                    <td>${req.state}</td>
                    <td>${req.startTime}</td>
                    <c:if test="${req.state eq'未审批'}">
                    <td>
                        <a href="${pageContext.request.contextPath}/findReqByIdServlet?id=${req.id}">
                            <button type="button" class="btn btn-default btn-sm">修改</button></a>
                    </td>
                    </c:if>
                    <c:if test="${req.state ne'未审批'}">
                        <td>
                            <button type="button" class="btn btn-default btn-sm" disabled>修改</button>
                        </td>
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
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?condition=${condition}&currentPage=${page.currentPage-1}&row=5">&laquo;</a>
            </li>
            <c:forEach begin="1" end="${page.totalPagenum}" var="i">
                <c:if test="${page.currentPage==i}">
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?condition=${condition}&currentPage=${i}&row=5">${i}</a>
                    </li>
                </c:if>
                <c:if test="${page.currentPage!=i}">
                    <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?condition=${condition}&currentPage=${i}&row=5">${i}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${page.currentPage==page.totalPagenum}">
             <li class="disabled">
            </c:if>
             <c:if test="${page.currentPage!=page.totalPagenum}">
                <li>
             </c:if>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?condition=${condition}&currentPage=${page.currentPage+1}&row=5">&raquo;</a>
            </li>
        </ul>
        <span class="box">共${page.totalnumber}条记录</span>
    </div>
</div>
</body>
</html>
