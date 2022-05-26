<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="uma.taw.ubayspring.types.GenderEnum" %><%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 21/5/22
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Modificar usuario</title>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>
<div class="d-flex flex-column align-items-center">
    <div>
        <h1>Datos</h1>
    </div>

    <div class="d-flex flex-column">
        <%--@elvariable id="clientDTO" type="uma.taw.ubayspring.dto.users.ClientDTO"--%>
        <form:form action="modify" modelAttribute="clientDTO" method="post">
            <div class="form col">
                <form:hidden class="form-control" name="id" path="id"/> <br>
                Nombre: <form:input required="true" path="name" type="text" class="form-control" maxlength="10"/>
                Apellidos: <form:input required="true" path="lastName" type="text" class="form-control" maxlength="10"/> <br>
                Dirección: <form:input required="true" path="address" type="text" class="form-control"
                                  maxlength="15"/> <br>
                Ciudad: <form:input required="true" path="city" type="text" class="form-control" maxlength="10"/>
                <br>
                Género:
            <form:select path="gender" name="gender" class="form-select" items="${GenderEnum.values()}">
                <% for (GenderEnum gender : GenderEnum.values()){
                    String clientGender = request.getParameter("gender");
                    GenderEnum clientGenderEnum = GenderEnum.valueOf(clientGender);
                    if(gender.equals(clientGenderEnum)){
                %>
                <option selected><%=gender.toString()%></option>
                <%
                } //else {
                %>

                <option><%=gender.toString()%></option>
                <%
                    }
                %>
                <%//}%>
            </form:select> </br>
                Fecha de nacimiento: <form:input path="birthDate" type="date" class="form-control"/> <br>
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

