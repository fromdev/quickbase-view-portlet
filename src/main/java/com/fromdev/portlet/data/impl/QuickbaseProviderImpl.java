/**
 * 
 */
package com.fromdev.portlet.data.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fromdev.portlet.data.Provider;
import com.intuit.quickbase.util.QuickBaseClient;

/**
 * @author sjoshi
 * 
 */
@Component(value = "provider")
public class QuickbaseProviderImpl implements Provider {

	private Logger logger = LoggerFactory.getLogger(QuickbaseProviderImpl.class);

	@Override
	@SuppressWarnings("rawtypes")
	public Map<String, List> getTableData() {

		logger.info("Start getTableData QuickbaseProviderImpl " + getUserName()
				+ " " + getAppToken() + " " + getDbId() + " " + getQuery());

		Map<String, List> tableData = new HashMap<String, List>();
		Vector resultSet = null;

		try {

			QuickBaseClient client = new QuickBaseClient(getUserName(),
					getPassword(), getUrl(), getAppToken());

			resultSet = client.doQuery(getDbId(), getQuery(), "", "", "");
			if (resultSet != null) {
				tableData.put(COLUMN_NAMES_LIST, this.getColumns(resultSet));
				tableData.put(ROW_DATA_LIST, this.getRows(resultSet));
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error loading data from quickabse", e);
		}
		logger.info("Finished getTableData QuickbaseProviderImpl.");
		return tableData;
	}



	/**
	 * Construct data rows list
	 * 
	 * @param resultSet
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<Object[]> getRows(Vector resultSet) {

		List<Object[]> rowList = new ArrayList<Object[]>();

		for (Iterator iterator = resultSet.iterator(); iterator.hasNext();) {
			HashMap qbRecord = (HashMap) iterator.next();

			Object[] row = this.constructRow(qbRecord);
			rowList.add(row);

		}

		return rowList;
	}

	/**
	 * Construct data row from quickbase record
	 * 
	 * @param qbRecord
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Object[] constructRow(HashMap qbRecord) {

		Object[] row = new Object[qbRecord.size()];

		Collection values = qbRecord.values();
		int i = 0;

		for (Iterator iterator = values.iterator(); iterator.hasNext();) {
			row[i] = (Object) iterator.next();
			i++;

		}

		return row;
	}

	/**
	 * Construct column names list
	 * 
	 * @param resultSet
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private List<String> getColumns(Vector resultSet) {

		List<String> columnList = new ArrayList<String>();

		int size = resultSet.size();

		if (size > 0) {
			HashMap row = (HashMap) resultSet.get(0);
			Set keys = row.keySet();

			for (Iterator iterator = keys.iterator(); iterator.hasNext();) {

				String columnName = (String) iterator.next();
				columnList.add(columnName);

			}
		}

		return columnList;
	}

	private String userName;
	private String password;
	private String appToken;
	private String dbId;
	private String query;
	private String url;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getDbId() {
		return dbId;
	}

	public void setDbId(String dbId) {
		this.dbId = dbId;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}		

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
