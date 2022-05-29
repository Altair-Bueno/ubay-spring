<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="uma.taw.ubayspring.keys.VendorKeys" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubayspring.dto.bids.ReceivedBidsDTO" %>
<%@ page import="uma.taw.ubayspring.dto.bids.ProductDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="uma.taw.ubayspring.dto.bids.BidsParamsDTO" %>
<%--
  Created by IntelliJ IDEA.
  Author: Altair Bueno 90% Francisco Javier Hernández 10%
  Date: 5/4/22
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ReceivedBidsDTO> bidsList = (List<ReceivedBidsDTO>) request.getAttribute(VendorKeys.BID_LIST);
    BidsParamsDTO bidsModel = (BidsParamsDTO) request.getAttribute("bidsModel");
    int pageNumber = 0;
    try {
        pageNumber = bidsModel.getPage();
    } catch (Exception ignored) {
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Received Bids</title>
</head>
<body>
<jsp:include page="../../../components/navbar.jsp"/>

<div class="container mt-4">
    <div class="row">
        <h1>Pujas recibidas</h1>
    </div>
    <div class="row">

        <aside class="col-md-12 col-lg-2">
            <%--@elvariable id="bidsModel" type="uma.taw.ubayspring.dto.bids.BidsParamsDTO"--%>
            <form:form
                    method="get"
                    modelAttribute="bidsModel"
                    action="${pageContext.request.contextPath}/vendor/bids">
                <div class="mb-3">
                    <label for="startDate" class="form-label">Fecha de publicación</label>
                    <form:input type="date" class="form-control" id="startDate" path="startDate"/>

                </div>
                <div class="mb-3">
                    <label for="endDate" class="form-label">Fecha de cierre</label>
                    <form:input type="date" class="form-control" id="endDate" path="endDate"/>
                </div>
                <div class="mb-3">
                    <label for="productTitle" class="form-label">Título del producto</label>
                    <form:input type="text" class="form-control" id="productTitle" path="productTitle"/>
                </div>
                <div class="mb-3">
                    <label for="clientName" class="form-label">Nombre del cliente</label>
                    <form:input type="text" class="form-control" id="clientName" path="clientName"/>
                </div>
                <div class="mb-3">
                    <label for="pageNumber" class="form-label">Página</label>
                    <form:input type="number" class="form-control" id="pageNumber" path="page"/>
                </div>
                <form:select
                        class="form-select mb-3"
                        id="orderBy"
                        path="orderBy"
                >
                    <form:options items="<%=VendorKeys.ORDER_BY_LIST%>"/>
                </form:select>
                <div class="form-check mb-3">
                    <form:checkbox
                            class="form-check-input"
                            id="flexCheckDefault"
                            path="asc"
                    />
                    <label class="form-check-label" for="flexCheckDefault">
                        Ordenar ascendentemente
                    </label>
                </div>
                <form:button type="submit" class="btn btn-primary">Filtrar</form:button>
                <a class="btn btn-secondary" href="<%=request.getContextPath()%>/vendor/bids">Limpiar</a>
            </form:form>
        </aside>
        <main class="table-responsive col">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Fecha de publicación</th>
                    <th scope="col">Valor</th>
                    <th scope="col">Producto</th>
                    <th scope="col">Nombre del cliente</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (int i = 0; i < bidsList.size(); i++) {
                %>
                <tr>
                    <%
                        ReceivedBidsDTO bid = bidsList.get(i);
                        ProductDTO product = bid.getProduct();
                    %>
                    <th scope="row">
                        <%=1 + i + pageNumber * 10%>
                    </th>
                    <td>
                        <%=dateFormat.format(bid.getPublishDate())%>
                    </td>
                    <td>
                        $<%=bid.getAmount()%>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/product/item?id=<%=product.getId()%>">
                            <%=product.getTitle()%>
                        </a>
                    </td>
                    <td>
                        <%=bid.getUser().getName()%>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </main>
    </div>
</div>

</body>
</html>