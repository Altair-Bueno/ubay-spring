<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductDTO" %>
<%@ page import="uma.taw.ubayspring.dto.products.ProductClientDTO" %>
<%@ page import="uma.taw.ubayspring.keys.UsersKeys" %>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


    <title>Ubay | Producto</title>
</head>
<body>
<%
    Object clientParameter = request.getAttribute("client");
    Object isFavParameter = request.getAttribute("isFav");
    Object highestBidParameter = request.getAttribute("highestBid");
    ProductFormParamsDTO productModel = (ProductFormParamsDTO) request.getAttribute("productModel");
    boolean isAdmin = clientParameter != null && ((ProductClientDTO) clientParameter).getKind().equals(KindEnum.admin), isFav = isFavParameter != null && (boolean) isFavParameter;
    double minBid;
    String closed = ResourceBundle.getBundle("messages", request.getLocale()).getString("closedStatus");
    boolean cerrado = productModel.getStatus().equals(closed);
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
                <h2>Estado: </h2>
                <h4>${productModel.status}
                </h4>
            </div>
            <div class="p-2">
                <h2>Descripcion: </h2>
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
                <form method="get" action="${pageContext.request.contextPath}/product/update">
                    <input type='hidden' name='id' value="${productModel.productId}"/>
                    <input class="btn btn-secondary btn-block me-2" type="submit" value="Editar">
                </form>
                <%
                    }
                %>


                <!-- BORRAR: Button trigger modal -->
                <button type="button" class="btn btn-danger btn-block" data-bs-toggle="modal"
                        data-bs-target="#deleteModal">
                    Eliminar
                </button>

                <%
                    if(!cerrado){

                %>
                <button type="button" class="btn btn-warning" data-bs-toggle="modal"
                        data-bs-target="#closeProductModal">
                    Cerrar
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
                            <h5 class="modal-title" id="staticBackdropLabel">Eliminar producto</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Cerrar"></button>
                        </div>
                        <div class="modal-body">
                            ¿Está seguro de que quiere eliminar el producto?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <form method="get" action="${pageContext.request.contextPath}/product/delete">
                                <input type='hidden' name='id' value="${productModel.productId}"/>
                                <input class="btn btn-danger" type="submit" value="Eliminar">
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
                            <h5 class="modal-title" id="staticBackdropLabel2">Eliminar producto</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Cerrar"></button>
                        </div>
                        <div class="modal-body">
                            ¿Está seguro de que quiere cerrar el producto?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
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
                                <form:hidden path="status" value="<%=closed%>"/>
                                <input class="btn btn-warning" type="submit" value="Cerrar">
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
                <h2>Este producto no ha recibido todavía ninguna puja</h2>
                <h2>Precio de puja mínima: ${productModel.price}
                </h2>
            </div>
            <%
            } else {
                ProductBidDTO highestBid = (ProductBidDTO) highestBidParameter;
                minBid = highestBid.getAmount();
            %>
            <div class="row">
                <h2>Puja más alta actual: <%=highestBid.getAmount()%>
                </h2>
            </div>
            <%
                }
            %>

            <div class="row align-items-center p-2">
                <form method="post" action="${pageContext.request.contextPath}/users/bids/new">
                    <div class="col-auto w-25">
                        <input type="number" min="<%=minBid%>" step="0.01" name="<%=UsersKeys.BID_AMOUNT_PARAMETER%>"
                               class="form-control" placeholder="Cantidad a pujar..." required>
                    </div>
                    <div class="col-auto">
                        <input type='hidden' name="<%=UsersKeys.BID_PRODUCT_ID_PARAMETER%>" id='id-compra'
                               value="${productModel.productId}"/>
                        <input class="btn btn-primary" type="submit" value="Pujar"/>
                    </div>
                </form>
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
                        <span><i class="bi bi-star-fill"></i></span>Eliminar de favoritos
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
                    <span><i class="bi bi-star-fill"></i></span>Añadir a favoritos
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