package com.fromdev.portlet.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fromdev.portlet.data.Provider;

/**
 * Portlet implementation class GenericTableController
 * @author sjoshi
 */

@Controller(value = "genericTableController")
@RequestMapping(value = "VIEW")
public class GenericTableController {

	private static final Logger logger = LoggerFactory
			.getLogger(GenericTableController.class);

	public static final String VIEW = "view";
	@Autowired
	private Provider provider;

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	/**
	 * Render method called for view mode
	 */
	@RenderMapping
	public ModelAndView doView_Render_Database(RenderRequest rRequest,
			RenderResponse rResponse) {

		logger.info("*****Entering doView_Render in GenericTableController*****");

		ModelAndView modelView = new ModelAndView();

		if (provider != null) {
			modelView.addObject("data", provider.getTableData());
		}

		modelView.setViewName(VIEW);

		return modelView;
	}

	/**
	 * Action method called for view mode
	 */
	@ActionMapping
	public void doView_Action(ActionRequest aRequest, ActionResponse aResponse) {

		logger.info("*****Entering doView_Action*****");

	}

}
