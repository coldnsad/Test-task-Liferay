<%@ include file="/init.jsp" %>

<portlet:actionURL name="saveElectroType" var="saveElectroTypeURL"></portlet:actionURL>

<h1>Add data to Electro Type Table</h1>

<aui:form name="fm" action="${saveElectroTypeURL}">

    <aui:input name="name" type="text"></aui:input>

    <aui:button-row>
        <aui:button cssClass="btn bnt-primary" type="submit" />
    </aui:button-row>
</aui:form>