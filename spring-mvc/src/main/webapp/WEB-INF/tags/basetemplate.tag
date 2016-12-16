<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="content" fragment="true" required="true" %>
<%@ attribute name="nav" required="true" %>

<%@ attribute name="headHeader" fragment="false" required="false" %>
<%@ attribute name="headDescription" fragment="false" required="false" %>
<%@ attribute name="tabHeader" fragment="false" required="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">

    <script src="${contextPath}/js/jquery-2.1.4.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="${contextPath}/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/styles.css">

    <title>Tigr</title>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Tigr</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="<c:if test="${nav == 'animals'}">active</c:if>"><a href="${contextPath}/animals">Animals</a>
                </li>
                <li class="<c:if test="${nav == 'species'}">active</c:if>"><a href="${contextPath}/species">Species</a></li>
                <li class="<c:if test="${nav == 'environments'}">active</c:if>"><a href="#">Environments</a></li>
                <c:if test="${not empty worker && worker.administrator}">
                    <li class="<c:if test="${nav == 'workers'}">active</c:if>"><a href="${contextPath}/workers/">Workers</a>
                </c:if>
                </li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                    <c:if test="${not empty worker}">
                        <li>
                            <p class="navbar-text">
                                Hello <c:out value="${worker.email}" />!
                            </p>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/workers/logout">
                                Logout
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${empty worker}">
                        <li>
                            <a href="#" data-toggle="modal" data-target="#myModal">
                                Login
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
                    <section class="environment-section page-width">
                        <h2 class="section-header">${headHeader}</h2>
                        <p class="section-header">${headDescription}</p>

                        <div class="environment-section entity-list-panel">
                            <section class="panel">
                                <h1 class="component-header">
                                    <span class="underline">${tabHeader}</span>
                                </h1>
                                <div class="template-body-content">
                                    <jsp:invoke fragment="content"/>
                                </div>
                            </section>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal Structure -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <img src="${contextPath}/img/user-grey.png"/>
                <form method="post" action="${contextPath}/workers/login">
                    <div class="form-group">
                        <input type="text" class="form-control" id="email" name="email" placeholder="Login">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>