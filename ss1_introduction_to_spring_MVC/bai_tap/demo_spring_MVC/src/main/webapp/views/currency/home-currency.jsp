<%--
  Created by IntelliJ IDEA.
  User: Hi
  Date: 27/11/2025
  Time: 2:53 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>Currency Converter</title>
  <c:import url="/views/css-js/header.jsp"/>
</head>
<body class="bg-light">

<div class="container mt-5">
  <div class="card shadow-lg p-4">
    <h2 class="text-center mb-4">Currency Converter</h2>

    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger">
      <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <form action="/currency/convert" method="post">

      <div class="mb-3">
        <label class="form-label">Chọn loại chuyển đổi</label>
        <select name="type" class="form-select">
          <option value="usd-to-vnd">USD → VND</option>
          <option value="vnd-to-usd">VND → USD</option>
        </select>
      </div>

      <div class="mb-3">
        <label class="form-label">Tỉ giá (VND/USD)</label>
        <input type="number" step="0.01" name="rate" class="form-control" required>
      </div>

      <div class="mb-3">
        <label class="form-label">Số tiền muốn đổi</label>
        <input type="number" step="0.01" name="amount" class="form-control" required>
      </div>

      <button type="submit" class="btn btn-primary w-100">Convert</button>

    </form>
    <a href="/home" class="btn btn-secondary mt-4">Quay lại trang chủ</a>
  </div>
</div>

</body>
</html>