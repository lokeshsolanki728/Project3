package in.co.rays.project_3.dto;

import java.util.Date;

public class IssueDTO extends BaseDTO {
	private Date openDate;
	private String title;
	private String description;
	private String assignTo;
	private String status;

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {

		return assignTo;
	}

	@Override
	public String getValue() {

		return assignTo;
	}

}
