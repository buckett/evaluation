/******************************************************************************
 * EvaluationEntityProviderImpl.java - created by aaronz on 23 May 2007
 * 
 * Copyright (c) 2008 Centre for Applied Research in Educational Technologies, University of Cambridge
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 * Contributors:
 * Aaron Zeckoski (aaronz@vt.edu) - primary
 * 
 *****************************************************************************/

package org.sakaiproject.evaluation.logic.entity;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.entitybroker.EntityReference;
import org.sakaiproject.entitybroker.entityprovider.CoreEntityProvider;
import org.sakaiproject.entitybroker.entityprovider.capabilities.AutoRegisterEntityProvider;
import org.sakaiproject.entitybroker.entityprovider.capabilities.RESTful;
import org.sakaiproject.entitybroker.entityprovider.extension.Formats;
import org.sakaiproject.entitybroker.entityprovider.search.Search;
import org.sakaiproject.evaluation.logic.EvalEvaluationService;
import org.sakaiproject.evaluation.logic.entity.EvaluationEntityProvider;

/**
 * Implementation for the entity provider for evaluationSetupService
 * 
 * @author Aaron Zeckoski (aaronz@vt.edu)
 */
public class EvaluationEntityProviderImpl implements EvaluationEntityProvider, CoreEntityProvider, AutoRegisterEntityProvider {

	private static Log log = LogFactory.getLog(EvaluationEntityProviderImpl.class);
	
	private EvalEvaluationService evaluationService;
   public void setEvaluationService(EvalEvaluationService evaluationService) {
      this.evaluationService = evaluationService;
   }
   

	public String getEntityPrefix() {
		return ENTITY_PREFIX;
	}

	public boolean entityExists(String id) {
		Long evaluationId;
		try {
		   evaluationId = new Long(id);
			if (evaluationService.checkEvaluationExists(evaluationId)) {
				return true;
			}
		} catch (NumberFormatException e) {
			// invalid number so roll through to the false
		}
		log.warn("NOTE: evaluation does not exist: " + id);
		return false;
	}

}
