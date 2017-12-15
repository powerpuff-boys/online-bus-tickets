package com.fmi.edu.online.bus.tickets.model;

import java.sql.Date;

public class Ticket {

	private long id;
	private long busId;
	private Date dateTime;
	private boolean isChecked;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getBusId() {
		return busId;
	}
	
	public void setBusId(long busId) {
		this.busId = busId;
	}
	
	public Date getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	public boolean isChecked() {
		return isChecked;
	}
	
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
}
