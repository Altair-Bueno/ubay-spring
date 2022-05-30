<%@ page import="uma.taw.ubayspring.dto.users.ClientDTO" %>
<%@ page import="uma.taw.ubayspring.types.GenderEnum" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 20/5/22
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
<%--
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous">
    </script>
    --%>

    <title>Ubay | <spring:message key="navbar.navlinktext.users"/></title>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>
<div>
    <div class="container">
        <a class="btn btn-primary"
           href="/auth/register"
           role="button"><spring:message key="users.registernewuser"/></a>
        <div class="row">
            <div class="col-3">
                <%--@elvariable id="filterUsersDTO" type="uma.taw.ubayspring.dto.users.FilterUsersDTO"--%>
                <form:form modelAttribute="filterUsersDTO" method="get">
                    <div class="form col">
                        ID: <form:input path="id" type="number" class="form-control" id="id"
                                    maxlength="5"/>
                        <spring:message key="name"/>: <form:input type="text" path="name" class="form-control"
                                       id="name"  maxlength="10"/>
                        <spring:message key="register.form.last_name"/>: <form:input type="text" path="lastName" class="form-control"
                                          id="lastName"
                                          maxlength="10"/>
                        <spring:message key="register.form.address"/>: <form:input type="text" path="address" class="form-control"
                                          id="address"
                                          maxlength="15"/>
                        <spring:message key="register.form.city"/>: <form:input type="text" path="city" class="form-control"
                                       id="city"
                                       aria-describedby="city" maxlength="10"/>
                        <spring:message key="register.form.gender"/>: <form:select class="form-select" path="gender" id="gender">
                                    <form:option value=""/>
                                    <form:options items="${GenderEnum.values()}"/>
                                </form:select>
                        <button type="submit" class="btn btn-primary mt-2">
                            <spring:message key="filter"/>
                        </button>
                        <a class="btn btn-secondary mt-2" href="<%=request.getContextPath()%>/users"><spring:message key="clean"/></a>
                    </div>
                </form:form>
            </div>

            <div class="col">
                <table class="table table-responsive" id="userDataTable">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th><spring:message key="name"/></th>
                        <th><spring:message key="register.form.last_name"/></th>
                        <th><spring:message key="register.form.gender"/></th>
                        <th><spring:message key="register.form.address"/></th>
                        <th><spring:message key="register.form.city"/></th>
                        <th><spring:message key="register.form.birth_date"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<ClientDTO> searchClient = (List<ClientDTO>) request.getAttribute("search-user");

                        if (searchClient != null) {
                            for (ClientDTO c : searchClient) {

                    %>
                    <tr>
                        <td><%=c.getId()%>
                        </td>
                        <td><%=c.getName()%>
                        </td>
                        <td><%=c.getLastName()%>
                        </td>
                        <td><%=c.getGender()%>
                        </td>
                        <td><%=c.getAddress()%>
                        </td>
                        <td><%=c.getCity()%>
                        </td>
                        <td><%=c.getBirthDate()%>
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary"
                                    data-bs-toggle="modal"
                                    data-bs-target="#deleteModal<%=c.getId()%>"><spring:message key="product.item.delete.label"/>
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
                                                id="staticBackdropLabel"><spring:message key="product.item.delete.label"/></h5>
                                            <button type="button" class="btn-close"
                                                    data-bs-dismiss="modal"
                                                    aria-label="Cerrar"></button>
                                        </div>
                                        <div class="modal-body">
                                            <spring:message key="users.delete.text"/> <%=c.getId()%> ?
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                    data-bs-dismiss="modal"><spring:message key="cancel"/>
                                            </button>
                                            <form method="GET"
                                                  action="/users/delete">
                                                <input hidden name='id' value="<%=c.getId()%>"/>
                                                <button class="btn btn-danger" type="submit"
                                                       ><spring:message key="product.item.delete.label"/></button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </td>

                        <td>
                            <a href="/users/modify?id=<%=c.getId()%>&name=<%=c.getName()%>&lastName=<%=c.getLastName()%>&gender=<%=c.getGender()%>&address=<%=c.getAddress()%>&city=<%=c.getCity()%>&birthDate=<%=c.getBirthDate()%>">
                                <spring:message key="modify"/></a></td>
                        <td><a href="/users/passwordChangeLink?id=<%=c.getId()%>"><spring:message key="resetPassword"/></a></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
