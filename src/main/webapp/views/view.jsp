<%@ include file="./init.jsp"%>

<%
	Map<String, List> tableData = (Map<String, List>) renderRequest
			.getAttribute("data");
    if(tableData == null) {
    	tableData = new HashMap();
    }
%>
<%@ include file="error.jsp"%>
<h3>Quickbase Table View</h3>

<liferay-ui:search-container  emptyResultsMessage="No data to display"
	delta="10">

	<liferay-ui:search-container-results>
		<%
		List<Object[]> rows = (List<Object[]>) tableData
		.get(com.fromdev.portlet.data.Provider.ROW_DATA_LIST);
					if (rows == null) {
						rows = new ArrayList<Object[]>();
					}

					results = ListUtil.subList(rows,
							searchContainer.getStart(),
							searchContainer.getEnd());
					total = rows.size();
					pageContext.setAttribute("results", results);
					pageContext.setAttribute("total", total);
		%>
	</liferay-ui:search-container-results>
	<liferay-ui:search-container-row className="java.lang.Object[]"
		modelVar="rows">
		<%
			List<String> columns = (List<String>) tableData
							.get(com.fromdev.portlet.data.Provider.COLUMN_NAMES_LIST);
			int noOfColumns = columns.size();

			for (int i = 0; i < noOfColumns; i++) {
				String columnName = columns.get(i);
		%>
				<liferay-ui:search-container-column-text name='<%=columnName%>'>
					<%=rows[i]%> 
				</liferay-ui:search-container-column-text>
		<%
			}
		%>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>
