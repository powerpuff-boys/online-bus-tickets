package com.fmi.edu.online.bus.tickets.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fmi.edu.online.bus.tickets.model.Ticket;
import com.fmi.edu.online.bus.tickets.model.TicketDto;


public class TicketDtoDao {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bus-tickets");
	EntityManager em = emf.createEntityManager();
	
	public void create(TicketDto ticket) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(ticket);
		em.getTransaction().commit();
	}

	public void update(String ticketId) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.find(TicketDto.class, ticketId).setChecked(true);
		em.getTransaction().commit();
	}

	public TicketDto get(String id) {
		// TODO Auto-generated method stub
		return em.find(TicketDto.class, id);
	}

	public void delete(String id) {
		em.getTransaction().begin();
		em.remove(em.find(TicketDto.class, id));
		em.getTransaction().commit();
	}

	public List<Ticket> getTickets() {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		TypedQuery<Ticket> query = em.createNamedQuery("SELECT * FROM bustickets", Ticket.class);
		return Collections.unmodifiableList(query.getResultList());
	}
}
