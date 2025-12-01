<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="title.result"/></title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>

    <style>
        body { padding: 40px; font-family: Arial, sans-serif; }
        .container-box {
            width: 420px;
        }
        pre {
            background: #f5f5f5;
            padding: 10px;
            border-radius: 6px;
        }
        .btn-back {
            margin-top: 20px;
        }
    </style>
</head>

<body>

<div class="container-box">

    <h2><spring:message code="title.result"/></h2>
    <hr>

    <p><strong><spring:message code="language"/>:</strong> ${mailSettings.language}</p>
    <p><strong><spring:message code="page.size"/>:</strong> ${mailSettings.pageSize}</p>

    <p><strong><spring:message code="spam.filter"/>:</strong>
        <c:choose>
            <c:when test="${mailSettings.spamFilter}">
                <spring:message code="enable.spam"/>
            </c:when>
            <c:otherwise>
                <spring:message code="spam.disabled" /> <!-- cần thêm vào messages_*.properties -->
            </c:otherwise>
        </c:choose>
    </p>

    <p><strong><spring:message code="signature"/>:</strong></p>
    <pre>${mailSettings.signature}</pre>

    <a href="/settings?lang=${mailSettings.language}" class="btn btn-primary btn-back">
        <spring:message code="back.settings"/>
    </a>

</div>

</body>
</html>
