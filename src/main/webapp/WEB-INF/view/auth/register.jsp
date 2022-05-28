<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="uma.taw.ubayspring.keys.AuthKeys" %>
<%@ page import="uma.taw.ubayspring.types.GenderEnum" %><%--
  Created by IntelliJ IDEA.
  User: Altair Bueno
  Date: 28/3/22
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title><spring:message key="register.title"/></title>
</head>
<style>
    body {
        display: flex;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
    }

    .form-signin {
        width: 100%;
        max-width: 330px;
        padding: 15px;
        margin: auto;
    }
</style>
<body class="text-center">
<main class="form-signin">
    <h1 class="h3 mb-3 fw-normal"><spring:message key="register.header"/></h1>
    <%--@elvariable id="registerDTO" type="uma.taw.ubayspring.dto.auth.RegisterDTO"--%>
    <form:form class="row g-2" modelAttribute="registerDTO" method="post">
        <div class="form-floating col-12">
            <form:input id="username" class="form-control" placeholder="Usuario"
                        type="text" pattern="<%=AuthKeys.USERNAME_REGEX%>"
                        required=""
                        aria-describedby="usernameHelp" path="username"/>
            <label for="username"><spring:message
                    key="register.form.username"/></label>
            <div id="usernameHelp" class="form-text">
                <spring:message key="register.form.username.help"/>
            </div>
        </div>
        <div class="form-floating col-12">
            <form:input id="password" class="form-control"
                        placeholder="Contraseña"
                        type="password" pattern="<%=AuthKeys.PASSWORD_REGEX%>"
                        required="" aria-describedby="passwordHelp"
                        path="password"/>
            <label for="password"><spring:message
                    key="register.form.password"/></label>
            <div id="passwordHelp" class="form-text">
                <spring:message key="register.form.password.help"/>
            </div>
        </div>
        <div class="form-floating col-12">
            <form:input id="repeat_password" class="form-control"
                        placeholder="Repite tu contraseña"
                        type="password" pattern="<%=AuthKeys.PASSWORD_REGEX%>"
                        required="" path="repeatPassword"/>
            <label for="repeat_password"><spring:message key="register.form.repeat_password"/></label>
        </div>
        <div class="form-floating col-12">
            <form:input id="first_name" class="form-control"
                        placeholder="Nombre"
                        type="text" required=""
                        maxlength="${AuthKeys.NAME_MAXLENGTH}" path="name"/>
            <label for="first_name"><spring:message key="register.form.first_name"/></label>
        </div>
        <div class="form-floating col-12">
            <form:input id="last_name" class="form-control"
                        placeholder="Apellidos"
                        type="text" required=""
                        maxlength="${AuthKeys.LAST_NAME_MAXLENGTH}"
                        path="lastName"/>
            <label for="last_name"><spring:message key="register.form.last_name"/></label>
        </div>
        <div class="col-md-6">
            <label for="address" class="form-label"><spring:message key="register.form.address"/></label>
            <form:input id="address" class="form-control" type="text"
                        required="" maxlength="${AuthKeys.ADDRESS_MAXLENGTH}"
                        path="address"/>
        </div>
        <div class="col-md-6">
            <label for="city" class="form-label"><spring:message key="register.form.city"/></label>
            <form:input id="city" class="form-control"
                        maxlength="${AuthKeys.CITY_MAXLENGTH}"
                        type="text" required="" path="city"/>
        </div>
        <div class="col-md-6">
            <label for="birth" class="form-label"><spring:message key="register.form.birth_date"/></label>
            <form:input id="birth" class="form-control"
                        type="date"
                        required="" path="birthDate"/>
        </div>
        <div class="col-md-6">
            <label for="gender" class="form-label"><spring:message key="register.form.gender"/></label>
            <form:select id="gender" class="form-select"
                         required=""
                         path="gender">
                <c:forEach items="${GenderEnum.values()}" var="enumValue">
                    <form:option value="${enumValue}">
                        <spring:message key="genderenum.${enumValue}"/>
                    </form:option>
                </c:forEach>
            </form:select>
        </div>
        <div class="row-1">
            <button type="submit" class="btn btn-primary col-6">
                <spring:message key="register.form.submit"/>
            </button>
        </div>
    </form:form>
    <small>
        <a class="link-primary"
           href="${pageContext.request.contextPath}/auth/login">
            <spring:message key="register.link.login"/>
        </a>
    </small>
</main>
</body>
</html>
