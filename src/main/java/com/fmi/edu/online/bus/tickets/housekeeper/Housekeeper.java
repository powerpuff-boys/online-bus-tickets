package com.fmi.edu.online.bus.tickets.housekeeper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.fmi.edu.online.bus.tickets.processors.TicketProcessor;

public class Housekeeper extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		final TicketProcessor ticketProcessor = new TicketProcessor();

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				ticketProcessor.getAllExpiredTickets().forEach(ticket -> ticketProcessor.deleteTicket(ticket.getId()));
			}
		}, 3, 3, TimeUnit.MINUTES);
	}

}
