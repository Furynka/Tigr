<%-- 
    Author     : Jiri Oliva
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../init.jspf" %>

<tigr:crud-template nav="animals">
<jsp:attribute name="content">
    <a href="${contextPath}/animals/create" class="btn btn-success btn-new">
        <spring:message code="tigr-message-crud-new"/>
    </a>
    
    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Species</th>
            <th>Description</th>
            <th>Environments</th>
            <th>Count</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${animalList}" var="animal">
            <tr>
                <td><c:out value="${animal.id}"/></td>
                <td><c:out value="${animal.name}"/></td>
                <td>
                    <c:out value="${animal.species.name}"/>
                </td>
                <td><c:out value="${animal.description}"/></td>
                <td>
                    <c:if test="${not empty animal.environments}">
                        <c:forEach items="${animal.environments}" var="environment">
                            <c:out value="${environment.name}"/>&nbsp;
                        </c:forEach>
                    </c:if>
                </td>
                <td><c:out value="${animal.count}"/></td>
                <td>
                    <spring:message var="confirmMessage"
                                    code="tigr-message-del-confirm-species"
                                    arguments="${animal.name}"/>
                    <div class="btn-group" role="group">
                        <a class="btn btn-default" href="${contextPath}/animals/edit/${animal.id}">
                            <spring:message code="tigr-message-crud-edit"/>
                        </a>
                        <c:if test="${not empty worker && worker.administrator}">
                            <a class="btn btn-danger" href="${contextPath}/animals/delete/${animal.id}">
                                <spring:message code="tigr-message-crud-delete"/>
                            </a>
                        </c:if>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</tigr:crud-template>
