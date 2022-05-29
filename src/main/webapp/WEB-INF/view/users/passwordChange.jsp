<%@ page import="java.net.URLEncoder" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.nio.charset.StandardCharsets" %><%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 22/5/22
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title><spring:message key="changePassword.title"/></title>
</head>
<body>
<%
    String passwordChangeID = (String) request.getAttribute("passwordChangeID");
    String usernamepwc = (String) request.getAttribute("username");
%>
<jsp:include page="../../components/navbar.jsp"/>

<div class="col-6 position-absolute top-50 start-50 translate-middle">
    <h1></h1>
    <input type="text" size="80" id="linkReseteo" value="${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/auth/resetPassword?passwordChangeID=<%=URLEncoder.encode(passwordChangeID,StandardCharsets.UTF_8)%>&username=<%=URLEncoder.encode(usernamepwc,StandardCharsets.UTF_8)%>"/>
    <button class="btn btn-primary mt-2" onclick="copyToClipboard()"><spring:message key="users.passwordchangelink.copytoclipboard.label"/></button>
</div>

<%--
    José Luis Bueno: Function extracted from: https://www.w3schools.com/howto/howto_js_copy_clipboard.asp
--%>
<script>
    function copyToClipboard() {
        /* Get the text field */
        var copyText = document.getElementById("linkReseteo");

        /* Select the text field */
        copyText.select();
        copyText.setSelectionRange(0, 99999); /* For mobile devices */

        /* Copy the text inside the text field */
        navigator.clipboard.writeText(copyText.value);
    }
</script>
</body>
</html>

