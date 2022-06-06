<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductClientDTO" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductBidDTO" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductForm.ProductFormParamsDTO" %>
<%@ page import="uma.taw.ubayspring.types.KindEnum" %>
<%@ page import="java.util.ResourceBundle" %>
<%--
Created by IntelliJ IDEA.
  Author: Francisco Javier Hernández
  Date: 6/4/22
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">

    <!-- Bootstrap icons -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css"
    />


    <title>Ubay | </title>
</head>
<body>
<%
    Object clientParameter = request.getAttribute("client");
    Object isFavParameter = request.getAttribute("isFav");
    Object highestBidParameter = request.getAttribute("highestBid");
    ProductFormParamsDTO productModel = (ProductFormParamsDTO) request.getAttribute("productModel");
    boolean isAdmin = clientParameter != null && ((ProductClientDTO) clientParameter).getKind().equals(KindEnum.admin), isFav = isFavParameter != null && (boolean) isFavParameter;
    double minBid;
    String closedLabel = ResourceBundle.getBundle("messages", request.getLocale()).getString("closedStatus");
    boolean cerrado = productModel.getStatus().equals("clo");
    Object imgId = request.getAttribute("imgId");
    String imgSrc = imgId == null ? "" : request.getContextPath() + "/image?id=" + URLEncoder.encode((String) imgId, StandardCharsets.UTF_8);
%>

<script>document.title = "Ubay | <%= productModel.getTitle()%>"</script>
<jsp:include page="../../components/navbar.jsp"/>

<div class="d-flex flex-column">
    <div class="d-flex flex-row m-auto p-2">
        <div class="p-2"><img src="<%=imgSrc%>" class="rounded" alt="<%=productModel.getTitle()%>"
                              style="height: 500px; width: 500px;"></div>
        <div class="d-flex flex-column p-2">
            <div class="p-2"><h1>${productModel.title}
            </h1></div>
            <div class="p-2"><h1>${productModel.price} €</h1></div>
            <div class="p-2">
                <h2><spring:message key="product.status.label"/>:</h2>
                <h4>
                    <c:choose>
                        <c:when test="${productModel.status eq 'clo'}">
                            <spring:message key="closedStatus"/>
                        </c:when>
                        <c:when test="${productModel.status eq 'act'}">
                            <spring:message key="activeStatus"/>
                        </c:when>
                    </c:choose>
                </h4>
            </div>
            <div class="p-2">
                <h2><spring:message key="description"/>: </h2>
                <h6>${productModel.description}
                </h6>
            </div>
            <%
                if (clientParameter != null || isAdmin) {
                    if (isAdmin || (clientParameter != null && clientParameter.equals(productModel.getVendor()))) {
            %>

            <div class="d-flex flex-row">

                <%
                    if(!cerrado){

                %>
                <!-- EDITAR -->
                <spring:message key="product.item.edit.label" var="edit"/>
                <form method="get" action="${pageContext.request.contextPath}/product/update">
                    <input type='hidden' name='id' value="${productModel.productId}"/>
                    <input class="btn btn-secondary btn-block me-2" type="submit" value="${edit}">
                </form>
                <%
                    }
                %>


                <!-- BORRAR: Button trigger modal -->
                <button type="button" class="btn btn-danger btn-block me-2" data-bs-toggle="modal"
                        data-bs-target="#deleteModal">
                    <spring:message key="product.item.delete.label"/>
                </button>

                <%
                    if(!cerrado){

                %>
                <button type="button" class="btn btn-warning me-2" data-bs-toggle="modal"
                        data-bs-target="#closeProductModal">
                    <spring:message key="product.item.close.label"/>
                </button>
                <%
                    }
                %>
            </div>

            <!-- Delete Modal -->
            <div class="modal fade" id="deleteModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel"><spring:message key="product.item.deletemodal.header"/></h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Cerrar"></button>
                        </div>
                        <div class="modal-body">
                            <spring:message key="product.item.deletemodal.body"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><spring:message key="cancel"/></button>
                            <form method="get" action="${pageContext.request.contextPath}/product/delete">
                                <input type='hidden' name='id' value="${productModel.productId}"/>
                                <spring:message key="product.item.delete.label" var="delete"/>
                                <input class="btn btn-danger" type="submit" value="${delete}">
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Close Product Modal -->
            <div class="modal fade" id="closeProductModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel2"><spring:message key="product.item.closemodal.header"/></h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Cerrar"></button>
                        </div>
                        <div class="modal-body">
                            <spring:message key="product.item.closemodal.body"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><spring:message key="product.item.closemodal.close"/></button>
                            <%--@elvariable id="productModel" type="uma.taw.ubayspring.dto.products.ProductForm.ProductFormParamsDTO"--%>
                            <form:form 
                                    method="post" 
                                    action="${pageContext.request.contextPath}/product/update"
                                    enctype="multipart/form-data"
                                    modelAttribute="productModel">
                                <form:hidden path="title"/>
                                <form:hidden path="description"/>
                                <form:hidden path="price"/>
                                <form:hidden path="category"/>
                                <form:hidden path="productId"/>
                                <form:hidden path="status" value="clo"/>
                                <spring:message key="product.item.close.label" var="close"/>
                                <input class="btn btn-warning" type="submit" value="${close}">
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>

            <%
            } else {
                if (!cerrado) {
                    if (highestBidParameter == null) {
                        minBid = productModel.getPrice();
            %>
            <div class="row">
                <h2><spring:message key="product.item.nobidsyettext"/></h2>
                <h2><spring:message key="product.item.minimumbidtext"/> ${productModel.price}
                </h2>
            </div>
            <%
            } else {
                ProductBidDTO highestBid = (ProductBidDTO) highestBidParameter;
                minBid = highestBid.getAmount();
            %>
            <div class="row">
                <h2><spring:message key="product.item.highestbidtext"/> <%=highestBid.getAmount()%>
                </h2>
            </div>
            <%
                }
            %>

            <div class="row align-items-center p-2">
                <%--@elvariable id="newBidModel" type="uma.taw.ubayspring.dto.bids.NewBidsDTO"--%>
                <form:form
                        method="post"
                        modelAttribute="newBidModel"
                        action="${pageContext.request.contextPath}/users/bids/new">
                    <spring:message key="product.item.bidamount.placeholder" var="amount"/>
                    <div class="col-auto w-25">
                        <form:input
                                type="number"
                                min="<%=minBid >= 0 ? minBid : 0%>"
                                step="0.01"
                                class="form-control"
                                placeholder="${amount}"
                                path="amount"
                                required="true"/>
                    </div>
                    <spring:message key="product.item.makebid.label" var="makebid"/>
                    <div class="col-auto">
                        <form:hidden
                                id='id-compra'
                                value="${productModel.productId}"
                                path="productID"/>
                        <input class="btn btn-primary" type="submit" value="${makebid}"/>
                    </div>
                </form:form>
            </div>
        </div>
        <div class="p-4">
            <%
                }
                if (clientParameter != null) {
                    ProductClientDTO client = (ProductClientDTO) clientParameter;
                    if(isFav){
            %>

            <div class="d-flex flex-row">
                <form method="get" action="${pageContext.request.contextPath}/users/deleteFavourite">
                    <input type='hidden' name='productID' value="${productModel.productId}"/>
                    <input type='hidden' name='clientID' value="<%=client.getId()%>"/>
                    <button class="btn btn btn-outline-danger btn-labeled" type="submit">
                        <span><i class="bi bi-star-fill"></i></span><spring:message key="removeFavourites"/>
                    </button>
                </form>
            </div>

            <%

            } else {
            %>

            <form method="get" action="${pageContext.request.contextPath}/users/addFavourite">
                <input type='hidden' name='productID' value="${productModel.productId}"/>
                <input type='hidden' name='clientID' value="<%=client.getId()%>"/>
                <button class="btn btn btn-outline-warning btn-labeled" type="submit">
                    <span><i class="bi bi-star-fill"></i></span><spring:message key="addFavourites"/>
                </button>
            </form>

            <%
                            }
                        }
                    }
                }
            %>
        </div>
    </div>

</div>

<script>
    function goBack() {
        window.history.back();
    }
</script>

</body>
</html>