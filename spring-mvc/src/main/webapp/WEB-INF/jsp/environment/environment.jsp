<%--
    Author: Eva Ambrušová
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../init.jspf" %>

<tigr:crud-template nav="environments">
<jsp:attribute name="content">
        <button link="${contextPath}/environments/create" class="new-button js-button">
            <spring:message code="tigr-message-crud-new"/>
        </button>

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
                <td>
                    <c:if test="${empty environment.animals}">
                        <spring:message code="tigr-message-species-no-animal"/>
                    </c:if>
                    <c:if test="${not empty environment.animals}">
                        <c:forEach items="${environment.animals}" var="animal">
                            <c:out value="${animal}"/>
                        </c:forEach>
                    </c:if>
                </td>
                <td>
                    <spring:message var="confirmMessage"
                                    code="tigr-message-del-confirm-species"
                                    arguments="${environment.name}"/>
                    EDIT

                    <button class="del-button"
                            link="${contextPath}/environments/delete/${environment.id}"
                            confirmMessage="${confirmMessage}">
                        <spring:message code="tigr-message-crud-delete"/>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</tigr:crud-template>
