package in.co.rays.project_3.dto;

import java.util.Date;

import in.co.rays.project_3.model.JobModelInt;

public class JobRequirementDTO extends BaseDTO{
	String title;
	String client;
	Date openDate;
	String jobDiscription;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public String getJobDiscription() {
		return jobDiscription;
	}
	public void setJobDiscription(String jobDiscription) {
		this.jobDiscription = jobDiscription;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return client;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return client;
	}
	
}
