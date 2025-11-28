<%--
  Created by IntelliJ IDEA.
  User: Hi
  Date: 27/11/2025
  Time: 2:15 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Bài tập SS1 - Spring MVC</title>
    <c:import url="/views/css-js/header.jsp" />
</head>

<body class="bg-light" style="min-height: 100vh;">

<div class="d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="card shadow-lg p-5" style="width: 450px; border-radius: 15px;">

        <h2 class="text-center mb-4 fw-bold text-primary">
            Bài tập SS1 <br> <span class="text-dark">Tổng quan Spring MVC</span>
        </h2>

        <p class="text-center text-muted mb-4">
            Chọn bài tập bạn muốn thực hiện:
        </p>

        <div class="d-grid gap-3">

            <a href="/currency" class="btn btn-outline-primary btn-lg fw-bold">
                💱 Currency Converter
            </a>

            <a href="/dictionary" class="btn btn-outline-success btn-lg fw-bold">
                📘 Dictionary (Anh – Việt)
            </a>

        </div>

    </div>
</div>

</body>
</html>
