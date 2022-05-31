<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductCategoryDTO" %>
<%--
Created by IntelliJ IDEA.
  Author: Francisco Javier Hernández
  Date: 28/3/22
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title><spring:message key="product.new.header"/></title>
</head>
<body>

<%
    List<ProductCategoryDTO> categoryList = (List<ProductCategoryDTO>) request.getAttribute("categoryList");
%>

<jsp:include page="../../components/navbar.jsp"/>

<%--@elvariable id="productModel" type="uma.taw.ubayspring.dto.products.ProductForm.ProductFormParamsDTO"--%>
<form:form 
        method="post" 
        enctype="multipart/form-data"
        action="${pageContext.request.contextPath}/product/new"
        modelAttribute="productModel"
>
    <div class="d-flex flex-row m-auto" style="width: 1000px">

        <%-- BLOQUE I - Imagen --%>
        <div class="d-flex flex-column p-2">
            <div class="p-2">
                <img id="output" style="height: auto; width: 500px;"/>
            </div>
            <div class="form-group mb-3 w-75 p-2">
                <label for="img" class="form-label"><spring:message key="product.new.uploadimage"/></label>
                <form:input
                        type="file"
                        accept="image/*"
                        class="form-control"
                        id="img"
                        onchange="loadFile(event)"
                        path="image"/>
            </div>

        </div>

        <%-- BLOQUE II - Resto --%>
        <div class="d-flex flex-column p-2">
            <%-- Titulo --%>
            <div class="form-group w-75 p-2">
                <label for="tit"><spring:message key="product.title"/>:</label>
                <form:input
                        type="text"
                        id="tit"
                        class="form-control"
                        path="title"
                        required="true"
                />
            </div>

            <%-- Descripcion --%>
            <div class="p-2">
                <label for="desc"><spring:message key="description"/>:</label>
                <form:textarea
                        id="desc"
                        class="form-control"
                        rows="4"
                        cols="50"
                        path="description"
                />
            </div>

            <%-- Precio --%>
            <div class="p-2">
                <label for="precio"><spring:message key="product.update.price"/></label>
                <form:input
                        type="number"
                        id="precio"
                        class="form-control"
                        path="price"
                        required="true"/>
            </div>

            <%-- Categoria --%>
            <div class="p-2">
                <label><spring:message key="product.index.filter.category"/></label>
                <form:select name="category" path="category">
                    <form:options items="<%=categoryList%>" itemValue="id" itemLabel="name"/>
                </form:select>
            </div>

            <%-- Submit --%>
            <div class="p-2">
                <div class="d-flex flex-row p-2">
                    <div class="p-2">
                        <spring:message key="confirm" var="confirm"/>

                        <input class="btn btn-primary p-2" type="submit" value="${confirm}"/>
                    </div>
                    <div class="p-2">
                        <a href="${pageContext.request.contextPath}/product" class="btn btn-secondary p-2"><spring:message key="cancel"/></a>
                    </div>
                </div>

            </div>
        </div>
    </div>

</form:form>
</body>
<script>
    var loadFile = function (event) {
        var image = document.getElementById('output');
        image.src = URL.createObjectURL(event.target.files[0]);
    };
</script>
</html>
