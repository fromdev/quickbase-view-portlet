
<%String error = (String)com.liferay.portal.kernel.servlet.SessionErrors.get(renderRequest,com.fromdev.portlet.action.GenericTableController.ERROR);
if(org.springframework.util.StringUtils.hasText(error)) {
 %>
 <liferay-ui:error key="<%=com.fromdev.portlet.action.GenericTableController.ERROR%>" message="<%=error%>" />
<%}%>