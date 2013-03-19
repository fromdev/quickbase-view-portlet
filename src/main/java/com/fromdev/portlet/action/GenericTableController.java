package com.fromdev.portlet.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fromdev.portlet.data.Provider;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;

/**
 * Portlet implementation class GenericTableController
 * 
 * @author sjoshi
 */

@Controller(value = "genericTableController")
public class GenericTableController {

	public static final String QUICKBASE_URL = "quickbaseUrl";

	public static final String QUICKBASE_QUERY = "quickbaseQuery";

	public static final String QUICKBASE_DB_ID = "quickbaseDbId";

	public static final String QUICKBASE_APP_TOKEN = "quickbaseAppToken";

	public static final String QUICKBASE_PASSWORD = "quickbasePassword";

	public static final String QUICKBASE_USERNAME = "quickbaseUsername";

	public static final Logger logger = LoggerFactory
			.getLogger(GenericTableController.class);

	public static final String VIEW = "view";
	public static final String CONFIG = "config";
	public static final String ERROR = "qb.portlet.error";
	public static final String SUCCESS = "success";
	@Autowired
	private Provider provider;

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	/**
	 * Render method called for view mode
	 */
	@RequestMapping(value = "VIEW")
	@RenderMapping
	public ModelAndView doView(RenderRequest rRequest, RenderResponse rResponse) {

		logger.debug("Entering doView in GenericTableController");

		ModelAndView modelView = new ModelAndView();
		
		if (provider != null) {
			/*
			 * Override the default quickbase config settings.
			 */
			provider.setConfig(rRequest.getPreferences().getMap());
			try {
				modelView.addObject("data", provider.getTableData());
			}catch(RuntimeException re) {
				logger.error("Failed to get data from Quickbase", re);
				SessionErrors.add(rRequest,ERROR, "Failed to get data from Quickbase, Please check your preferences: " + re.getMessage());	
			}
		}

		modelView.setViewName(VIEW);

		return modelView;
	}

	/**
	 * Render method called for edit mode is for config
	 */
	@RequestMapping(value = "EDIT")
	@RenderMapping
	public ModelAndView doConfig(RenderRequest rRequest,
			RenderResponse rResponse) {

		logger.info("*****Entering doConfig in GenericTableController*****");

		ModelAndView modelView = new ModelAndView();

		modelView.setViewName(CONFIG);

		return modelView;
	}

	/**
	 * Action method called for saving edit mode config
	 */
	@RequestMapping(value = "EDIT")
	@ActionMapping(value = "saveConfig")
	public void saveConfig(ActionRequest aRequest, ActionResponse aResponse) {

		logger.info("*****Entering saveConfig in GenericTableController*****");
		String quickbaseUsername = aRequest.getParameter(QUICKBASE_USERNAME); // "quickbase.user@example.com";
		String quickbasePassword = aRequest.getParameter(QUICKBASE_PASSWORD);// password-for-above-quickbase-user";
		String quickbaseAppToken = aRequest.getParameter(QUICKBASE_APP_TOKEN); // "get-app-token-from-quickbase";
		String quickbaseDbId = aRequest.getParameter(QUICKBASE_DB_ID); // "get-dbid-from-quickbase";
		String quickbaseQuery = aRequest.getParameter(QUICKBASE_QUERY); // "{17.EX.'TEST'}";
		String quickbaseUrl = aRequest.getParameter(QUICKBASE_URL); // "https://www.quickbase.com/db/";

		PortletPreferences preferences = aRequest.getPreferences();
		try {
			if (StringUtils.hasText(quickbaseUsername)) {
				preferences.setValue(QUICKBASE_USERNAME, quickbaseUsername);
			} else {
				SessionErrors.add(aRequest,"quickbase-username-is-required");	
			}
			if (StringUtils.hasText(quickbasePassword)) {
				preferences.setValue(QUICKBASE_PASSWORD, quickbasePassword);
			}
			if (StringUtils.hasText(quickbaseAppToken)) {
				preferences.setValue(QUICKBASE_APP_TOKEN, quickbaseAppToken);
			}
			if (StringUtils.hasText(quickbaseDbId)) {
				preferences.setValue(QUICKBASE_DB_ID, quickbaseDbId);
			}
			if (StringUtils.hasText(quickbaseQuery)) {
				preferences.setValue(QUICKBASE_QUERY, quickbaseQuery);
			}
			if (StringUtils.hasText(quickbaseUrl)) {
				preferences.setValue(QUICKBASE_URL, quickbaseUrl);
			}
			preferences.store();
			if(SessionErrors.isEmpty(aRequest)) {
				SessionMessages.add(aRequest,SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SessionErrors.add(aRequest,ERROR, "Error Saving config data: " + e.getMessage());			
		}
	}

	/**
	 * Action method called for saving edit mode config
	 */
	@RequestMapping(value = "EDIT")
	@ActionMapping(value = "resetConfig")
	public void resetConfig(ActionRequest aRequest, ActionResponse aResponse) {

		logger.info("*****Entering resetConfig in GenericTableController*****");

	}

}
