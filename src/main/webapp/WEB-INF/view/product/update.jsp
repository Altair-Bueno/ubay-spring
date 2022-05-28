<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductDTO" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductCategoryDTO" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductForm.ProductFormParamsDTO" %>
<%--
  Created by IntelliJ IDEA.
  Author: Francisco Javier Hernández
  Date: 29/3/22
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Actualizar producto</title>
</head>
<body>
<%
    List<ProductCategoryDTO> categoryList = (List<ProductCategoryDTO>) request.getAttribute("categoryList");
    String imgSrc = request.getContextPath() + "/image?id=" + URLEncoder.encode((String)request.getAttribute("imageId"), StandardCharsets.UTF_8);
%>
<jsp:include page="../../components/navbar.jsp"/>

<%--@elvariable id="productModel" type="uma.taw.ubayspring.dto.products.ProductForm.ProductFormParamsDTO"--%>
<form:form
        method="post"
        action="${pageContext.request.contextPath}/product/update"
        enctype="multipart/form-data"
        modelAttribute="productModel">
    <div class="d-flex flex-row m-auto" style="width: 1000px">

        <%-- BLOQUE I - Imagen --%>
        <div class="d-flex flex-column p-2">
            <div class="p-2">
                <img src="<%=imgSrc%>" id="output" style="height: auto; width: 500px;"/>
            </div>
            <div class="form-group mb-3 w-75 p-2">
                <label for="img" class="form-label">Cambiar imagen: </label>
                <form:input
                        type="file"
                        accept="image/*"
                        onchange="loadFile(event)"
                        class="form-control"
                        id="img"
                        path="image"/>
            </div>

        </div>

        <%-- BLOQUE II - Resto --%>
        <div class="d-flex flex-column p-2">
            <%-- Titulo --%>
            <div class="form-group w-75 p-2">
                <label for="tit">Título: </label>
                <form:input
                        type="text"
                        id="tit"
                        class="form-control"
                        path="title"
                        required="true"
                        maxlength="20"/>
            </div>

            <%-- Descripcion --%>
            <div class="p-2">
                <label for="desc">Descripcion: </label>
                <form:textarea
                        id="desc"
                        class="form-control"
                        rows="4"
                        cols="50"
                        path="description"/>
            </div>

            <%-- Precio --%>
            <div class="p-2">
                <label for="precio">Precio: </label>
                <form:input
                        type="number"
                        id="precio"
                        class="form-control"
                        path="price"
                        required="true"
                        maxlength="6"/>
            </div>

            <%-- Categoria --%>
            <div class="p-2">
                <label>Categoria: </label>

                <form:select path="category" required="true">
                    <form:options items="<%=categoryList%>" itemValue="id" itemLabel="name"/>
                </form:select>
            </div>

            <%-- Submit --%>
            <div class="p-2">
                <form:hidden
                        name="productId"
                        path="productId"
                />
                <div class="d-flex flex-row p-2">
                    <div class="p-2">
                        <input class="btn btn-primary p-2" type="submit" value="Confirmar">
                    </div>
                    <div class="p-2">
                        <input class="btn btn-secondary p-2" type="submit" value="Cancelar"
                               formaction="item" formnovalidate>
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
