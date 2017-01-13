<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../init.jspf" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<tigr:crud-template nav="species">
<jsp:attribute name="content">
    <form:form modelAttribute="data"
               method="post"
               action="${continueLink}"
               cssClass="species-form">

        <div class="form-group">
            <label for="name"><spring:message code="tigr-message-species-name"/></label>
            <form:input path="name" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="description"><spring:message code="tigr-message-species-description"/></label>
            <form:input path="description" class="form-control"/>
        </div>

        <div class="form-group left-checkbox">
            <label><form:checkbox path="inDanger"/>
                <b><spring:message code="tigr-message-species-in-danger"/></b></label>
        </div>

        <button type="submit" class="tigr-form-button">
            <spring:message code="${buttonLabelCode}"/>
        </button>

        <form:hidden path="id"/>
    </form:form>

    <button class="js-button tigr-form-button back-button"
            link="/pa165/species">
        <spring:message code="tigr-message-back"/>
    </button>

</jsp:attribute>
</tigr:crud-template>

<spring:message var="errorNameRequired" code="tigr-message-species-error-name-required"/>
<spring:message var="errorNameLength" code="tigr-message-species-error-name-minlength"/>
<spring:message var="errorDescRequired" code="tigr-message-species-error-desc"/>
<script>
    $().ready(function () {
        $(".species-form").validate({
            rules: {
                name: {
                    required: true,
                    minlength: 5
                },
                description: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "${errorNameRequired}",
                    minlength: "${errorNameLength}"
                },
                description: {
                    required: "${errorDescRequired}"
                }
            }
        });
    });
</script>