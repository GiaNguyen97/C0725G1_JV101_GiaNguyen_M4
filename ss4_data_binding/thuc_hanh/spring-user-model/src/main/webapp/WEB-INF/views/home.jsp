<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.example.springusermodel.entity.Login" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h3>Home</h3>
<form:form action="login" method="post" modelAttribute="login">
  <fieldset>
    <legend>Login</legend>
    <table>
      <tr>
        <td><form:label path="account">Account:</form:label></td>
        <td><form:input path="account" /></td>
      </tr>
      <tr>
        <td><form:label path="password">Password:</form:label></td>
        <td>
          <form:input path="password" type="password" id="passwordField"/>
          <button type="button" id="toggleBtn">👁</button>
        </td>
      </tr>
      <tr>
        <td></td>
        <td><form:button>Login</form:button></td>
      </tr>
    </table>
  </fieldset>
</form:form>
<script>
  const pass = document.getElementById("passwordField");
  const btn = document.getElementById("toggleBtn");

  btn.addEventListener("click", function () {
    if (pass.type === "password") {
      pass.type = "text";
      btn.textContent = "🙈"; // icon khi đang hiện
    } else {
      pass.type = "password";
      btn.textContent = "👁"; // icon khi đang ẩn
    }
  });
</script>
</body>

</html>