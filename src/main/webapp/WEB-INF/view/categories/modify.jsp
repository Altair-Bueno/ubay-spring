<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Ubay | <spring:message key="modify"/></title>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>

<div class="d-flex flex-column align-items-center">
    <h1><spring:message key="dataheader"/></h1>

    <div class="d-flex flex-column">
        <%--@elvariable id="categoryDTO" type="uma.taw.ubayspring.dto.categories.CategoryDTO"--%>
        <form:form action="modify" method="post" modelAttribute="categoryDTO">
            <div class="form col">
                <label>
                    <form:hidden path="id" class="form-control" name="id"/>
                    <br>
                    <spring:message key="name"/> <form:input required="required" path="name" type="text" class="form-control"  maxlength="15"/> <br>
                </label>
            </div>

            <div>
                <label>
                    <spring:message key="description"/> <form:textarea path="description" rows = "4" cols = "30" required="required" maxlength="150" class="form-control"/>
                </label>
            </div>
            <button type="submit" class="btn btn-primary mt-2"><spring:message key="modify"/></button>
            <button type="button" class="btn btn-secondary mt-2" onclick="goBack()"><spring:message key="cancel"/></button>
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
