<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="uma.taw.ubayspring.dto.notifications.BidsDTO" %>
<%@ page import="uma.taw.ubayspring.dto.notifications.ProductDTO" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%--
  Created by IntelliJ IDEA.
  Author: Francisco Javier Hernández
  Date: 24/4/22
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title><spring:message key="notifications.header"/></title>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>
<div class="container">
    <h1><spring:message key="notifications"/></h1>
    <%
        HashMap<BidsDTO, Boolean> notificaciones = (HashMap<BidsDTO, Boolean>) request.getAttribute("notifications");

        if (notificaciones.size() == 0) {
    %>
    <div class="m-auto w-50 h-75 d-flex justify-content-center align-items-center">
        <h1><spring:message key="notifications.nonotificationstext"/></h1>
    </div>

    <%
    } else {
    %>

    <table class="table table-bordered text-center">
        <thead>
        <tr>
            <th scope="col"><spring:message key="product.image"/></th>
            <th scope="col"><spring:message key="product.title"/></th>
            <th scope="col"><spring:message key="closedate"/></th>
            <th scope="col"><spring:message key="notifications.resolutionheader"/></th>
        </tr>
        </thead>
        <tbody>
        <%
            for (BidsDTO b : notificaciones.keySet()) {
                ProductDTO p = b.getProduct();
                String imgSrc = p.getImages() == null ? "" : request.getContextPath() + "/image?id=" + URLEncoder.encode(p.getImages(), StandardCharsets.UTF_8);
        %>

        <tr onclick="window.location='${pageContext.request.contextPath}/product/item?id=' + <%=p.getId()%>">
            <td><img src="<%=imgSrc%>" class="img-thumbnail" alt="<%=p.getTitle()%>" style="width: 200px"></td>
            <td class="align-middle"><h3><%=p.getTitle()%>
            </h3></td>
            <td class="align-middle"><%=p.getCloseDate()%>
            </td>
            <td class="align-middle">
                <c:choose>
                    <c:when test="${notifications.get(b)}">
                        <spring:message key="notifications.won"/>
                    </c:when>
                    <c:otherwise>
                        <spring:message key="notifications.lost"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

        <%
            }
        %>
        </tbody>
    </table>
    <%
        }
    %>
</div>
</body>
</html>
