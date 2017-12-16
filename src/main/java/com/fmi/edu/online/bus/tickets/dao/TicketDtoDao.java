package com.fmi.edu.online.bus.tickets.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		em.find(TicketDto.class,ticketId).setChecked(false);
		em.getTransaction().commit();
	}

	public TicketDto get(String id) {
		// TODO Auto-generated method stub
		return em.find(TicketDto.class, id);
	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.remove(em.find(TicketDto.class, id));
		em.getTransaction().commit();
	}
}
