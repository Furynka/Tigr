<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../init.jspf" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<tigr:crud-template nav="environments">
<jsp:attribute name="content">
    <form:form modelAttribute="data" method="post" action="${continueLink}">

        <div class="form-group">
            <label for="name"> <b><spring:message
                    code="tigr-message-species-name"/></b></label>
            <form:input path="name" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="description"><spring:message code="tigr-message-species-description"/></label>
            <form:input path="description" class="form-control"/>
        </div>


        <div class="btn-group" role="group">
            <a class="btn btn-default" href="/pa165/environments">
                <spring:message code="tigr-message-back"/>
            </a>
            <button type="submit" class="btn btn-success">
                <spring:message code="${buttonLabelCode}"/>
            </button>
        </div>
        <form:hidden path="id"/>
    </form:form>

</jsp:attribute>
</tigr:crud-template>