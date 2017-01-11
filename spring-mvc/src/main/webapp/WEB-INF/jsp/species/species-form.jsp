<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tigr" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<tigr:basetemplate
        nav="species"
        headHeader="Manage your species"
        headDescription="In this page, you can add, delete or edit all food chain species. Use
                            the search bar for easier work!"
        tabHeader="Species">
<jsp:attribute name="content">
    <form:form modelAttribute="data" method="post" action="${continueLink}">
        <label>Jméno<form:input path="name"/></label><br>
        <label>Popis<form:input path="description"/></label><br>
        <label>V ohrožení<form:checkbox path="inDanger"/></label><br>
        <button type="submit">${buttonLabel}</button>
    </form:form>


</jsp:attribute>
</tigr:basetemplate>