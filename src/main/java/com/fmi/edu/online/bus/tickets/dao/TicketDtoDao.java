package com.fmi.edu.online.bus.tickets.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fmi.edu.online.bus.tickets.model.Ticket;
import com.fmi.edu.online.bus.tickets.model.TicketDto;

public class TicketDtoDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bus-tickets");

	public void create(TicketDto ticket) {
		try (AutoclosableEntityManager autoclosableEntityManager = new AutoclosableEntityManager(
				emf.createEntityManager())) {
			autoclosableEntityManager.getTransaction().begin();
			autoclosableEntityManager.persist(ticket);
			autoclosableEntityManager.getTransaction().commit();
		}
	}

	public void update(String ticketId) {
		try (AutoclosableEntityManager autoclosableEntityManager = new AutoclosableEntityManager(
				emf.createEntityManager())) {
			autoclosableEntityManager.getTransaction().begin();
			autoclosableEntityManager.find(TicketDto.class, ticketId).setChecked(true);
			autoclosableEntityManager.getTransaction().commit();
		}
	}

	public TicketDto get(String id) {
		TicketDto ticketDto = null;
		try (AutoclosableEntityManager autoclosableEntityManager = new AutoclosableEntityManager(
				emf.createEntityManager())) {
			ticketDto = autoclosableEntityManager.find(TicketDto.class, id);
		}
		return ticketDto;
	}

	public void delete(String id) {
		try (AutoclosableEntityManager autoclosableEntityManager = new AutoclosableEntityManager(
				emf.createEntityManager())) {
			autoclosableEntityManager.getTransaction().begin();
			autoclosableEntityManager.remove(autoclosableEntityManager.find(TicketDto.class, id));
			autoclosableEntityManager.getTransaction().commit();
		}
	}

	public List<Ticket> getTickets() {
		try (AutoclosableEntityManager autoclosableEntityManager = new AutoclosableEntityManager(
				emf.createEntityManager())) {
			autoclosableEntityManager.getTransaction().begin();
			TypedQuery<Ticket> query = autoclosableEntityManager.createNamedQuery("SELECT * FROM bustickets",
					Ticket.class);
			return Collections.unmodifiableList(query.getResultList());
		}
	}

}
