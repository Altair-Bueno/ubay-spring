<%@ page import="java.util.List" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="uma.taw.ubayspring.dto.categories.CategoryDTO" %>
<%@ page import="uma.taw.ubayspring.types.KindEnum" %>
<%@ page import="uma.taw.ubayspring.entity.LoginCredentialsEntity" %><%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 5/4/22
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
</head>
<title>Ubay | <spring:message key="navbar.navlinktext.categories"/></title>
<body>
<jsp:include page="../../components/navbar.jsp"/>
<%
    LoginCredentialsEntity login = (LoginCredentialsEntity) request.getAttribute("login");
    if (login != null && login.getKind().equals(KindEnum.admin)) {
%>
<div class="container">
    <h1><spring:message key="description"/> <a class="btn btn-primary m-2" href="/categories/add" role="button"><spring:message key="categories.addnew.label"/></a></h1>

    <div class="row">
        <div class="col">
            <table class="table table-responsive" id="categoryDataTable">
                <thead>
                <tr>
                    <th>ID</th>
                    <th><spring:message key="name"/></th>
                    <th><spring:message key="description"/></th>
                    <th><spring:message key="product.item.delete.label"/></th>
                    <th><spring:message key="modify"/></th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<CategoryDTO> catList = (List) request.getAttribute("category-list");
                    if (catList != null) {
                        for (CategoryDTO c : catList) {
                %>
                <tr>
                    <td><%=c.getId()%>
                    </td>
                    <td><%=c.getName()%>
                    </td>
                    <td><%=c.getDescription()%>
                    </td>
                    <td>
                        <button type="button" class="btn btn-primary"
                                data-bs-toggle="modal"
                                data-bs-target="#deleteModal<%=c.getId()%>"><spring:message key="category.deleteModal.header"/>
                        </button>

                        <!-- Delete Modal -->
                        <div class="modal fade" id="deleteModal<%=c.getId()%>"
                             data-bs-backdrop="static" data-bs-keyboard="false"
                             tabindex="-1"
                             aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"
                                            id="staticBackdropLabel"><spring:message key="category.deleteModal.header"/></h5>
                                        <button type="button" class="btn-close"
                                                data-bs-dismiss="modal"
                                                aria-label="Cerrar"></button>
                                    </div>
                                    <div class="modal-body">
                                        <spring:message key="category.delete.text"/> <%=c.getId()%> ?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary"
                                                data-bs-dismiss="modal"><spring:message key="cancel"/>
                                        </button>
                                        <form method="GET"
                                              action="/categories/delete">
                                            <input hidden name='id' value="<%=c.getId()%>"/>
                                            <input class="btn btn-danger" type="submit"
                                                   value="Eliminar">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </td>
                    <td><a href="/categories/modify?id=<%=c.getId()%>&name=<%=c.getName()%>&description=<%=c.getDescription()%>"><spring:message key="modify"/></a></td>
                </tr>
                <%
                        }
                    }

                %>
                </tbody>
            </table>
            <br>

        </div>
    </div>
</div>

<%
} else if (login != null && login.getKind().equals(KindEnum.client)) {
%>

<div class="container">
    <h1><spring:message key="navbar.navlinktext.categories"/></h1>
    <div class="row">
        <div class="col">
            <table class="table table-responsive">
                <thead>
                <tr>
                    <th>ID</th>
                    <th><spring:message key="name"/></th>
                    <th><spring:message key="description"/></th>
                    <% if (login != null) { %>
                    <th></th>
                    <%}%>


                </tr>
                </thead>
                <tbody>
                <%
                    List<CategoryDTO> catList = (List) request.getAttribute("category-list");
                    List<CategoryDTO> favouriteCategories = (List<CategoryDTO>) request.getAttribute("user-fav-category-list");
                    if (catList != null) {
                        for (CategoryDTO c : catList) {
                %>

                <%
                    if (favouriteCategories.contains(c)) {
                %>

                    <tr class="table-primary">
                        <td>
                            <%=c.getId()%>
                        </td>

                        <td>
                            <a href="/product?category=<%=c.getId()%>"><%=c.getName()%></a>
                        </td>

                        <td>
                            <%=c.getDescription()%>
                        </td>

                        <td>
                            <a href="/categories/deleteFavourite?categoryID=<%=c.getId()%>&clientID=<%=request.getAttribute("client-id")%>"><spring:message key="removeFavourites"/></a>
                        </td>
                    </tr>

                <%
                    } else {
                %>
                    <tr>
                        <td>
                            <%=c.getId()%>
                        </td>

                        <td>
                            <a href="/product?category=<%=c.getId()%>"><%=c.getName()%></a>
                        </td>

                        <td>
                            <%=c.getDescription()%>
                        </td>

                        <td>
                            <a href="/categories/addFavourite?categoryID=<%=c.getId()%>&clientID=<%=request.getAttribute("client-id")%>"><spring:message key="addFavourites"/></a>
                        </td>
                    </tr>
                <%
                    }
                %>

                <%
                            }
                        }
                    }

                %>
                </tbody>
            </table>
            <br>
        </div>
    </div>
</div>
</body>
</html>
