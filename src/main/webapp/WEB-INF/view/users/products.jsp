<%@ page import="uma.taw.ubayspring.dto.users.ProductDTO" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 21/5/22
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | <spring:message key="navbar.userdropdown.user.favproducts"/></title>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>
<div class="container mt-4">
    <div class="row">
        <h1><spring:message key="navbar.userdropdown.user.favproducts"/></h1>
    </div>
    <div class="row">
        <%
            List<ProductDTO> l = (List<ProductDTO>) request.getAttribute("favourite-products-list");
        %>

        <table class="table table-bordered text-center">
            <thead>
            <tr>
                <th scope="col"><spring:message key="product.image"/></th>
                <th scope="col"><spring:message key="product.title"/></th>
                <th scope="col"><spring:message key="product.status.label"/></th>
                <th scope="col"><spring:message key="description"/></th>
                <th scope="col"><spring:message key="product.item.delete.label"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="<%=l%>" var="p">
            <tr onclick="window.location='${pageContext.request.contextPath}/product/item?id=' + ${p.id}">
                <td><img src="${p.images}" class="img-thumbnail" alt="${p.title}" style="width: 200px">
                </td>
                <td class="align-middle"><h3>${p.title}
                </h3></td>
                <c:choose>
                    <c:when test="${p.closeDate eq null}">
                        <td class="align-middle"><spring:message key="activeStatus"/></td>
                    </c:when>
                    <c:otherwise>
                        <td class="align-middle"><spring:message key="closedStatus"/></td>
                    </c:otherwise>
                </c:choose>
                <td class="align-middle">${p.description}
                </td>

                <td class="align-middle">
                    <form method="get" action="${pageContext.request.contextPath}/users/deleteFavourite">
                        <input type='hidden' name='productID' value="${p.id}"/>
                        <input type='hidden' name='clientID' value="<%=request.getAttribute("clientID")%>"/>
                        <button class="btn btn btn-outline-danger btn-labeled" type="submit">
                            <span><i class="bi bi-star-fill"></i></span><spring:message key="removeFavourites"/>
                        </button>
                    </form>
                </td>

            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

