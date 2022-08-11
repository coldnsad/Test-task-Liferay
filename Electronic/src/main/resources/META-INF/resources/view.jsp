<%@ include file="/init.jsp" %>

<portlet:renderURL var="electronicCreateRender">
    <portlet:param name="mvcRenderCommandName" value="/electronic/create"/>
</portlet:renderURL>

<portlet:actionURL name="importFromCsv" var="importFromCsvURL">
</portlet:actionURL>

<h1> List of electronics </h1>
<liferay-ui:search-container
    emptyResultsMessage="there-are-no-electronics-yet"
    delta="2" total="${totalElectronics}"
    deltaConfigurable="false">

    <liferay-ui:search-container-results results="${electronics}" />
    <liferay-ui:search-container-row
        className="com.db.model.Electronic"
        keyProperty="id"
        modelVar="entry"
        escapedModel="<%=true%>">

        <liferay-ui:search-container-column-text property="name"  />
        <liferay-ui:search-container-column-text name="Electronic type" value="<%= entry.showElectronicType() %>"/>
        <liferay-ui:search-container-column-text name="Price" value="<%= entry.showPriceInRubles() %>" />
        <liferay-ui:search-container-column-text property="count" />
        <liferay-ui:search-container-column-text name="instock" />
        <liferay-ui:search-container-column-text property="archive" />
        <liferay-ui:search-container-column-text property="description" />
        <liferay-ui:search-container-column-text name="Action">
            <portlet:renderURL var="employeeEditRender">
                <portlet:param name="mvcRenderCommandName" value="/electronic/create"/>
                <portlet:param name="electronicId" value="${entry.id}"/>
            </portlet:renderURL>
            <a href="${employeeEditRender}">Edit</a>
        </liferay-ui:search-container-column-text>
    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator />
</liferay-ui:search-container>

<a href="${electronicCreateRender}" cssClass="btn bnt-primary">Create electronic</a>
<hr>

<h1>Upload Electronic Data CSV </h1>
<aui:form action="${importFromCsvURL}" enctype="multipart/form-data" method="post" id="csvDataFileForm">

    <aui:input type="file" name="fileupload" label="CSV or ZIP file required" id="csvDataFile"></aui:input>
    <aui:button-row>
            <aui:button cssClass="btn bnt-primary" type="submit" label="import"/>
     </aui:button-row>

</aui:form>

