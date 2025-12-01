<%--
  Created by IntelliJ IDEA.
  User: Hi
  Date: 01/12/2025
  Time: 8:59 SA
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .calc-container {
            max-width: 850px;
            margin: 50px auto;
            padding: 25px;
            border: 2px solid #ddd;
            border-radius: 16px;
            background: #ffffff;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        h2 {
            font-weight: 600;
            color: #333;
        }
        .calc-buttons {
            display: flex;
            flex-wrap: wrap;         /* Cho phép xuống dòng */
            justify-content: center; /* Căn giữa */
            gap: 12px;
        }

        .calc-buttons button {
            flex: 1 1 45%;           /* Chia đều 2 nút mỗi dòng, responsive */
            max-width: 180px;        /* Giới hạn để nút không quá to */
            min-width: 150px;        /* Tất cả nút bằng nhau */
            height: 48px;            /* Chiều cao đồng nhất */

            display: flex;
            align-items: center;
            justify-content: center;

            font-size: 0.95rem;
            border-radius: 12px;
            font-weight: 500;
            transition: 0.25s;
        }

        .calc-buttons button:hover {
            transform: translateY(-2px);
            opacity: 0.9;
        }
    </style>
</head>
<body>

<div class="calc-container">
    <h2 class="text-center mb-4">Calculator</h2>

    <form action="calculate" method="post">
        <div class="d-flex mb-3">
            <input type="number" step="any" name="num1" class="form-control me-2" placeholder="Num1" value="${num1}" required>
            <input type="number" step="any" name="num2" class="form-control" placeholder="Num2" value="${num2}" required>
        </div>

        <!-- Nút nằm trên 1 hàng, responsive -->
        <div class="calc-buttons mb-3">
            <button type="submit" name="operator" value="add" class="btn btn-outline-primary">Addition(+)</button>
            <button type="submit" name="operator" value="sub" class="btn btn-outline-success">Subtraction(-)</button>
            <button type="submit" name="operator" value="mul" class="btn btn-outline-warning">Multiplication(X)</button>
            <button type="submit" name="operator" value="div" class="btn btn-outline-danger">Division(/)</button>
        </div>
    </form>

    <!-- Hiển thị kết quả -->
    <c:if test="${not empty result}">
        <div class="result alert alert-success text-center">
            Result
                ${operator == 'add' ? 'Addition' : operator == 'sub' ? 'Subtraction' : operator == 'mul' ? 'Multiplication' : 'Division'}:
                ${result}
        </div>
    </c:if>

    <!-- Hiển thị lỗi -->
    <c:if test="${not empty error}">
        <div class="result alert alert-danger text-center">
                ${error}
        </div>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
