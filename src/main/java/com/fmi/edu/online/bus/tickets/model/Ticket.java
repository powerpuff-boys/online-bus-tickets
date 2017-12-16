package com.fmi.edu.online.bus.tickets.model;

import java.util.Calendar;
import java.util.Date;

public class Ticket {

	private String id;

	private String busId;

	private Date createdOn;

	private Date expiresOn;

	private boolean isChecked;

	public Ticket() {
	}

	public Ticket(String busId, Date createdOn, boolean isChecked) {
		setBusId(busId);
		setCreatedOn(createdOn);
		setChecked(isChecked);
	}

	public Ticket(String ticketId, String busId, Date createdOn, boolean isChecked) {
		this(busId, createdOn, isChecked);
		setId(ticketId);
	}

	public Ticket(TicketDto ticketDto) {
		setId(ticketDto.getId());
		setBusId(ticketDto.getBusId());
		setCreatedOn(new Date(ticketDto.getCreatedOn()));
		setChecked(ticketDto.isChecked());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date localDateTime2) {
		this.createdOn = localDateTime2;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public Date getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(createdOn);
		cal.add(Calendar.HOUR, 2);
		this.expiresOn = cal.getTime();
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", busId=" + busId + ", localDateTime=" + createdOn + ", isChecked=" + isChecked
				+ "]";
	}

}
