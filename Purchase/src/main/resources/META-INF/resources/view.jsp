<%@ include file="/init.jsp" %>

<portlet:renderURL var="purchaseCreateRender">
    <portlet:param name="mvcRenderCommandName" value="/purchase/create"/>
</portlet:renderURL>

<portlet:actionURL name="importFromCsv" var="importFromCsvURL">
</portlet:actionURL>

<h1> List of Purchases </h1>
<liferay-ui:search-container
    emptyResultsMessage="there-are-no-purchase-yet"
    total="${totalPurchases}"
    deltaConfigurable="false">

    <liferay-ui:search-container-results results="${purchases}" />
    <liferay-ui:search-container-row
        className="com.db.model.Purchase"
        keyProperty="id"
        modelVar="entry"
        escapedModel="<%=true%>">

        <liferay-ui:search-container-column-text name="Electronic name" value="<%= entry.showElectronic() %>"  />
        <liferay-ui:search-container-column-text
            name="Employee"
            value="<%= entry.showEmployee() %>"
            />
        <liferay-ui:search-container-column-text name="Purchase date" value="<%= entry.formatDate() %>"/>
        <liferay-ui:search-container-column-text name="Purchase Type" value="<%= entry.showPurchaseType() %>" />
        <liferay-ui:search-container-column-text name="Action">
            <portlet:renderURL var="purchaseEditRender">
                <portlet:param name="mvcRenderCommandName" value="/purchase/create"/>
                <portlet:param name="purchaseId" value="${entry.id}"/>
            </portlet:renderURL>
            <a href="${purchaseEditRender}">Edit</a>
        </liferay-ui:search-container-column-text>
    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator />
</liferay-ui:search-container>

<a href="${purchaseCreateRender}" cssClass="btn bnt-primary">Create purchase</a>