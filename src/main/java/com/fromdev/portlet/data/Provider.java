/**
 * 
 */
package com.fromdev.portlet.data;

import java.util.List;
import java.util.Map;

/**
 * @author sjoshi
 * 
 */
@SuppressWarnings("rawtypes")
public interface Provider {

	public static final String ROW_DATA_LIST = "rowList";
	public static final String COLUMN_NAMES_LIST = "columnList";

	public Map<String, List> getTableData();
	public void setConfig(Map config);

}
