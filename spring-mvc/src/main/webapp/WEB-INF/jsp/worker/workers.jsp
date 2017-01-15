<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../init.jspf" %>

<tigr:crud-template nav="workers">
<jsp:attribute name="content">
    <div class="panel panel-default">
        <!-- Table -->
        <table class="table">
            <thead>
            <tr>
                <th data-field="id">ID</th>
                <th data-field="email">Email</th>
                <th data-field="administrator">Administrator</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${workers}" var="srcWorker">
                <tr>
                    <td><c:out value="${srcWorker.id}"/></td>
                    <td><a href="mailto:<c:out value="${srcWorker.email}"/>"><c:out value="${srcWorker.email}"/></a></td>
                    <td>
                        <input type="checkbox" onchange="changeRole(this)" name="<c:out value="${srcWorker.email}"/>" <c:if test="${srcWorker.administrator}">checked</c:if>/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <script>
        function changeRole(target) {
            $.ajax({
                type: "POST",
                url: "/pa165/workers/changeRole",
                data: {
                    email: target.getAttribute("name"),
                    admin: target.checked
                },
                error: function () {
                    showMessage("Error!", "danger");
                },
                success: function () {
                    showMessage("Success!", "success");
                }
            })
        }
    </script>
</jsp:attribute>
</tigr:crud-template>