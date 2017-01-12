<%--
  Created by IntelliJ IDEA.
  User: khudiakov
  Date: 15.12.2016
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tigr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tigr:basetemplate
        nav="workers"
        headHeader="Manage your workers"
        headDescription="To change this description edit tag call"
        tabHeader="Workers">
<jsp:attribute name="content">
    <div class="panel panel-default">
        <!-- Table -->
        <table class="table">
            <thead>
            <tr>
                <th data-field="id">ID</th>
                <th data-field="email">Email</th>
                <th data-field="administrator">Administrator</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${workers}" var="srcWorker">
                <tr>
                    <td><c:out value="${srcWorker.id}"/></td>
                    <td><a href="mailto:<c:out value="${srcWorker.email}"/>"><c:out value="${srcWorker.email}"/></a></td>
                    <td><c:out value="${srcWorker.administrator?'yes':'no'}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</jsp:attribute>
</tigr:basetemplate>