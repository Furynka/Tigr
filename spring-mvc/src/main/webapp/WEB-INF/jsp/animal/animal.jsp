<%-- 
    Author     : Jiri Oliva
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../init.jspf" %>

<tigr:crud-template nav="animals">
<jsp:attribute name="content">
    NEW
    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Species</th>
            <th>Description</th>
            <th>Environments</th>
            <th>Count</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${animalList}" var="animal">
            <tr>
                <td><c:out value="${animal.id}"/></td>
                <td><c:out value="${animal.name}"/></td>
                <td><c:out value="${animal.species}"/></td>
                <td><c:out value="${animal.description}"/></td>
                <td><c:out value="${animal.environments}"/></td>
                <td><c:out value="${animal.count}"/></td>
                <td>
                    EDIT DELETE
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</tigr:crud-template>
