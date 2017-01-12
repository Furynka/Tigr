<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../init.jspf" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<tigr:crud-template nav="species">
<jsp:attribute name="content">
    <form:form modelAttribute="data" method="post" action="${continueLink}">

        <label><spring:message code="tigr-message-species-name"/><form:input path="name"/></label><br>
        <label><spring:message code="tigr-message-species-description"/><form:input path="description"/></label><br>
        <label><spring:message code="tigr-message-species-in-danger"/><form:checkbox path="inDanger"/></label><br>

        <button type="submit"><spring:message code="${buttonLabelCode}"/></button>

        <form:hidden path="id"/>
    </form:form>

    <button class="tigr-button"
            link="/pa165/species">
        <spring:message code="tigr-message-back"/>
    </button>


</jsp:attribute>
</tigr:crud-template>