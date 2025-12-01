<%--
  Created by IntelliJ IDEA.
  User: Hi
  Date: 01/12/2025
  Time: 8:30 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Selected Condiments</title>
  <c:import url="header.jsp"/>
</head>
<body class="bg-body-secondary">

<div class="container mt-5">
  <div class="card shadow-lg" style="max-width: 450px; margin: auto;">
    <div class="card-body">

      <h3 class="text-center text-primary mb-4">Các gia vị đã chọn</h3>

      <c:if test="${empty condiments}">
        <div class="alert alert-warning text-center">
          Không có gia vị nào được chọn!
        </div>
      </c:if>

      <c:if test="${not empty condiments}">
        <ul class="list-group">
          <c:forEach var="item" items="${condiments}">
            <li class="list-group-item list-group-item-info fw-semibold">
                ${item}
            </li>
          </c:forEach>
        </ul>
      </c:if>

      <a href="/home" class="btn btn-dark w-100 mt-4">Quay lại</a>

    </div>
  </div>
</div>

</body>
</html>