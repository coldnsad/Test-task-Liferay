<%@ include file="/init.jsp" %>
<%@ page import="com.db.model.Electronic" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.db.service.ElectronicLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="com.liferay.portal.kernel.model.ModelHintsUtil"%>

<%
    long electronicId = ParamUtil.getLong(renderRequest, "electronicId", 0);
    Electronic electronic = null;

    if (electronicId > 0){
        try {
            electronic = ElectronicLocalServiceUtil.getElectronic(electronicId);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
    }
%>
<portlet:actionURL name="saveElectronic" var="saveElectronicURL">
    <portlet:param name="electronicId" value="<%=String.valueOf(electronicId)%>"/>
</portlet:actionURL>

<h1> Electronic creation </h1>
<aui:form name="fm" action="${saveElectronicURL}">

    <aui:model-context bean="<%= electronic %>" model="<%= Electronic.class %>" />
    <aui:input
        name="name"
        maxLength="<%= ModelHintsUtil.getMaxLength(Electronic.class.getName(), \"name\") %>"
        >
        <aui:validator name="required" />
    </aui:input>
    <aui:select name="etype" label="Electronic type">
        <c:forEach var="current" items="${electronicTypes}">
            <aui:option value="${current.key}">${current.value}</aui:option>
        </c:forEach>
        <aui:validator name="required" />
    </aui:select>
    <aui:input name="price" label="Price(in kopecks)"
        maxLength="<%= ModelHintsUtil.getMaxLength(Electronic.class.getName(), \"price\") %>">
        <aui:validator name="required" />
    </aui:input>
    <aui:input name="count"
        maxLength="<%= ModelHintsUtil.getMaxLength(Electronic.class.getName(), \"count\") %>">
        <aui:validator name="required" />
    </aui:input>
    <aui:input type="textarea" name="description" placeholder="Your description here..."
        maxLength="<%= ModelHintsUtil.getMaxLength(Electronic.class.getName(), \"description\") %>" >
        <aui:validator name="required" />
    </aui:input>
    <aui:input type="checkbox" name="instock" checked="<%= (electronic != null) ? ((electronic.getInstock() == true) ? true : false) : false %>" />
    <aui:input type="checkbox" name="archive" checked="<%= (electronic != null) ? ((electronic.getArchive() == true) ? true : false) : false %>" />
    <aui:button-row>
        <aui:button cssClass="btn bnt-primary" type="submit" />
    </aui:button-row>
</aui:form>
<a href="<portlet:renderURL />">Back</a>