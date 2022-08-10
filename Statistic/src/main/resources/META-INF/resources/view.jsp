<%@ include file="/init.jsp" %>

<portlet:defineObjects />
<%
String[] tvSeller = (String[]) renderRequest.getAttribute("tv-seller");
String[] amountCardPurchases = (String[]) renderRequest.getAttribute("amount-purchases-paid-by-a-card");
String[] amountArchiveTvSmartphones = (String[]) renderRequest.getAttribute("amount-archive-tv-smartphones");
%>

<h1>The Best TV seller in a month</h1>
<h2>Employee: <%= tvSeller[0] %></h2>
<h2>Amount of purchases: <%= tvSeller[1] %></h2>
<hr>

<h1>Amount of purchases paid by card</h1>
<h2>Amount(rubles): <%= amountCardPurchases[0] %></h2>
<hr>

<h1>Amount of purchases of the archive electronic</h1>
<h2>Amount(rubles): <%= amountArchiveTvSmartphones[0] %></h2>
<hr>