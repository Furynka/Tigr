<%-- 
    Author     : Jiri Oliva
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tigr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<my:basetemplate nav="animals">
<jsp:attribute name="content">
    <div class="panel panel-default">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${animals}" var="entity">
                <tr>
                    <td><c:out value="${entity.name}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</jsp:attribute>
</my:basetemplate>
