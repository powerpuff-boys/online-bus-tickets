package com.fmi.edu.online.bus.tickets.web.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fmi.edu.online.bus.tickets.model.TicketDto;

@Path("/tickets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TicketsResource {

	private static List<TicketDto> tickets = new ArrayList<>();
	
	@GET
	@Path("/{id}")
	public Response getTicket(@PathParam("id") String id){
		TicketDto result = tickets.stream().filter(ticket -> ticket.getId().equals(id)).findFirst().orElse(null);
		if (result == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	public Response getTickets(){
		return Response.ok().build();
	}
	
	@POST
	public Response createTicket(TicketDto ticket){
		tickets.add(ticket);
		return Response.created(URI.create("tickets/"+ ticket.getId())).entity(ticket).build();
	}
}
