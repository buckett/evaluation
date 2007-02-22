package org.sakaiproject.evaluation.model;

// Generated Feb 15, 2007 3:42:04 PM by Hibernate Tools 3.2.0.beta6a

import java.util.Date;

/**
 * EvalAssignContext generated by hbm2java
 */
public class EvalAssignGroup implements java.io.Serializable {

	// Fields    

	private Long id;

	private Date lastModified;

	private String owner;

	private String evalGroupId;

	private Boolean instructorApproval;

	private Boolean instructorsViewResults;

	private Boolean studentsViewResults;

	private EvalEvaluation evaluation;

	// Constructors

	/** default constructor */
	public EvalAssignGroup() {
	}

	/** full constructor */
	public EvalAssignGroup(Date lastModified, String owner, String context, Boolean instructorApproval,
			Boolean instructorsViewResults, Boolean studentsViewResults, EvalEvaluation evaluation) {
		this.lastModified = lastModified;
		this.owner = owner;
		this.evalGroupId = context;
		this.instructorApproval = instructorApproval;
		this.instructorsViewResults = instructorsViewResults;
		this.studentsViewResults = studentsViewResults;
		this.evaluation = evaluation;
	}

	// Property accessors
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getEvalGroupId() {
		return this.evalGroupId;
	}

	public void setEvalGroupId(String context) {
		this.evalGroupId = context;
	}

	public Boolean getInstructorApproval() {
		return this.instructorApproval;
	}

	public void setInstructorApproval(Boolean instructorApproval) {
		this.instructorApproval = instructorApproval;
	}

	public Boolean getInstructorsViewResults() {
		return this.instructorsViewResults;
	}

	public void setInstructorsViewResults(Boolean instructorsViewResults) {
		this.instructorsViewResults = instructorsViewResults;
	}

	public Boolean getStudentsViewResults() {
		return this.studentsViewResults;
	}

	public void setStudentsViewResults(Boolean studentsViewResults) {
		this.studentsViewResults = studentsViewResults;
	}

	public EvalEvaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(EvalEvaluation evaluation) {
		this.evaluation = evaluation;
	}

}