<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>

<%@include file="../jsp/init.jspf" %>

<%@ attribute name="content" fragment="true" required="true" %>
<%@ attribute name="nav" required="true" %>


<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">

    <script src="${contextPath}/js/jquery-2.1.4.min.js"></script>
    <%--todo download libraries--%>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.js"></script>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.8/validator.min.js"></script>--%>
    <script src="${contextPath}/js/bootstrap.min.js"></script>
    <%--<script src="${contextPath}/js/tigr-scripts.js"></script>--%>

    <link rel="stylesheet" type="text/css" href="${contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/styles_v2.0.css"/>
    <%--<link rel="stylesheet" type="text/css" href="${contextPath}/css/styles.css">--%>

    <title><spring:message code="tigr-message-page-title"/></title>
</head>
<body>

<%--Navigation--%>
<%@include file="../jsp/components/nav.jspf" %>


<div class="container">
    <%--Created by Eva Ambrusova--%>
    <div class="container-fluid">
        <div class="row">
            <div>
                <div class="content">
                    <jsp:invoke fragment="content"/>
                </div>
            </div>
        </div>
    </div>
</div>

<%--locale change--%>
<%
    request.setAttribute("locales", new String[]{"en", "cs", "zh"});
%>
<div class="footer">
    <c:forEach items="${locales}" var="loc">
        <a href="?locale=${loc}"><spring:message code="locale-${loc}"/></a> |
    </c:forEach>
</div>

<%--login modal window--%>
<%@include file="../jsp/components/modal-login.jspf" %>

</body>
</html>