<%@ include file="/init.jsp" %>


<portlet:actionURL name="saveEmployee" var="saveEmployeeURL"></portlet:actionURL>

<h1>Add data to Position Type Table</h1>
<aui:form name="fm" action="${saveEmployeeURL}">

    <aui:input name="lastName"  type="text"></aui:input>
    <aui:input name="firstName" type="text"></aui:input>
    <aui:input name="patronymic"  label="Patronymic" type="text"></aui:input>
    <aui:input name="birthday"  type="date"></aui:input>

    <aui:select name="position" label="Position">
        <c:forEach var="current" items="${positionTypes}">
            <aui:option value="${current.key}">${current.value}</aui:option>
        </c:forEach>
    </aui:select>

     <aui:select name="gender" label="Gender">
        <aui:option value="male">Male</aui:option>
        <aui:option value="female">Female</aui:option>
    </aui:select>

    <aui:button-row>
        <aui:button cssClass="btn bnt-primary" type="submit" />
    </aui:button-row>
</aui:form>