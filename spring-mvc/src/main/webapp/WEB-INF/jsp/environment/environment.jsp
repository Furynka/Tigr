<%--
    Author: Eva Ambrušová
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../init.jspf" %>

<tigr:crud-template nav="environments">
<jsp:attribute name="content">
    NEW
    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Animals</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${environmentList}" var="environment">
            <tr>
                <td><c:out value="${environment.id}"/></td>
                <td><c:out value="${environment.name}"/></td>
                <td><c:out value="${environment.description}"/></td>
                <td><c:out value="${environment.animals}"/></td>
                <td>
                    EDIT DELETE
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</tigr:crud-template>
