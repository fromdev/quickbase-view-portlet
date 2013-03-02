<%@ include file="./init.jsp"%>

<liferay-portlet:actionURL name="saveConfig" var="saveConfigURL">
	<liferay-portlet:param name="url_param" value="edit"></liferay-portlet:param>
</liferay-portlet:actionURL>
<liferay-portlet:actionURL name="resetConfig" var="resetConfigURL">
	<liferay-portlet:param name="url_param" value="reset"></liferay-portlet:param>
</liferay-portlet:actionURL>

<liferay-ui:success key="config-data-reset-successfully"
	message="config-data-reset-successfully" />
<liferay-ui:success key="config-data-saved-successfully"
	message="config-data-saved-successfully" />
<%
PortletPreferences preferences = renderRequest.getPreferences();

String quickbaseUsername = preferences.getValue(GenericTableController.QUICKBASE_USERNAME, "quickbase.user@example.com"); // "quickbase.user@example.com";
String quickbasePassword = preferences.getValue(GenericTableController.QUICKBASE_PASSWORD, "password-for-above-quickbase-user");// password-for-above-quickbase-user";
String quickbaseAppToken = preferences.getValue(GenericTableController.QUICKBASE_APP_TOKEN, "get-app-token-from-quickbase"); // "get-app-token-from-quickbase";
String quickbaseDbId = preferences.getValue(GenericTableController.QUICKBASE_DB_ID, "get-dbid-from-quickbase"); // "get-dbid-from-quickbase";
String quickbaseQuery = preferences.getValue(GenericTableController.QUICKBASE_QUERY, "{1.EX.'TEST'}"); // "{17.EX.'TEST'}";
String quickbaseUrl = preferences.getValue(GenericTableController.QUICKBASE_URL, "https://www.quickbase.com/db/"); // "https://www.quickbase.com/db/";
%>
	<aui:form name="configForm"
		action="<%=saveConfigURL.toString()%>" method="post">
		<aui:fieldset label="Quickbase Configuration">
		<aui:input class="lfr-input-text-container"  name="quickbaseUsername" type="text" value="<%= quickbaseUsername %>" />
		<aui:input class="lfr-input-text-container"  name="quickbasePassword" type="text" value="<%= quickbasePassword %>" />
		<aui:input class="lfr-input-text-container"  name="quickbaseAppToken" type="text" value="<%= quickbaseAppToken %>" />
		<aui:input class="lfr-input-text-container"  name="quickbaseDbId" type="text" value="<%= quickbaseDbId %>" />
		<aui:input class="lfr-input-text-container"  name="quickbaseQuery" type="text" value="<%= quickbaseQuery %>" />
		<aui:input class="lfr-input-text-container"  name="quickbaseUrl" type="text" value="<%= quickbaseUrl %>" />
		
			<aui:button-row>
				<aui:button name="<portlet:namespace />saveButton" type="submit"
					value="Save" />
				<aui:button name="<portlet:namespace />resetButton" type="button"
					value="Reset" onClick="<%=resetConfigURL.toString()%>" />
			</aui:button-row>
		</aui:fieldset>
	</aui:form>