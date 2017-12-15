package com.fmi.edu.online.bus.tickets.web.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fmi.edu.online.bus.tickets.model.Ticket;

@Path("/tickets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TicketsResource {

	private static List<Ticket> tickets = new ArrayList<>();
	
	public Ticket getTicket(@PathParam("id") String id){
		return null;
	}
}
