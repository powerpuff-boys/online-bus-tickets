package com.fmi.edu.online.bus.tickets.web.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fmi.edu.online.bus.tickets.model.Ticket;

@Path("/tickets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TicketsResource {

	private static List<Ticket> tickets = new ArrayList<>();
	
	public Response getTicket(@PathParam("id") String id){
		Ticket result = tickets.stream().filter(ticket -> ticket.getId().equals(id)).findFirst().orElse(null);
		if (result == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.OK).entity(result).build();
	}
}
