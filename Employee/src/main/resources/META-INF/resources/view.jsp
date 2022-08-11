<%@ include file="/init.jsp" %>

<portlet:renderURL var="employeeCreateRender">
    <portlet:param name="mvcRenderCommandName" value="/employee/create"/>
</portlet:renderURL>

<portlet:actionURL name="importFromCsv" var="importFromCsvURL">
</portlet:actionURL>

<h1> List of employees </h1>
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
        <liferay-ui:search-container-column-text name="Action">
            <portlet:renderURL var="employeeEditRender">
                <portlet:param name="mvcRenderCommandName" value="/employee/create"/>
                <portlet:param name="employeeId" value="${entry.id}"/>
            </portlet:renderURL>
            <a href="${employeeEditRender}">Edit</a>
        </liferay-ui:search-container-column-text>
    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator />
</liferay-ui:search-container>

<a href="${employeeCreateRender}" cssClass="btn bnt-primary">Create employee</a>
<hr>

<h1>Upload Employee Data CSV </h1>
<aui:form action="${importFromCsvURL}" enctype="multipart/form-data" method="post" id="csvDataFileForm">

    <aui:input type="file" name="fileupload" label="CSV or ZIP file required" id="csvDataFile"></aui:input>
    <aui:button-row>
            <aui:button cssClass="btn bnt-primary" type="submit" label="import"/>
     </aui:button-row>

</aui:form>

