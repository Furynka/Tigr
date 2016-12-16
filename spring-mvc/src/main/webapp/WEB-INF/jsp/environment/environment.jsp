<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tigr" %>

<tigr:basetemplate
        nav="environments"
        headHeader="Manage your environments"
        headDescription="In this page, you can add, delete or edit all food chain environments. Use the search bar for
    easier work!"
        tabHeader="Environment">
<jsp:attribute name="content">

            <div class="search-add">
                <span><input type="search" placeholder="search"></span>
                <button class="btn btn-primary">GO!</button>
                <button class="add btn btn-primary">+ New environment</button>
            </div>
            <div>
                <ul class="link-strips">
                    <li class="list-item" title="Environment1 TODO">Environment1 TODO
                        <span><button type="delete" class="btn btn-primary">Delete</button></span>
                    </li>
                    <li class="list-item" title="Environment2 TODO">Environment2 TODO
                        <span><button type="delete" class="btn btn-primary">Delete</button></span>
                    </li>
                    <li class="list-item" title="Environment3 TODO">Environment3 TODO
                        <span><button type="delete" class="btn btn-primary">Delete</button></span>
                    </li>
                </ul>
            </div>
            <div class="center">
                <button class="btn btn-primary" href="#">See all environments</button>
            </div>

</jsp:attribute>
</tigr:basetemplate>