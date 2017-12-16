package com.fmi.edu.online.bus.tickets.web.resources;

import java.net.URI;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
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

import com.fmi.edu.online.bus.tickets.Constants;
import com.fmi.edu.online.bus.tickets.Messages;
import com.fmi.edu.online.bus.tickets.model.TicketDto;
import com.fmi.edu.online.bus.tickets.processors.PaymentApi;
import com.fmi.edu.online.bus.tickets.processors.PaymentDetails;
import com.fmi.edu.online.bus.tickets.processors.PaymentExecutor;
import com.fmi.edu.online.bus.tickets.processors.PaymentResult;
import com.fmi.edu.online.bus.tickets.processors.TicketProcessor;
import com.fmi.edu.online.bus.tickets.processors.UserPaymentDetails;

@Path("/tickets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TicketsResource {

	private TicketProcessor ticketProcessor;

	private PaymentApi paymentApi;

	public TicketsResource() {
		ticketProcessor = new TicketProcessor();
		paymentApi =  new PaymentApi(new PaymentExecutor());
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
	public Response createTicket(TicketDto ticketDto, HttpServletRequest req) {
		try {
			String authorizationHeader = getAuthorizationHeader(req);
			
			UserPaymentDetails userPaymentDetails = getUserPaymentDetails(authorizationHeader);
			
			payTicket(userPaymentDetails);
			
			ticketProcessor.createTicket(ticketDto);
			return Response.created(URI.create(Constants.DEFAULT_TICKETS_API + ticketDto.getId())).entity(ticketDto).build();
		} catch (IllegalArgumentException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	private String getAuthorizationHeader(HttpServletRequest req) {
		String authorizationHeader = req.getHeader(Constants.AUTHENTICATION_HEADER);
		validateAuthorizationHeaader(authorizationHeader);
		return authorizationHeader;
	}

	private void payTicket(UserPaymentDetails userPaymentDetails) {
		PaymentDetails payment = paymentApi.createPayment(userPaymentDetails);
		if (payment.getPaymentResult() != PaymentResult.DONE) {
			throw new IllegalArgumentException(MessageFormat.format(Messages.COULD_NOT_PAY_TICKET, payment.getPaymentMessage()));
		}
	}

	private UserPaymentDetails getUserPaymentDetails(String authorizationHeader) {
		String[] authHeaderSplitted = authorizationHeader.split(" ");
		return UserPaymentDetails.getUserDetailsParser().parse(authHeaderSplitted[1]);
	}

	private void validateAuthorizationHeaader(String authorizationHeader) {

		if (authorizationHeader == null) {
			throw new IllegalArgumentException(Messages.AUTHORIZATION_HEADER_IS_NOT_PROVIDED);
		}

		if (!authorizationHeader.startsWith(Constants.BASIC_AUTHENTICATION_KEYWORD)) {
			throw new IllegalArgumentException(Messages.THE_AUTHORIZATION_HEADER_MUST_BE_BASIC_AUTHENTICATION);
		}
	}

	@PUT
	@Path("/{id}")
	public Response updateTicket(@PathParam("id") String id, TicketDto ticket) {
		TicketDto result = findInternal(id);
		if (result == null) {
			return Response.status(Status.NOT_FOUND)
					.entity(MessageFormat.format(Messages.TICKET_WITH_ID_0_DOES_NOT_EXIST, id)).build();
		}
		ticketProcessor.updateTicket(id);
		return Response.created(URI.create(Constants.DEFAULT_TICKETS_API + ticket.getId())).entity(result).build();
	}
}
