/******************************************************************************
 * TemplateProducer.java - created on Aug 21, 2006
 * 
 * Copyright (c) 2007 Virginia Polytechnic Institute and State University
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 * Contributors:
 * Antranig Basman (antranig@caret.cam.ac.uk)
 * Kapil Ahuja (kahuja@vt.edu)
 *****************************************************************************/

package org.sakaiproject.evaluation.tool.producers;

import java.util.ArrayList;
import java.util.List;

import org.sakaiproject.evaluation.logic.EvalExternalLogic;
import org.sakaiproject.evaluation.logic.EvalSettings;
import org.sakaiproject.evaluation.model.constant.EvalConstants;
import org.sakaiproject.evaluation.tool.EvaluationConstant;
import org.sakaiproject.evaluation.tool.TemplateBeanLocator;
import org.sakaiproject.evaluation.tool.params.EvalViewParameters;

import uk.org.ponder.rsf.components.UIBranchContainer;
import uk.org.ponder.rsf.components.UICommand;
import uk.org.ponder.rsf.components.UIContainer;
import uk.org.ponder.rsf.components.UIELBinding;
import uk.org.ponder.rsf.components.UIForm;
import uk.org.ponder.rsf.components.UIInput;
import uk.org.ponder.rsf.components.UIInternalLink;
import uk.org.ponder.rsf.components.UIMessage;
import uk.org.ponder.rsf.components.UISelect;
import uk.org.ponder.rsf.flow.jsfnav.NavigationCase;
import uk.org.ponder.rsf.flow.jsfnav.NavigationCaseReporter;
import uk.org.ponder.rsf.view.ComponentChecker;
import uk.org.ponder.rsf.view.ViewComponentProducer;
import uk.org.ponder.rsf.viewstate.SimpleViewParameters;
import uk.org.ponder.rsf.viewstate.ViewParameters;
import uk.org.ponder.rsf.viewstate.ViewParamsReporter;

/**
 * view to start creating a template and for modifying the template settings
 * (title, description, sharing)
 * 
 * @author: Kapil Ahuja (kahuja@vt.edu)
 */
public class ModifyTemplateProducer 
	implements ViewComponentProducer, ViewParamsReporter, NavigationCaseReporter {

	public static final String VIEW_ID = "modify_template";
	public String getViewID() {
		return VIEW_ID;
	}

	private EvalSettings settings;
	public void setSettings(EvalSettings settings) {
		this.settings = settings;
	}

	private EvalExternalLogic externalLogic;
	public void setExternalLogic(EvalExternalLogic externalLogic) {
		this.externalLogic = externalLogic;
	}


	/*
	 * 1) accessing this page trough "Create Template" link -- 2) accessing
	 * through "Modify Template Title/Description" link on ModifyTemplate page
	 * 2-1) no template been save in DAO 2-2) existing template in DAO
	 * 
	 */
	public void fillComponents(UIContainer tofill, ViewParameters viewparams,
			ComponentChecker checker) {
		EvalViewParameters evalViewParams = (EvalViewParameters) viewparams;

		// setup the OTP binding strings
		String templateOTPBinding = null;
		if (evalViewParams.templateId != null) {
			templateOTPBinding = "templateBeanLocator." + evalViewParams.templateId; //$NON-NLS-1$
		} else {
			templateOTPBinding = "templateBeanLocator." + TemplateBeanLocator.NEW_1; //$NON-NLS-1$
		}
		String templateOTP = templateOTPBinding + "."; //$NON-NLS-1$

		UIMessage.make(tofill, "template-title-desc-title", "modifytemplatetitledesc.page.title"); //$NON-NLS-1$ //$NON-NLS-2$

		UIInternalLink.make(tofill,	"summary-toplink", UIMessage.make("summary.page.title"), //$NON-NLS-1$ //$NON-NLS-2$
				new SimpleViewParameters(SummaryProducer.VIEW_ID));

		UIForm form = UIForm.make(tofill, "basic-form"); //$NON-NLS-1$
		UIMessage.make(form, "title-header", "modifytemplatetitledesc.title.header"); //$NON-NLS-1$ //$NON-NLS-2$
		UIMessage.make(form, "description-header", "modifytemplatetitledesc.description.header"); //$NON-NLS-1$ //$NON-NLS-2$
		UIMessage.make(form, "description-note", "modifytemplatetitledesc.description.note"); //$NON-NLS-1$ //$NON-NLS-2$

		UICommand.make(form, "addContinue", UIMessage.make("modifytemplatetitledesc.save.button"),
				"#{templateBBean.updateTemplateTitleDesc}");

		UIInput.make(form, "title", templateOTP + "title");
		UIInput.make(form, "description", templateOTP + "description"); //$NON-NLS-1$ //$NON-NLS-2$

		/*
		 * (Non-javadoc) If "EvalSettings.TEMPLATE_SHARING_AND_VISIBILITY" is set
		 * EvalConstants.SHARING_OWNER, then it means that owner can decide what
		 * sharing settings to chose. In other words, it means that show the
		 * sharing/visibility dropdown. Else just show the label for what has been
		 * set in system setting (admin setting) i.e.
		 * "EvalSettings.TEMPLATE_SHARING_AND_VISIBILITY".
		 */
		String sharingkey = null;
		String sharingValue = (String) settings.get(EvalSettings.TEMPLATE_SHARING_AND_VISIBILITY);
		if ( EvalConstants.SHARING_OWNER.equals(sharingValue) ) {
			/*
			 * Dropdown values are visible only for admins. For instructors
			 * (non-admin) we just show the private label
			 */
			if (externalLogic.isUserAdmin(externalLogic.getCurrentUserId())) {
				UIBranchContainer showSharingOptions = UIBranchContainer.make(form,	"showSharingOptions:"); //$NON-NLS-1$
				String[] sharingList = {
						"modifytemplatetitledesc.sharing.private", //$NON-NLS-1$
						"modifytemplatetitledesc.sharing.public" //$NON-NLS-1$
				};
				UISelect.make(showSharingOptions, "sharing",
						EvaluationConstant.MODIFIER_VALUES, sharingList, templateOTP
						+ "sharing", null).setMessageKeys();
			}
			else {
				sharingkey = "modifytemplatetitledesc.sharing.private";
				form.parameters.add(new UIELBinding(templateOTP + "sharing", EvalConstants.SHARING_PRIVATE)); //$NON-NLS-1$
			}
		} else {
			if ((EvalConstants.SHARING_PRIVATE).equals(sharingValue))
				sharingkey = "modifytemplatetitledesc.sharing.private"; //$NON-NLS-1$
			else if ((EvalConstants.SHARING_PUBLIC).equals(sharingValue))
				sharingkey = "modifytemplatetitledesc.sharing.public"; //$NON-NLS-1$

			// Doing the binding of this sharing value so that it can be persisted
			form.parameters.add(new UIELBinding(templateOTP + "sharing", sharingValue)); //$NON-NLS-1$
		}

		if (sharingkey != null) {
			// Displaying the sharing label
			UIMessage.make(form, "sharingValueToDisplay", sharingkey); //$NON-NLS-1$
		}

		UIMessage.make(form, "cancel-button", "general.cancel.button");
	}

	/* (non-Javadoc)
	 * @see uk.org.ponder.rsf.viewstate.ViewParamsReporter#getViewParameters()
	 */
	public ViewParameters getViewParameters() {
		return new EvalViewParameters();
	}

	/* (non-Javadoc)
	 * @see uk.org.ponder.rsf.flow.jsfnav.NavigationCaseReporter#reportNavigationCases()
	 */
	public List reportNavigationCases() {
		List togo = new ArrayList();
		togo.add(new NavigationCase("success", new EvalViewParameters(
				ModifyTemplateItemsProducer.VIEW_ID, null)));
		return togo;
	}

}