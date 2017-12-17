package com.fmi.edu.online.bus.tickets.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.fmi.edu.online.bus.tickets.model.Bus;

public class BusDao {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bus-tickets");

	public Bus find(String id) {
		Bus result = null;
		try (AutoclosableEntityManager autoclosableEntityManager = new AutoclosableEntityManager(
				emf.createEntityManager())) {
			result = autoclosableEntityManager.find(Bus.class, id);
//			TypedQuery<Bus> query = autoclosableEntityManager.createNamedQuery("Select * from Bus where id = " + id, Bus.class);
//			result = query.getSingleResult();
		}
		return result;
	}
}
