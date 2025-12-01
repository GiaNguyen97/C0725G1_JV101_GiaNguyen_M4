<%--
  Created by IntelliJ IDEA.
  User: Hi
  Date: 01/12/2025
  Time: 8:28 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Sandwich Condiments</title>
  <c:import url="header.jsp"/>
</head>
<body class="bg-body-secondary">

<div class="container mt-5">
  <div class="card shadow-lg" style="max-width: 450px; margin: auto;">
    <div class="card-body">
      <h3 class="text-center mb-4 text-primary">Chọn gia vị cho Sandwich</h3>

      <form action="save" method="get">

        <div class="form-check mb-2">
          <input class="form-check-input" type="checkbox" name="condiment" value="Lettuce" id="c1">
          <label class="form-check-label" for="c1">Lettuce</label>
        </div>

        <div class="form-check mb-2">
          <input class="form-check-input" type="checkbox" name="condiment" value="Tomato" id="c2">
          <label class="form-check-label" for="c2">Tomato</label>
        </div>

        <div class="form-check mb-2">
          <input class="form-check-input" type="checkbox" name="condiment" value="Mustard" id="c3">
          <label class="form-check-label" for="c3">Mustard</label>
        </div>

        <div class="form-check mb-3">
          <input class="form-check-input" type="checkbox" name="condiment" value="Sprouts" id="c4">
          <label class="form-check-label" for="c4">Sprouts</label>
        </div>

        <button type="submit" class="btn btn-primary w-100">
          Xác nhận
        </button>

      </form>
    </div>
  </div>
</div>

</body>
</html>
