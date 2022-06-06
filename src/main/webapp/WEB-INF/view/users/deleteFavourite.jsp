<%--
  Created by IntelliJ IDEA.
  User: José Luis Bueno Pachón
  Date: 21/5/22
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Eliminar producto favorito</title>
</head>
<body>
<div class="container">

    <div class="col-6 position-absolute top-50 start-50 translate-middle">
        <form class="form" action="${pageContext.request.contextPath}/users/products" method="get">
            <h1>¿Estás seguro de que quieres eliminar este producto de favoritos?</h1>
            <button type="submit" class="btn btn-danger mt-2">Confirmar</button>
        </form>
    </div>

</div>
</body>
</html>
