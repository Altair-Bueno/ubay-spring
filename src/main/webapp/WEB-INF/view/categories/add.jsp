<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: José Luis Bueno Pachón
  Date: 5/4/22
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | <spring:message key="categories.addnew.label"/></title>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>

<div class="d-flex flex-column align-items-center">
    <h1>Datos</h1>
    <div class="d-flex flex-column">
        
        <%--@elvariable id="addCategoryDTO" type="uma.taw.ubayspring.dto.categories.AddCategoryDTO"--%>
        <form:form action="add" method="post" modelAttribute="addCategoryDTO">
            <div class="form col">
                <label>
                    <spring:message key="name"/>
                    <form:input path="name" required="required" type="text" maxlength="15" size="15" class="form-control" /> <br>
                </label>
            </div>

            <div class="form col">
                 <label>

                      <spring:message key="description"/> <form:textarea path="description" rows = "4" cols = "30" required="required" maxlength="150" class="form-control"/>
                 </label>
            </div>
            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-primary mt-2"><spring:message key="create"/></button>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>
