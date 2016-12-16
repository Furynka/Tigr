<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="content" fragment="true" required="true" %>
<%@ attribute name="nav" required="true" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <spring:url value="/resources/img/logo.png" var="logoImg" />
    <link rel="stylesheet" href="${contextPath}/css/bootstrap.min.css"/>
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
                <li class="<c:if test="${nav == 'animals'}">active</c:if>"><a href="#">Animals</a></li>
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
    <jsp:invoke fragment="content"/>
</div>

<!-- Modal Structure -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <form method="post" action="${pageContext.request.contextPath}/workers/login">
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

<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>