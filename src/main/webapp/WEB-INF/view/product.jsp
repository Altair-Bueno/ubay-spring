<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="uma.taw.ubayspring.keys.ProductKeys" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductCategoryDTO" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductDTO" %>
<%@ page import="uma.taw.ubayspring.dto.products.index.ListsDTO" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductClientDTO" %>
<%--
  Created by IntelliJ IDEA.
  Author: Francisco Javier Hernández
  Date: 28/3/22
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Productos</title>
</head>

<style>
    tr {
        cursor: pointer
    }

    th {
        cursor: default;
    }

    tr:hover {
        background-color: #F5F5F5;
    }
</style>

<body>
<jsp:include page="../components/navbar.jsp"/>

<%
    ListsDTO listas = (ListsDTO) request.getAttribute("listas");
    Integer pageLimit = (Integer) request.getAttribute("pageLimit");
    ProductClientDTO client = (ProductClientDTO) request.getAttribute("client");
%>

<div class="mx-auto" style="width: 1500px;">
    <div class="container">
        <div class="row">
            <div class="col-3">
                <h2>Filtros:</h2>
                <%--@elvariable id="productModel" type="uma.taw.ubayspring.dto.products.index.ParamsDTO"--%>
                <form:form
                        modelAttribute="productModel"
                        method="get"
                        action="${pageContext.request.contextPath}/product">
                    <div class="form col">
                        Nombre del producto:
                        <form:input
                                type="text"
                                class="form-control"
                                maxlength="20"
                                path="name"/>
                        Categoría:
                        <form:select
                                class="form-select"
                                path="category">
                            <c:choose>
                                <c:when test="${productModel.category eq 0}">
                                    <form:option selected="true" value="0">--</form:option>
                                </c:when>
                                <c:otherwise>
                                    <form:option selected="false" value="0">--</form:option>
                                </c:otherwise>
                            </c:choose>

                            <form:options items="<%=listas.getCategoryList()%>" itemValue="id" itemLabel="name"/>
                        </form:select>
                        <c:if test="<%=client != null%>"> <%-- if(logged in) --%>
                            <form:radiobuttons
                                    path="favOwnedFilter"
                                    items="<%=listas.getFavOwnedFilterOptions()%>"
                                    itemValue="value"
                                    itemLabel="label"
                                    element="br"
                            />
                        </c:if>

                        <form:button type="submit" class="btn btn-primary mt-2">Buscar</form:button>
                        <a class="btn btn-secondary mt-2" href="<%=request.getContextPath()%>/product">Limpiar</a>
                    </div>
                </form:form>
            </div>

            <div class="col">
                <c:if test="<%=client != null%>">
                    <form:form method="get" action="${pageContext.request.contextPath}/product/new">
                        <div class="py-3" style="width: max-content; float: left">
                            <button type="submit" class="btn btn-primary">Subir producto</button>
                        </div>
                    </form:form>
                </c:if>

                <table class="table table-bordered text-center">
                    <thead>
                    <tr>
                        <th scope="col">Imagen</th>
                        <th scope="col">Titulo</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Descripcion</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="<%=listas.getProductList()%>" var="p">
                        <tr onclick="window.location='${pageContext.request.contextPath}/product/item?id=${p.id}'">
                            <td><img src="${p.images}" class="img-thumbnail" alt="${p.title}" style="width: 200px">
                        </td>
                            <td class="align-middle"><h3>${p.title}</h3></td>
                            <c:choose>
                                <c:when test="${p.closeDate eq null}">
                                    <td class="align-middle">Abierto</td>
                                </c:when>
                                <c:otherwise>
                                    <td class="align-middle">Cerrado</td>
                                </c:otherwise>
                            </c:choose>

                            <td class="align-middle">${p.description}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <form:form
                        id="pagination"
                        method="get"
                        modelAttribute="productModel"
                        action="${pageContext.request.contextPath}/product">
                    <c:if test="${productModel.category ne 0}">
                        <form:hidden
                                name="category"
                                path="category"/>
                    </c:if>
                    <c:if test="${productModel.name ne ''}">
                        <form:hidden
                                name="name"
                                path="name"/>
                    </c:if>

                    <c:choose>
                        <c:when test="${productModel.favOwnedFilter eq 'favFilter'}">
                            <form:hidden
                                    path="favOwnedFilter"/>
                        </c:when>
                        <c:when test="${productModel.favOwnedFilter eq 'ownedFilter'}">
                            <form:hidden
                                    path="favOwnedFilter"/>
                        </c:when>
                    </c:choose>

                    <nav aria-label="Pagination">
                        <ul class="pagination justify-content-center">
                            <c:forEach begin="1" end="<%=pageLimit%>" var="pageNum">
                                <li class="page-item ${(pageNum eq 1 and page == null) or (pageNum eq productModel.page)? 'active' : ''}">
                                    <form:input
                                            type="submit"
                                            class="page-link"
                                            aria-checked="${pageNum eq productModel.page}"
                                            value="${pageNum}"
                                            path="page"
                                    />
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
