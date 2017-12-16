package com.fmi.edu.online.bus.tickets.processors;

import java.util.UUID;

import com.fmi.edu.online.bus.tickets.dao.TicketDtoDao;
import com.fmi.edu.online.bus.tickets.model.TicketDto;

public class TicketProcessor {

	// @Inject
	private TicketDtoDao ticketDtoDAO = new TicketDtoDao();

	public void createTicket(TicketDto ticketDto) {
		// if(isValidInformation(busId,createdOn)){
		//
		// }
		// TODO validation
		ticketDto.setId(UUID.randomUUID().toString());
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

}
