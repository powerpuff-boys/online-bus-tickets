package com.fmi.edu.online.bus.tickets.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fmi.edu.online.bus.tickets.web.resources.TicketsResource;

@ApplicationPath("/rest/*")
public class TicketApplication extends Application{

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(TicketsResource.class);
		return classes;
	}
}
