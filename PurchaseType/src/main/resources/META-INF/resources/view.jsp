<%@ include file="/init.jsp" %>

<portlet:actionURL name="savePurchaseType" var="savePurchaseTypeURL"></portlet:actionURL>

<h1>Add data to Purchase Type Table</h1>

<aui:form name="fm" action="${savePurchaseTypeURL}">

    <aui:input name="name" type="text"></aui:input>

    <aui:button-row>
        <aui:button cssClass="btn bnt-primary" type="submit" />
    </aui:button-row>
</aui:form>