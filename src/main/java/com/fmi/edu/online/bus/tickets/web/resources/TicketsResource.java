package com.fmi.edu.online.bus.tickets.web.resources;

import java.net.URI;
import java.text.MessageFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fmi.edu.online.bus.tickets.model.TicketDto;
import com.fmi.edu.online.bus.tickets.processors.TicketProcessor;

@Path("/tickets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TicketsResource {

	private TicketProcessor ticketProcessor;

	public TicketsResource() {
		ticketProcessor = new TicketProcessor();
	}

	@GET
	@Path("/{id}")
	public Response getTicket(@PathParam("id") String id) {
		TicketDto result = findInternal(id);
		if (result == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(Status.OK).entity(result).build();
	}

	private TicketDto findInternal(String id) {
		return ticketProcessor.getTicket(id);
	}

	@POST
	public Response createTicket(TicketDto ticketDto) {
		ticketProcessor.createTicket(ticketDto);
		return Response.created(URI.create("tickets/" + ticketDto.getId())).entity(ticketDto).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateTicket(@PathParam("id") String id, TicketDto ticket) {
		TicketDto result = findInternal(id);
		if (result == null) {
			return Response.status(Status.NOT_FOUND)
					.entity(MessageFormat.format("Ticket with id {0} does not exist", id)).build();
		}
		result.setBusId(ticket.getBusId());
		result.setChecked(ticket.isChecked());
		result.setCreatedOn(ticket.getCreatedOn());
		result.setExpiresOn(ticket.getExpiresOn());
		ticketProcessor.deleteTicket(result.getId());
		ticketProcessor.createTicket(result);
		return Response.created(URI.create("tickets/" + ticket.getId())).entity(result).build();
	}
}
