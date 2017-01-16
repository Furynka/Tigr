<%--
    Author: Eva Ambrušová
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../init.jspf" %>

<tigr:crud-template nav="environments">
<jsp:attribute name="content">

    <a href="${contextPath}/environments/create" class="btn btn-success btn-new">
        <spring:message code="tigr-message-crud-new"/>
    </a>

    <div>
    <table class="table table-hover" >
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
                            <c:out value="${animal.name}"/><br />
                        </c:forEach>
                    </c:if>
                </td>

                <td>
                    <spring:message var="confirmMessage"
                                    code="tigr-message-del-confirm-species"
                                    arguments="${environment.name}"/>


                    <div class="btn-group" role="group">
                        <a class="btn btn-default" href="${contextPath}/environments/edit/${environment.id}">
                            <spring:message code="tigr-message-crud-edit"/>
                        </a>
                        <c:if test="${not empty worker && worker.administrator}">
                            <a class="btn btn-danger" href="${contextPath}/environments/delete/${environment.id}">
                                <spring:message code="tigr-message-crud-delete"/>
                            </a>
                        </c:if>
                    </div>
                </td>

        </c:forEach>

        </tr>
        </tbody>

    </table>
    <table class="table table-hover" >
        <thead>
        <th>Top 3 endangered</th>
        </thead>
        <tbody>
                    <c:forEach items="${top3animalsList}" var="animalList">
                        <tr>
                            <td>
                        <c:if test="${empty animalList}">
                            <spring:message code="tigr-message-species-no-animal"/>
                        </c:if>
                            <c:forEach items="${animalList}" var="endangeredAnimal">
                            <c:out value="${endangeredAnimal.name}"/><br />
                        </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
        </tbody>
    </table>
    </div>
</jsp:attribute>
</tigr:crud-template>

<!-- Modal Structure -->
<div class="modal fade" id="top3EndangeredModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Top 3 endangered animals in </h4><c:out value="${environment.name}"/>
            </div>
            <div class="modal-body login-modal-body">

                <form id="changePasswordForm">
                    <p>zviera</p>
                    <p>zviera</p>

                </form>

            </div>
        </div>
    </div>
</div>
