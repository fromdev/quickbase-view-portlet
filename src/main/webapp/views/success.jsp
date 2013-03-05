
<%String msg = (String)renderRequest.getAttribute(com.fromdev.portlet.action.GenericTableController.SUCCESS);
if(org.springframework.util.StringUtils.hasText(msg)) {
 %>
<div class="portlet-msg-success"> <%=msg%></div> 
<%}%>