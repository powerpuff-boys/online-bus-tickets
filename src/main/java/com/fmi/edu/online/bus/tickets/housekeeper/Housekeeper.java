package com.fmi.edu.online.bus.tickets.housekeeper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.fmi.edu.online.bus.tickets.processors.TicketProcessor;

public class Housekeeper extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

		ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				private TicketProcessor ticketProcessor = new TicketProcessor();
			}
		}, 3, 3, TimeUnit.MINUTES);
	}

}
