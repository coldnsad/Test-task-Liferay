<%@ include file="/init.jsp" %>
<%@ page import="com.db.model.Employee" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.db.service.EmployeeLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="com.liferay.portal.kernel.model.ModelHintsUtil"%>

<%
    long employeeId = ParamUtil.getLong(renderRequest, "employeeId", 0);
    Employee employee = null;

    if (employeeId > 0){
        try {
            employee = EmployeeLocalServiceUtil.getEmployee(employeeId);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
    }
%>
<portlet:actionURL name="saveEmployee" var="saveEmployeeURL">
    <portlet:param name="employeeId" value="<%=String.valueOf(employeeId)%>"/>
</portlet:actionURL>

<liferay-ui:error key="error-date-message" message="Wrong date input" />
<h1> Employee creation </h1>
<aui:form name="fm" action="${saveEmployeeURL}">

    <aui:model-context bean="<%= employee %>" model="<%= Employee.class %>" />
    <aui:input
        name="lastname"
        maxLength="<%= ModelHintsUtil.getMaxLength(Employee.class.getName(), \"lastname\") %>"
        >
        <aui:validator name="required" />
    </aui:input>
    <aui:input name="firstname">
        <aui:validator name="required" />
    </aui:input>
    <aui:input name="patronymic" label="Patronymic">
        <aui:validator name="required" />
    </aui:input>
    <aui:input name="birthdate">
        <aui:validator name="required" />
    </aui:input>

    <aui:select name="positionTypesId" label="Position">
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
<a href="<portlet:renderURL />">Back</a>