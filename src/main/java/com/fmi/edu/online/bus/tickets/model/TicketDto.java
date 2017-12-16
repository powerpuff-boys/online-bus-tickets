package com.fmi.edu.online.bus.tickets.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class TicketDto {

	private String id;

	private String busId;

	private String localDateTime;

	@SerializedName("checked")
	private boolean isChecked;

	public TicketDto() {
	}

	public TicketDto(String busId, Date createdOn, boolean isChecked) {
		setBusId(busId);
		setLocalDateTime(localDateTime);
		setChecked(isChecked);
	}

	public TicketDto(String ticketId, String busId, Date createdOn, boolean isChecked) {
		this(busId, createdOn, isChecked);
		setId(ticketId);
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

	public String getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(String localDateTime2) {
		this.localDateTime = localDateTime2;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", busId=" + busId + ", localDateTime=" + localDateTime + ", isChecked=" + isChecked
				+ "]";
	}

}
