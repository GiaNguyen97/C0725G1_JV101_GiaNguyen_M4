<%--
  Created by IntelliJ IDEA.
  User: Hi
  Date: 27/11/2025
  Time: 2:54 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<html>
<head>
  <title>Kết quả</title>
  <c:import url="/views/css-js/header.jsp"/>
</head>

<body class="bg-light">

<div class="container mt-5">
  <div class="card shadow-lg p-4">

    <h2 class="text-center mb-4">Kết quả chuyển đổi</h2>

    <p class="fs-5"><strong>${message}</strong></p>

    <p>
      Tỉ giá (VND/USD):
      <strong>
        <fmt:formatNumber value="${rate}" type="number" groupingUsed="true" minFractionDigits="2"/>
      </strong>
    </p>

    <p>
      Số tiền nhập:
      <strong>
        <fmt:formatNumber value="${amount}" type="number" groupingUsed="true" minFractionDigits="2"/>
        ${unitInput}
      </strong>
    </p>

    <h3 class="mt-3">
      ➡️ Kết quả:
      <span class="text-success">
                <fmt:formatNumber value="${result}" type="number" groupingUsed="true" minFractionDigits="2"/>
                ${unitOutput}
            </span>
    </h3>

    <a href="/currency" class="btn btn-secondary mt-4">Quay lại</a>

  </div>
</div>

</body>
</html>
