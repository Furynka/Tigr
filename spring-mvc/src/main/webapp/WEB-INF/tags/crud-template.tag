<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>

<%@include file="../jsp/init.jspf" %>

<%@ attribute name="content" fragment="true" required="true" %>
<%@ attribute name="nav" required="true" %>


<tigr:base-template nav="${nav}">
    <jsp:attribute name="content">
        <section class="environment-section page-width">
            <h2 class="section-header">
                <spring:message code="tigr-message-page-head-header-${nav}"/>
            </h2>
            <p class="section-header">
                <spring:message code="tigr-message-page-head-description-${nav}"/>
            </p>

            <div class="environment-section entity-list-panel">
                <section class="panel">
                    <h1 class="component-header">
                        <span class="underline">
                            <spring:message code="tigr-message-page-menu-tab-${nav}"/>
                        </span>
                    </h1>
                    <div class="template-body-content">
                        <jsp:invoke fragment="content"/>
                    </div>
                </section>
            </div>
        </section>
    </jsp:attribute>
</tigr:base-template>
