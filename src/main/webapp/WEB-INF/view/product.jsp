<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.URLEncoder" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <title><spring:message key="product.header.title"/></title>
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
                <h2><spring:message key="product.index.filter.header"/></h2>
                <%--@elvariable id="productModel" type="uma.taw.ubayspring.dto.products.index.ParamsDTO"--%>
                <form:form
                        modelAttribute="productModel"
                        method="get"
                        action="${pageContext.request.contextPath}/product">
                    <div class="form col">
                        <spring:message key="product.index.filter.name"/>
                        <form:input
                                type="text"
                                class="form-control"
                                maxlength="20"
                                path="name"/>
                        <spring:message key="product.index.filter.category"/>
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
                                    class="no-wrapper"
                                    itemValue="value"
                                    itemLabel="label"
                                    element="br"
                            />
                        </c:if>

                        <form:button type="submit" class="btn btn-primary mt-2"><spring:message key="search"/></form:button>
                        <a class="btn btn-secondary mt-2" href="<%=request.getContextPath()%>/product"><spring:message key="clean"/></a>
                    </div>
                </form:form>
            </div>

            <div class="col">
                <c:if test="<%=client != null%>">
                    <form:form method="get" action="${pageContext.request.contextPath}/product/new">
                        <div class="py-3" style="width: max-content; float: left">
                            <button type="submit" class="btn btn-primary"><spring:message key="product.index.createproduct.label"/></button>
                        </div>
                    </form:form>
                </c:if>

                <table class="table table-bordered text-center">
                    <thead>
                    <tr>
                        <th scope="col"><spring:message key="product.image"/></th>
                        <th scope="col"><spring:message key="product.title"/></th>
                        <th scope="col"><spring:message key="product.status.label"/></th>
                        <th scope="col"><spring:message key="description"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="<%=listas.getProductList()%>" var="p">

                        <tr onclick="window.location='${pageContext.request.contextPath}/product/item?id=${p.id}'">
                            <%
                                ProductDTO p = (ProductDTO) pageContext.getAttribute("p");
                                String imgSrc = p.getImages() == null ? "" : request.getContextPath() + "/image?id=" + URLEncoder.encode(p.getImages(), StandardCharsets.UTF_8);
                            %>
                            <td><img src="<%=imgSrc%>" class="img-thumbnail" alt="${p.title}" style="width: 200px"></td>

                            <td class="align-middle"><h3>${p.title}</h3></td>
                            <c:choose>
                                <c:when test="${p.closeDate eq null}">
                                    <td class="align-middle"><spring:message key="activeStatus"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td class="align-middle"><spring:message key="closedStatus"/></td>
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

<script>
    $('input[type=radio].no-wrapper').on('mousedown', function(e){
        var wasChecked = $(this).prop('checked');
        this.turnOff = wasChecked;
        $(this).prop('checked', !wasChecked);
    });

    $('input[type=radio].no-wrapper').on('click', function(e){
        $(this).prop('checked', !this.turnOff);
        this['turning-off'] = !this.turnOff;
    });
</script>
</html>
