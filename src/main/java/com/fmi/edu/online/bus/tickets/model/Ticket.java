package com.fmi.edu.online.bus.tickets.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Ticket")
public class Ticket {

	@Id
	@GeneratedValue
	private String id;

	@Column(nullable = false)
	private String busId;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date localDateTime;

	@Column(nullable = false)
	private boolean isChecked;

	public Ticket() {
	}

	public Ticket(String busId, Date createdOn, boolean isChecked) {
		setBusId(busId);
		setLocalDateTime(localDateTime);
		setChecked(isChecked);
	}

	public Ticket(String ticketId, String busId, Date createdOn, boolean isChecked) {
		this(busId, createdOn, isChecked);
		setId(ticketId);
	}

	@SuppressWarnings("deprecation")
	public Ticket(TicketDto ticketDto) {
		setId(ticketDto.getId());
		setBusId(ticketDto.getBusId());
		this.setLocalDateTime(new Date(ticketDto.getLocalDateTime()));
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

	public Date getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(Date localDateTime2) {
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
