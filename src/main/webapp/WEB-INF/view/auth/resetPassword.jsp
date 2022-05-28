<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="uma.taw.ubayspring.keys.AuthKeys" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: Altair Bueno
  Date: 28/3/22
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title><spring:message key="resetPassword.title"/></title>
</head>
<style>
    html,
    body {
        height: 100%;
    }

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

    .form-signin .checkbox {
        font-weight: 400;
    }

    .form-signin .form-floating:focus-within {
        z-index: 2;
    }

    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin .last {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<body class="text-center">
<main class="form-signin">
    <%--@elvariable id="resetPasswordDTO" type="uma.taw.ubayspring.dto.auth.ResetPasswordDTO"--%>
    <form:form method="post"
               modelAttribute="resetPasswordDTO"
               action="${pageContext.request.contextPath}/auth/resetPassword">
        <h1 class="h3 mb-3 fw-normal"><spring:message key="resetPassword.header"/></h1>
        <div class="form-floating">
            <form:input
                    type="password"
                    class="form-control"
                    id="floatingPassword"
                    placeholder="New Password"
                    pattern="${AuthKeys.PASSWORD_REGEX}" required=""
                    aria-describedby="passwordHelp" path="newPassword"/>
            <label for="floatingPassword"><spring:message key="resetPassword.form.new_password"/></label>
            <div id="passwordHelp" class="form-text"><spring:message key="resetPassword.form.new_password.help"/></div>
        </div>
        <div class="form-floating last">
            <form:input
                    type="password"
                    class="form-control"
                    id="floatingRepeat"
                    placeholder="Repeat New Password"
                    pattern="${AuthKeys.PASSWORD_REGEX}" required=""
                    path="repeatPassword"/>
            <label for="floatingRepeat"><spring:message key="resetPassword.form.repeat_password"/></label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">
            <spring:message key="resetPassword.form.submit"/>
        </button>
        <form:hidden path="loginID"/>
        <form:hidden path="requestID"/>
    </form:form>
</main>
</body>
</html>