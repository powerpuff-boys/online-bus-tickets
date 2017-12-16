package com.fmi.edu.online.bus.tickets.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Bus")
public class Bus {

	@Id
	@Column(nullable = false, unique = true)
	private String id;

	@Column(nullable = false, updatable = true)
	private String line;

	Bus() {
		id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
}
