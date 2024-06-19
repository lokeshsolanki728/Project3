package in.co.rays.project_3.dto;

import java.util.Date;

public class ClientDTO extends BaseDTO{
	
	private String name;
	private String address;
	private String phone;
	private String priority;
	private Date date;
	private double version;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	}
	@Override
	public String getKey() {
		return id+"";
	}
	@Override
	public String getValue() {
		return priority;
	}
	
	
	
}
