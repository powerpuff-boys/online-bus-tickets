package com.fmi.edu.online.bus.tickets.processors;

import java.util.Date;

import com.fmi.edu.online.bus.tickets.dao.TicketDao;
import com.fmi.edu.online.bus.tickets.model.Ticket;

public class TicketProcessor {
	
//	@Inject
	private TicketDao ticketDAO = new TicketDao();
	
	public void createTicket(String busId, Date createdOn, boolean isChecked) {
//		if(isValidInformation(busId,createdOn)){
//			
//		}
		//TODO validation
		Ticket ticket = new Ticket(busId, createdOn, isChecked);
		ticketDAO.create(ticket);
	}
	
	public void updateTicket(String ticketId){
		if(!isValidTicket(ticketId)){
			
		}
		ticketDAO.update(ticketId);
//		ticketDAO.update(ticket);
	}
	
	public Ticket getTicket(String id){
		return ticketDAO.get(id);
	}
	
	public void deleteTicket(String id){
		ticketDAO.delete(id);
	}
	
	

	private boolean isValidTicket(String ticketId) {
		return false;
//		return ticketDAO.getTicket(ticket) != null;
	}
	
	public static void main(String[] args) {
		new TicketProcessor().deleteTicket("11");
	}
}
