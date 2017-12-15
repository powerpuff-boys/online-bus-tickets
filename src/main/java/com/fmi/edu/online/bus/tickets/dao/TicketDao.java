package com.fmi.edu.online.bus.tickets.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fmi.edu.online.bus.tickets.model.Ticket;


public class TicketDao {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bus-tickets");
	EntityManager em = emf.createEntityManager();
	
	public void create(Ticket ticket) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist(ticket);
		em.getTransaction().commit();
	}

	public void update(String ticketId) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.find(Ticket.class,ticketId).setChecked(false);
		em.getTransaction().commit();
	}

	public Ticket get(String id) {
		// TODO Auto-generated method stub
		return em.find(Ticket.class, id);
	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.remove(em.find(Ticket.class, id));
		em.getTransaction().commit();
	}
}
