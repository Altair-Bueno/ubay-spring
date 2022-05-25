<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Ubay | Registro</title>
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
    <h1 class="h3 mb-3 fw-normal">Registro en Ubay</h1>
    <%--@elvariable id="registerDTO" type="uma.taw.ubayspring.dto.auth.RegisterDTO"--%>
    <form:form class="row g-2" modelAttribute="registerDTO" method="post">
        <div class="form-floating col-12">
            <form:input id="username" class="form-control" placeholder="Usuario"
                        type="text" pattern="<%=AuthKeys.USERNAME_REGEX%>"
                        required=""
                        aria-describedby="usernameHelp" path="username"/>
            <label for="username">Usuario</label>
            <div id="usernameHelp" class="form-text">Entre 3 y 20 caracteres
            </div>

        </div>
        <div class="form-floating col-12">
            <form:input id="password" class="form-control"
                        placeholder="Contraseña"
                        type="password" pattern="<%=AuthKeys.PASSWORD_REGEX%>"
                        required="" aria-describedby="passwordHelp"
                        path="password"/>
            <label for="password">Contraseña</label>
            <div id="passwordHelp" class="form-text">8 caracteres mínimo</div>
        </div>
        <div class="form-floating col-12">
            <form:input id="repeat_password" class="form-control"
                        placeholder="Repite tu contraseña"
                        type="password" pattern="<%=AuthKeys.PASSWORD_REGEX%>"
                        required="" path="repeatPassword"/>
            <label for="repeat_password">Repite tu contraseña</label>
        </div>
        <div class="form-floating col-12">
            <form:input id="first_name" class="form-control"
                        placeholder="Nombre"
                        type="text" required=""
                        maxlength="${AuthKeys.NAME_MAXLENGTH}" path="name"/>
            <label for="first_name">Nombre</label>
        </div>
        <div class="form-floating col-12">
            <form:input id="last_name" class="form-control"
                        placeholder="Apellidos"
                        type="text" required=""
                        maxlength="${AuthKeys.LAST_NAME_MAXLENGTH}"
                        path="lastName"/>
            <label for="last_name">Apellidos</label>
        </div>
        <div class="col-md-6">
            <label for="address" class="form-label">Dirección</label>
            <form:input id="address" class="form-control" type="text"
                        required="" maxlength="${AuthKeys.ADDRESS_MAXLENGTH}"
                        path="address"/>
        </div>
        <div class="col-md-6">
            <label for="city" class="form-label">Ciudad</label>
            <form:input id="city" class="form-control"
                        maxlength="${AuthKeys.CITY_MAXLENGTH}"
                        type="text" required="" path="city"/>
        </div>
        <div class="col-md-6">
            <label for="birth" class="form-label">Fecha de nacimiento</label>
            <form:input id="birth" class="form-control"
                        type="date"
                        required="" path="birthDate"/>
        </div>
        <div class="col-md-6">
            <label for="gender" class="form-label">Género</label>
            <form:select id="gender" class="form-select"
                         required=""
                         path="gender">
                <form:options items="${GenderEnum.values()}"/>
            </form:select>
        </div>
        <div class="row-1">
            <button type="submit" class="btn btn-primary col-6">Crear cuenta
            </button>
        </div>
    </form:form>
    <small>
        <a class="link-primary"
           href="${pageContext.request.contextPath}/auth/login">
            ¿Tiene ya una cuenta en Ubay?
        </a>
    </small>
</main>
</body>
</html>
