<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="init.jspf" %>

<tigr:base-template nav="home">
<jsp:attribute name="content">
    <div style="display: flex; flex:1; flex-direction: row;">
        <div style="flex: 3">
            <h1 style="font-size: 7em"><spring:message code="tigr-message-page-menu-tab-home"/></h1>
            <h2><spring:message code="tigr-message-page-head-description-home"/></h2>
        </div>
        <div style="flex: 2;">
            <h2 style="text-align: center">Top of food chain</h2>
            <table class="table table-bordered">
                <tr><th>Name</th><th>Preys</th></tr>
                <c:forEach items="${topOfFoodChain}" var="animal">
                    <tr>
                        <td><c:out value="${animal.name}"/></td>
                        <td><c:out value="${animal.preys.size()}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</jsp:attribute>
</tigr:base-template>
