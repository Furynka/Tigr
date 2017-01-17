<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../init.jspf" %>

<spring:message var="disabledDeleteMessage" code="tigr-message-species-disabled-delete"/>

<tigr:crud-template nav="species">
<jsp:attribute name="content">


    <a href="${contextPath}/species/create" class="btn btn-success btn-new">
        <spring:message code="tigr-message-crud-new"/>
    </a>

    <table class="table table-bordered">
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
                            <c:out value="${animal.name}"/>&nbsp;
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
                    <div class="btn-group" role="group">
                        <a class="btn btn-default" href="${contextPath}/species/edit/${species.id}">
                            <spring:message code="tigr-message-crud-edit"/>
                        </a>

                        <c:if test="${empty species.animals}">
                            <a class="btn btn-danger" href="${contextPath}/species/delete/${species.id}">
                                <spring:message code="tigr-message-crud-delete"/>
                            </a>
                        </c:if>
                        <c:if test="${!empty species.animals}">
                            <a class="btn btn-danger" disabled="true" title="${disabledDeleteMessage}">
                                <spring:message code="tigr-message-crud-delete"/>
                            </a>
                        </c:if>
                    </div>
                </td>
            </tr>
    </c:forEach>
    </table>

</jsp:attribute>
</tigr:crud-template>

