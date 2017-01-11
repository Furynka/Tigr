<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tigr" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<tigr:basetemplate
        nav="species"
        headHeader="Manage your species"
        headDescription="In this page, you can add, delete or edit all food chain species. Use
                            the search bar for easier work!"
        tabHeader="Species">
<jsp:attribute name="content">


    <a href="${contextPath}/species/create">NEW</a>

    <table class="table table-hover">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Animals</th>
            <th>inDanger</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${speciesList}" var="species">
            <tr>
                <td><c:out value="${species.name}"/></td>
                <td><c:out value="${species.description}"/></td>
                <td><c:out value="${species.animals}"/></td>
                <td><c:out value="${species.inDanger}"/></td>
                <td>
                    <a href="${contextPath}/species/edit/${species.id}">EDIT</a>
                    <a href="${contextPath}/species/delete/${species.id}">DELETE</a>
                </td>
            </tr>
    </c:forEach>
    </table>

</jsp:attribute>
</tigr:basetemplate>