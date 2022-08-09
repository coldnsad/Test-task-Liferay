<%@ include file="/init.jsp" %>

<portlet:actionURL name="saveElectroEmployee" var="saveElectroEmployeeURL"></portlet:actionURL>

<h1>Add data to Electronic Employee Table</h1>

<aui:form name="fm" action="${saveElectroEmployeeURL}">

    <aui:select name="employeeId" label="Employee">
        <c:forEach var="current" items="${employees}">
            <aui:option value="${current.key}">${current.value}</aui:option>
        </c:forEach>
    </aui:select>

    <aui:select name="etypeId" label="Electronic Type">
        <c:forEach var="current" items="${electroTypes}">
            <aui:option value="${current.key}">${current.value}</aui:option>
        </c:forEach>
    </aui:select>

    <aui:button-row>
        <aui:button cssClass="btn bnt-primary" type="submit" />
    </aui:button-row>
</aui:form>