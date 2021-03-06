<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: José Luis Bueno Pachón
  Date: 11/4/22
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Add cateogira favorita</title>
</head>
<body>


<div class="container">
    <div class="container">
        <div class="col-6 position-absolute top-50 start-50 translate-middle">
            <form class="form" action="../categories" method="get">
                <h1><spring:message key="categories.addFavourite.text"></h1>
                <button type="submit" class="btn btn-primary mt-2"><spring:message key="confirm"></button>
            </form>

        </div>
    </div>
</div>
</body>
</html>
