<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../init.jspf" %>

<tigr:crud-template nav="species">
<jsp:attribute name="content">


    <button link="${contextPath}/species/create" class="new-button tigr-button">
        <spring:message code="tigr-message-crud-new"/>
    </button>

    <table class="table table-hover">
        <tr>
            <th><spring:message code="tigr-message-species-name"/></th>
            <th><spring:message code="tigr-message-species-description"/></th>
            <th><spring:message code="tigr-message-species-animals"/></th>
            <th><spring:message code="tigr-message-species-in-danger"/></th>
            <th><spring:message code="tigr-message-species-actions"/></th>
        </tr>
        <c:forEach items="${speciesList}" var="species">
            <tr>
                <td><c:out value="${species.name}"/></td>
                <td><c:out value="${species.description}"/></td>
                <td>
                        <%--animals--%>
                    <c:if test="${empty species.animals}">
                        <spring:message code="tigr-message-species-no-animal"/>
                    </c:if>
                    <c:if test="${not empty species.animals}">
                        <c:forEach items="${species.animals}" var="animal">
                            <c:out value="${animal}"/>
                        </c:forEach>
                    </c:if>
                </td>
                <td>
                        <%--inDanger--%>
                    <c:set var="dangerCode" value="tigr-message-no"/>
                    <c:if test="${species.inDanger}">
                        <c:set var="dangerCode" value="tigr-message-yes"/>
                    </c:if>
                    <spring:message code="${dangerCode}"/>
                </td>
                <td>
                    <spring:message var="confirmMessage"
                                    code="tigr-message-del-confirm-species"
                                    arguments="${species.name}"/>

                    <button class="edit-button tigr-button"
                            link="${contextPath}/species/edit/${species.id}">
                        <spring:message code="tigr-message-crud-edit"/>
                    </button>
                    <button class="del-button"
                            link="${contextPath}/species/delete/${species.id}"
                            confirmMessage="${confirmMessage}">
                        <spring:message code="tigr-message-crud-delete"/>
                    </button>
                </td>
            </tr>
    </c:forEach>
    </table>

</jsp:attribute>
</tigr:crud-template>

