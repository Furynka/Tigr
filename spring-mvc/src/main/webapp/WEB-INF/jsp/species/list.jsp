<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tigr" %>

<tigr:basetemplate nav="species">
<jsp:attribute name="content">

    <table>
        <c:forEach items="${speciesList}" var="species">
            <tr>
                <td><c:out value="${species.id}"/></td>
                <td><c:out value="${species.name}"/></td>
                <td><c:out value="${species.description}"/></td>
                <td><c:out value="${species.animals}"/></td>
                <td><c:out value="${species.inDanger}"/></td>
            </tr>
    </c:forEach>
    </table>

</jsp:attribute>
</tigr:basetemplate>