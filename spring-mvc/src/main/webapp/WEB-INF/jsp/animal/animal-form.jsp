<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../init.jspf" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<tigr:crud-template nav="animals">
<jsp:attribute name="content">
    <form:form modelAttribute="data" method="post" action="${continueLink}" cssClass="animal-form">

        <div class="form-group">
            <label for="name"> <b><spring:message code="tigr-message-species-name"/></b></label>
            <form:input path="name" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="description"><spring:message code="tigr-message-species-description"/></label>
            <form:input path="description" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="count"><spring:message code="tigr-message-animal-count"/></label>
            <form:input path="count" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="species"><spring:message code="tigr-message-animal-species"/></label>
            <form:select path="species" items="${speciesList}" />
        </div>


        <div class="btn-group" role="group">
            <a class="btn btn-default" href="/pa165/animals">
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

<spring:message var="errorNameRequired" code="tigr-message-species-error-name-required"/>
<spring:message var="errorDescRequired" code="tigr-message-species-error-desc"/>
<spring:message var="errorNumberRequired" code="tigr-message-species-error-desc"/>
<script>
    $().ready(function () {
        $(".animal-form").validate({
            rules: {
                name: {
                    required: true,
                },
                description: {
                    required: true
                },
                count: {
                    required: true,
                    number: true,
                    min: 0
                }
            },
            messages: {
                name: {
                    required: "${errorNameRequired}",
                },
                description: {
                    required: "${errorDescRequired}"
                },
                count: {
                    required: "${errorNumberRequired}"
                }
            }
        });
    });
</script>