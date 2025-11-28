<%--
  Created by IntelliJ IDEA.
  User: Hi
  Date: 27/11/2025
  Time: 3:22 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>Simple Dictionary</title>

  <!-- Bootstrap -->
  <c:import url="/views/css-js/header.jsp"/>
</head>

<body class="bg-light">

<div class="container mt-5">
  <div class="card shadow-lg p-4" style="max-width: 500px; margin: 0 auto;">
    <h2 class="text-center mb-4">Từ điển Anh – Việt</h2>

    <form action="/dictionary/translate" method="post">

      <div class="mb-3">
        <label class="form-label">Nhập từ tiếng Anh:</label>
        <input type="text" name="word" class="form-control" placeholder="Ví dụ: hello"
               required>
      </div>

      <button type="submit" class="btn btn-primary w-100">Dịch</button>
    </form>
    <a href="/home" class="btn btn-secondary mt-4">Quay lại trang chủ</a>
  </div>
</div>

</body>
</html>
