package com.fmi.edu.online.bus.tickets.processors;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fmi.edu.online.bus.tickets.dao.TicketDtoDao;
import com.fmi.edu.online.bus.tickets.model.TicketDto;
import com.fmi.edu.online.bus.tickets.model.convertor.TicketConvertor;

public class TicketProcessor {

	private TicketDtoDao ticketDtoDAO = new TicketDtoDao();
		
	public void createTicket(TicketDto ticketDto) {
		try {
			ticketDto.setId(UUID.randomUUID().toString());
			ticketDtoDAO.create(ticketDto);
		} catch (Exception e) {
			throw e;
		}
	}

	public void updateTicket(String ticketId) {
		if (!isValidTicket(ticketId)) {

		}
		ticketDtoDAO.update(ticketId);
	}

	public List<TicketDto> getAllExpiredTickets() {
		return ticketDtoDAO.getTickets().stream()
				.filter(ticket -> Date.from(Instant.now()).after(ticket.getExpiresOn()))
				.map(ticket -> TicketConvertor.convertToDto(ticket))
				.collect(Collectors.toList());
	}

	public TicketDto getTicket(String id) {
		return ticketDtoDAO.get(id);
	}

	public void deleteTicket(String id) {
		ticketDtoDAO.delete(id);
	}

	private boolean isValidTicket(String ticketId) {
		// BusId -> correct
		return false;
	}

}
