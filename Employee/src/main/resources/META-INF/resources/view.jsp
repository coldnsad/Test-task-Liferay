<%@ include file="/init.jsp" %>

<portlet:renderURL var="employeeCreateRender">
    <portlet:param name="mvcRenderCommandName" value="/employee/create"/>
</portlet:renderURL>

<liferay-ui:search-container
    emptyResultsMessage="there-are-no-employees-yet"
    delta="2" total="${totalEmployees}"
    deltaConfigurable="false">

    <liferay-ui:search-container-results results="${employees}" />
    <liferay-ui:search-container-row
        className="com.db.model.Employee"
        keyProperty="id"
        modelVar="entry"
        escapedModel="<%=true%>">

        <liferay-ui:search-container-column-text property="lastname"  />
        <liferay-ui:search-container-column-text property="firstname" />
        <liferay-ui:search-container-column-text property="patronymic" />
        <liferay-ui:search-container-column-text name="birthdate" value="<%= entry.formatDate() %>" />
        <liferay-ui:search-container-column-text property="gender" />
        <liferay-ui:search-container-column-text name="Position" value="<%= entry.showPosition() %>"/>
    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator />
</liferay-ui:search-container>

<a href="${employeeCreateRender}">Create employee</a>
