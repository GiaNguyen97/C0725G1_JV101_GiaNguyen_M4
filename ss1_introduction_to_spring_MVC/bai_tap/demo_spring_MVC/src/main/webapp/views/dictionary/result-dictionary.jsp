<%--
  Created by IntelliJ IDEA.
  User: Hi
  Date: 27/11/2025
  Time: 3:23 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>Kết quả</title>

  <!-- Bootstrap -->
  <c:import url="/views/css-js/header.jsp"/>
</head>

<body class="bg-light">

<div class="container mt-5">
  <div class="card shadow-lg p-4" style="max-width: 500px; margin: 0 auto;">

    <h2 class="text-center mb-4">Kết quả tra cứu</h2>

    <!-- Nếu có lỗi -->
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger text-center">
      <%= request.getAttribute("error") %>
    </div>
    <% } else { %>

    <!-- Nếu có kết quả -->
    <div class="alert alert-success text-center fs-5">
      <strong>${word}</strong> → ${result}
    </div>

    <% } %>

    <a href="/dictionary" class="btn btn-secondary w-100 mt-3">Quay lại</a>
  </div>
</div>

</body>
</html>