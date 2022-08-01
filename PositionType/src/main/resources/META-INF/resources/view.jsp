<%@ include file="/init.jsp" %>

<portlet:actionURL name="savePosition" var="savePositionURL"></portlet:actionURL>

<h1>Add data to Position Type Table</h1>

<aui:form name="fm" action="${savePositionURL}">

    <aui:input name="name"></aui:input>

    <aui:button-row>
        <aui:button cssClass="btn bnt-primary" type="submit" />
    </aui:button-row>
</aui:form>