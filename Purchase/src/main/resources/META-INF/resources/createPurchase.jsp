<%@ include file="/init.jsp" %>
<%@ page import="com.db.model.Purchase" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.db.service.PurchaseLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="com.liferay.portal.kernel.model.ModelHintsUtil"%>

<%
    long purchaseId = ParamUtil.getLong(renderRequest, "purchaseId", 0);
    Purchase purchase = null;

    if (purchaseId > 0){
        try {
            purchase = PurchaseLocalServiceUtil.getPurchase(purchaseId);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
    }
%>
<portlet:actionURL name="savePurchase" var="savePurchaseURL">
    <portlet:param name="purchaseId" value="<%=String.valueOf(purchaseId)%>"/>
</portlet:actionURL>

<liferay-ui:error key="error-employee-message" message="Wrong employee for this electronic, please choose another one" />
<liferay-ui:error key="error-electronic-count-message" message="Dont have any chosen electronics anymore" />
<liferay-ui:error key="error-date-message" message="Wrong date input" />

<h1> Purchase creation </h1>
<aui:form name="fm" action="${savePurchaseURL}">

    <aui:model-context bean="<%= purchase %>" model="<%= Purchase.class %>" />

    <aui:select name="electronicId" label="Electronic">
        <c:forEach var="current" items="${electronics}">
            <aui:option value="${current.key}">${current.value}</aui:option>
        </c:forEach>
        <aui:validator name="required" />
    </aui:select>

    <aui:select name="employeeId" label="Employee">
        <c:forEach var="current" items="${employees}">
            <aui:option value="${current.key}">${current.value}</aui:option>
        </c:forEach>
        <aui:validator name="required" />
    </aui:select>

     <aui:input
        name="purchaseDate"
        maxLength="<%= ModelHintsUtil.getMaxLength(Purchase.class.getName(), \"purchaseDate\") %>">
        <aui:validator name="required" />
    </aui:input>

    <aui:select name="purchaseTypeId" label="Purchase Type">
        <c:forEach var="current" items="${purchaseTypes}">
            <aui:option value="${current.key}">${current.value}</aui:option>
        </c:forEach>
        <aui:validator name="required" />
    </aui:select>

    <aui:button-row>
        <aui:button cssClass="btn bnt-primary" type="submit" />
    </aui:button-row>
</aui:form>
<a href="<portlet:renderURL />">Back</a>