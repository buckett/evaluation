/******************************************************************************
 * PreviewEvalParameters.java - created by aaronz on 31 May 2007
 * 
 * Copyright (c) 2007 Centre for Academic Research in Educational Technologies
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 * Contributors:
 * Aaron Zeckoski (aaronz@vt.edu) - primary
 * 
 *****************************************************************************/

package org.sakaiproject.evaluation.tool.viewparams;

import uk.org.ponder.rsf.viewstate.SimpleViewParameters;

/**
 * Allows for passing of information needed for previewing templates or evaluations
 * (rewrite of original)
 * 
 * @author Aaron Zeckoski (aaronz@vt.edu)
 */
public class PreviewEvalParameters extends SimpleViewParameters {

	public Long evaluationId;
	public Long templateId; 
	public String evalGroupId;

	public PreviewEvalParameters() { }

	public PreviewEvalParameters(String viewID, Long evaluationId, Long templateId) {
		this.viewID = viewID;
		this.evaluationId = evaluationId;	
		this.templateId = templateId;
	}

	public PreviewEvalParameters(String viewID, Long evaluationId, Long templateId, String evalGroupId) {
		this.viewID = viewID;
		this.evaluationId = evaluationId;	
		this.templateId = templateId;
		this.evalGroupId = evalGroupId;
	}

}