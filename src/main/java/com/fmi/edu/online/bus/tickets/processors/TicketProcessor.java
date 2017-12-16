package com.fmi.edu.online.bus.tickets.processors;

import java.sql.Date;
import java.time.Instant;

import com.fmi.edu.online.bus.tickets.dao.TicketDtoDao;
import com.fmi.edu.online.bus.tickets.model.Ticket;
import com.fmi.edu.online.bus.tickets.model.TicketDto;

public class TicketProcessor {

	// @Inject
	private TicketDtoDao ticketDtoDAO = new TicketDtoDao();

	public void createTicket(Ticket ticket) {
		// if(isValidInformation(busId,createdOn)){
		//
		// }
		// TODO validation
		TicketDto ticketDto = new TicketDto(ticket);
		ticketDtoDAO.create(ticketDto);
	}

	public void updateTicket(String ticketId) {
		if (!isValidTicket(ticketId)) {

		}
		ticketDtoDAO.update(ticketId);
		// ticketDAO.update(ticket);
	}

	public TicketDto getTicket(String id) {
		return ticketDtoDAO.get(id);
	}

	public void deleteTicket(String id) {
		ticketDtoDAO.delete(id);
	}

	private boolean isValidTicket(String ticketId) {
		return false;
		// return ticketDAO.getTicket(ticket) != null;
	}

	public static void main(String[] args) {
		new TicketProcessor().createTicket(new Ticket("buska", Date.from(Instant.now()), false));
	}
}
