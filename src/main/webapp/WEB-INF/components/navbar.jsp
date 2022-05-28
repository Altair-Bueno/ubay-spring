<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="uma.taw.ubayspring.types.KindEnum" %>
<%@ page
        import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%--
  Author: Francisco Javier Hernández
  Date: 30/3/22
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- JavaScript Bundle with Popper -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous">
</script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">

<style>
    .dropdown-toggle {
        outline: none !important;
    }
</style>

<%
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();

    boolean isAuthenticated = false;
    String username;

    if (principal instanceof UserDetails) {
        isAuthenticated = true;
        username = ((UserDetails) principal).getUsername();
    } else {
        username = principal.toString();
    }

    Map<String, String> navbar = new LinkedHashMap<>();
    navbar.put("Productos", "/product");
    if (isAuthenticated) {
        navbar.put("Categorías", "/categories");
        if (((UserDetails) principal).getAuthorities().contains(KindEnum.admin))
            navbar.put("Usuarios", "/users");
    }
%>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark"
     aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand"
           href="${pageContext.request.contextPath}">Ubay</a>
        <ul class="navbar-nav me-auto mb-2 mb-sm-0">
            <% for (String name : navbar.keySet()) { %>
            <%
                String route = navbar.get(name);
            %>
            <li class="nav-item">
                <a class="nav-link <%=route.equals(request.getRequestURI()) ? "active" : ""%>"
                   aria-current="page"
                   href="<%=route%>"><%=name%>
                </a>
            </li>
            <%}%>
        </ul>

        <%-- LANGUAGE SELECTOR --%>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNavDropdown"
                aria-controls="navbarNavDarkDropdown" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="languageDropdown">
            <ul class="navbar-nav ms-auto me-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#"
                       id="languageDropdownLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-translate"></i> <spring:message key="languagelabel"/>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end mt-2"
                        aria-labelledby="languageDropdownLink">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/language?code=en">🇬🇧
                            English</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/language?code=es">🇪🇸
                            Español</a></li>

                    </ul>
                </li>
            </ul>
        </div>

        <%-- USER DROPDOWN --%>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNavDropdown"
                aria-controls="navbarNavDarkDropdown" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ms-auto me-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#"
                       id="navbarDarkDropdownMenuLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <%=username%>
                    </a>
                    <%
                        if (isAuthenticated) {
                    %>
                    <ul class="dropdown-menu dropdown-menu-end mt-2"
                        aria-labelledby="navbarDarkDropdownMenuLink">
                        <% if (authentication.getAuthorities().contains(KindEnum.client)) { %>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/users/bids">Mis
                            pujas</a>
                        </li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/vendor/bids">Pujas
                            recibidas</a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/users/products">Productos
                            favoritos</a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/users/notifications">Notificaciones</a>
                        </li>

                        <% } %>

                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/auth/changePassword">Cambiar
                            mi contraseña</a></li>
                        <li>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/auth/signoff">
                                <input type="submit" class="dropdown-item"
                                       value="Cerrar sesión">
                            </form>
                        </li>
                    </ul>

                    <% } else { %>
                    <ul class="dropdown-menu dropdown-menu-end mt-2"
                        aria-labelledby="navbarDarkDropdownMenuLink">
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/auth/login">Iniciar
                            sesión</a></li>
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/auth/register">Registrarse</a>
                        </li>
                    </ul>
                    <% } %>
                </li>
            </ul>
        </div>
    </div>
</nav>



