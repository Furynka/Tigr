<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>

<%@include file="../jsp/init.jspf" %>

<%@ attribute name="content" fragment="true" required="true" %>
<%@ attribute name="nav" required="true" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">

    <script src="${contextPath}/js/jquery-2.1.4.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.8/validator.min.js"></script>
    <script src="${contextPath}/js/bootstrap.min.js"></script>
    <script src="${contextPath}/js/tigr-scripts.js"></script>

    <link rel="stylesheet" type="text/css" href="${contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/styles.css">

    <title>Tigr</title>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                <spring:message code="tigr-message-page-menu-tab-home"/>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav">
                <li class="<c:if test="${nav == 'animals'}">active</c:if>">
                    <a href="${contextPath}/animals">
                        <spring:message code="tigr-message-page-menu-tab-animals"/>
                    </a>
                </li>
                <li class="<c:if test="${nav == 'species'}">active</c:if>">
                    <a href="${contextPath}/species">
                        <spring:message code="tigr-message-page-menu-tab-species"/>
                    </a>
                </li>
                <li class="<c:if test="${nav == 'environments'}">active</c:if>">
                    <a href="${contextPath}/environments">
                        <spring:message code="tigr-message-page-menu-tab-environments"/>
                    </a>
                </li>
                <c:if test="${not empty worker && worker.administrator}">
                    <li class="<c:if test="${nav == 'workers'}">active</c:if>">
                        <a href="${contextPath}/workers/">
                            <spring:message code="tigr-message-page-menu-tab-workers"/>
                        </a>
                    </li>
                </c:if>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${not empty worker}">
                    <li>
                        <p class="navbar-text">
                            Hello <c:out value="${worker.email}"/>!
                        </p>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/workers/logout">
                            <spring:message code="tigr-message-page-logout"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${empty worker}">
                    <li>
                        <a href="#" data-toggle="modal" data-target="#myModal">
                            <spring:message code="tigr-message-page-login"/>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>


<div class="container">
    <!-- Created by Eva Ambrusova -->
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

<%-- locale change --%>
<%
    request.setAttribute("locales", new String[]{"en", "cs", "zh"});
%>
<div class="footer">
    <c:forEach items="${locales}" var="loc">
        <a href="?locale=${loc}"><spring:message code="locale-${loc}"/></a> |
    </c:forEach>
</div>


<!-- Modal Structure -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form method="post" action="${contextPath}/workers/login" data-toggle="validator">
                    <div class="form-group">
                        <input type="email" class="form-control" id="email" name="email" value="admin@test.com"
                               placeholder="Login" required>
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <input data-minlength="6" type="password" class="form-control" id="password" name="password"
                               value="password" placeholder="Password" required>
                        <div class="help-block with-errors"></div>
                    </div>
                    <button type="submit" class="btn btn-primary"><spring:message
                            code="tigr-message-page-login"/></button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>