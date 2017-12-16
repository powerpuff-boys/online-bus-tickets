package com.fmi.edu.online.bus.tickets.model.convertor;

import com.fmi.edu.online.bus.tickets.model.Ticket;
import com.fmi.edu.online.bus.tickets.model.TicketDto;

public class TicketConvertor {
	public static TicketDto convertToDto(Ticket ticket) {
		return new TicketDto(ticket);
	}

	public static Ticket convertToTicket(TicketDto ticketDto) {
		return new Ticket(ticketDto);
	}
}
