package com.fmi.edu.online.bus.tickets.http;

public final class HttpUtil {
	public static final String ENDPOINT = "http://localhost:8080/bus-tickets/rest/tickets";

	public static String buildUrlWith(String ticketId) {
		return new StringBuilder(ENDPOINT).append("/").append(ticketId).toString();
	}
}
