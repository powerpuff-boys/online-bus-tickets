package com.fmi.edu.online.bus.tickets.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fmi.edu.online.bus.tickets.model.Bus;

public class BusDao {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bus-tickets");
	
	public Bus find(String id){
		Bus result = null;
		try (AutoclosableEntityManager autoclosableEntityManager = new AutoclosableEntityManager(
				emf.createEntityManager())) {
			result = autoclosableEntityManager.find(Bus.class, id);
		}
		return result;
	}
}
