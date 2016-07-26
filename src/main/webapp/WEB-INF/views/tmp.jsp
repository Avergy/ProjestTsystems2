<%--
  Created by IntelliJ IDEA.
  User: Siry
  Date: 06.07.2016
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>Message : ${message}</h1>
</body>
<head>
    <img src="/resources/images/phoneImages/standart_phone_img.png">
    <img src="/resources/images/phoneImages/1.jpg">
</head>
</html>
<div class="grid_3 grid_5">
    <div class="bs-example bs-example-tabs" data-example-id="togglable-tabs">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active">
                <a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">Personal info</a></li>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <li class="">
                    <a href="#profile" role="tab" id="profile-tab" data-togle="tab" aria-controls="profile" aria-expanded="false">Admin features</a></li>
            </security:authorize>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade active in" id="home" aria-labelledby="home-tab">
                <ul class="list-group">
                    <li class="list-group-item"><a href="">Edit account detail</a></li>
                    <li class="list-group-item"><a href="">My orders</a></li>
                    <li class="list-group-item"><a href="">My address</a></li>
                </ul>
            </div>
            <div class="tab-pane fade" id="profile" aria-labelledby="profile-tab">
                <ul class="list-group">
                    <li class="list-group-item"><a href="">Add phone</a></li>
                    <li class="list-group-item"><a href="">Edit brand list</a></li>
                    <li class="list-group-item"><a href="">Statistics</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>