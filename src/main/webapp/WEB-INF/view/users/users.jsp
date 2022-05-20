<%@ page import="uma.taw.ubayspring.dto.users.ClientDTO" %>
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
    <title>Ubay | Usuarios</title>
</head>
<body>
<%-- <%@include file="../WEB-INF/components/navbar.jsp" %>--%>
<div>
    <div class="container">
        <h1>Buscar usuarios</h1>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/auth/register" role="button">Registrar
            a un usuario nuevo</a>
        <div class="row">
            <div class="col-3">
                <form>
                    <div class="form col">
                        ID: <input type="number" class="form-control" id="id" name="id" maxlength="5">
                        Nombre: <input type="text" class="form-control" id="name" name="name" maxlength="10">
                        Apellidos: <input type="text" class="form-control" id="lastName" name="lastName" maxlength="10">
                        Dirección: <input type="text" class="form-control" id="address" name="address" maxlength="15">
                        Ciudad: <input type="text" class="form-control" id="city" name="city" aria-describedby="city" maxlength="10">
                        Género: <select class="form-select" id="gender" name="gender">
                        <option selected value="--">--</option>
                        <option value="male">Masculino</option>
                        <option value="female">Femenino</option>
                        <option value="other">Otro</option>
                    </select>
                        <button type="submit" class="btn btn-primary mt-2">Buscar</button>
                    </div>
                </form>
            </div>

            <div class="col">
                <table class="table table-responsive" id="userDataTable">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Género</th>
                        <th>Dirección</th>
                        <th>Ciudad</th>
                        <th>Fecha de Nacimiento</th>
                        <th>Eliminar usuario</th>
                        <th>Modificar user</th>
                        <th>Reestablecer contraseña</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<ClientDTO> searchClient = (List<ClientDTO>)request.getAttribute("search-user");

                        if(searchClient != null){
                            for(ClientDTO c : searchClient){

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
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                               data-bs-target="#deleteModal">Eliminar usuario</button>
                        </td>

                        <td>
                            <a href="modify?id=<%=c.getId()%>&name=<%=c.getName()%>&lastName=<%=c.getLastName()%>&gender=<%=c.getGender()%>&address=<%=c.getAddress()%>&city=<%=c.getCity()%>&birthDate=<%=c.getBirthDate()%>">Modificar
                                usuario</a></td>
                        <td><a href="passwordChangeLink?id=<%=c.getId()%>">Reestablecer contraseña</a></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>

                <!-- Delete Modal -->
                <div class="modal fade" id="deleteModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">Eliminar usuario</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Cerrar"></button>
                            </div>
                            <div class="modal-body">
                                ¿Está seguro de que quiere eliminar el usuario?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <form method="post" action="${pageContext.request.contextPath}/users/delete">
                                    <input type='hidden' name='id' value="10"/>
                                    <input class="btn btn-danger" type="submit" value="Eliminar">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>