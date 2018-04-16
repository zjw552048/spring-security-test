<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Hello Spring Security</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="/css/main.css" th:href="@{/css/main.css}" />
</head>
<body>
<%--<div th:substituteby="index::logout"></div>--%>
<div th:fragment="logout" class="logout" sec:authorize access="hasRole('USER')">
    Logged in user: <span sec:authentication="name"></span> |
    Roles: <span sec:authentication="principal.authorities"></span>
    <div>
        <form action="/logout" th:th:action="@{/logout}" method="post">
            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
            <input type="submit" value="Logout" />
        </form>
    </div>
</div>
<h1>This is a secured page!</h1>
<p><a href="/index" th:href="@{/index}">Back to home page</a></p>
</body>
</html>
