<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Ubay | <spring:message key="modify"/></title>
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
                <spring:message key="name"/>: <form:input required="true" path="name" type="text" class="form-control" maxlength="10"/>
                <spring:message key="register.form.last_name"/>: <form:input required="true" path="lastName" type="text" class="form-control" maxlength="10"/> <br>
                <spring:message key="register.form.address"/>: <form:input required="true" path="address" type="text" class="form-control"
                                  maxlength="15"/> <br>
                <spring:message key="register.form.city"/>: <form:input required="true" path="city" type="text" class="form-control" maxlength="10"/>
                <br>
                <spring:message key="register.form.gender"/>:
            <form:select path="gender" name="gender" class="form-select" required="">
                <%
                    String clientGender = request.getParameter("gender");
                    GenderEnum clientGenderEnum = GenderEnum.valueOf(clientGender);
                %>
                <c:forEach items="${GenderEnum.values()}" var="enumValue">
                    <c:if test="enumValue == <%=clientGenderEnum%>">
                        <form:option value="${enumValue}" selected="">
                            <spring:message key="genderenum.${enumValue}"/>
                        </form:option>
                    </c:if>

                    <form:option value="${enumValue}">
                        <spring:message key="genderenum.${enumValue}"/>
                    </form:option>
                </c:forEach>
            </form:select> </br>
                <spring:message key="register.form.birth_date"/>: <form:input path="birthDate" type="date" class="form-control"/> <br>
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

