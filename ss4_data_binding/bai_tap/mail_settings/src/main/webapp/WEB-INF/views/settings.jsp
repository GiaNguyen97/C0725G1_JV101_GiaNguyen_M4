<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8"/>
  <title><spring:message code="title.settings"/></title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
  <style>
    body { padding: 40px; font-family: Arial, sans-serif; }
    .settings-container { width: 420px; }
    label { font-weight: bold; margin-bottom: 6px; }
    .btn-cancel { background: white; border: 1px solid black; color: black; width: 100px; }
    .btn-update { width: 100px; }
    textarea { resize: none; }
  </style>
</head>
<body>

<div class="settings-container">
  <h2><spring:message code="title.settings"/></h2>

  <form:form method="post" modelAttribute="mailSettings" action="${pageContext.request.contextPath}/settings">
    <!-- Languages -->
    <div class="mt-3">
      <label><spring:message code="language"/></label>
      <form:select path="language" cssClass="form-select">
        <<form:option value="en">English</form:option>
        <form:option value="vi">Vietnamese</form:option>
        <form:option value="ja">Japanese</form:option>
        <form:option value="zh">Chinese </form:option>
      </form:select>
    </div>

    <!-- Page Size -->
    <div class="mt-3">
      <label><spring:message code="page.size"/></label>
      <div class="d-flex align-items-center gap-2">
        <span><spring:message code="show"/></span>
        <form:select path="pageSize" cssClass="form-select" style="width: 80px">
          <form:option value="5">5</form:option>
          <form:option value="10">10</form:option>
          <form:option value="15">15</form:option>
          <form:option value="25">25</form:option>
          <form:option value="50">50</form:option>
          <form:option value="100">100</form:option>
        </form:select>
        <span><spring:message code="emails.per.page"/></span>
      </div>
    </div>

    <!-- Spam Filter -->
    <div class="mt-3">
      <label><spring:message code="spam.filter"/></label><br>
      <form:checkbox path="spamFilter"/>
      <span><spring:message code="enable.spam"/></span>
    </div>

    <!-- Signature -->
    <div class="mt-3">
      <label><spring:message code="signature"/></label>
      <form:textarea path="signature" cssClass="form-control" rows="4"/>
    </div>

    <!-- Buttons -->
    <div class="mt-4 d-flex gap-3">
      <button type="submit" class="btn btn-primary btn-update">
        <spring:message code="button.update"/>
      </button>
      <a class="btn btn-cancel" href="/settings?lang=${mailSettings.language}">
        <spring:message code="button.cancel"/>
      </a>
    </div>

  </form:form>
</div>

</body>
</html>
