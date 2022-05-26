<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 5/4/22
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Modificar categoria</title>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>

<div class="d-flex flex-column align-items-center">
    <h1>Datos</h1>

    <div class="d-flex flex-column">
        <%--@elvariable id="categoryDTO" type="uma.taw.ubayspring.dto.categories.CategoryDTO"--%>
        <form:form action="modify" method="post" modelAttribute="categoryDTO">
            <div class="form col">
                <label>
                    <form:hidden path="id" class="form-control" name="id"/>
                    <br>
                    Nombre: <form:input required="" path="name" type="text" class="form-control"  maxlength="15"/> <br>
                    Descripción: <form:input required="" path="description" type="text" class="form-control"  maxlength="25"/> <br>

                </label>
            </div>
            <button type="submit" class="btn btn-primary mt-2">Modificar</button>
            <button type="button" class="btn btn-secondary mt-2" onclick="goBack()">Cancelar</button>
        </form:form>
    </div>
</div>

<script>
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>
