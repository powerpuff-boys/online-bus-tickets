package com.fmi.edu.online.bus.tickets.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fmi.edu.online.bus.tickets.model.Ticket;
import com.google.gson.Gson;

public class TicketHttpClient {
	private static final String ACCEPT = "accept";
	private static final String CONTENT_TYPE = "content-type";
	private static final String APPLICATION_JSON = "application/json";
	private static final String ENDPOINT = "rest-endpoint-goes-here";

	private HttpClient httpClient;
	private HttpResponse httpResponse;
	private StringEntity stringEntity;
	private Gson gson;

	public TicketHttpClient() {
		httpClient = HttpClientBuilder.create().build();
		gson = new Gson();
	}

	public int createTicket(Ticket ticket) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(ENDPOINT);
		httpPost.setHeader(CONTENT_TYPE, APPLICATION_JSON);

		stringEntity = new StringEntity(gson.toJson(ticket));
		httpPost.setEntity(stringEntity);

		httpResponse = httpClient.execute(httpPost);
		return httpResponse.getStatusLine().getStatusCode();
	}

	public int updateTicket(String ticketId, Ticket ticket) throws ClientProtocolException, IOException {
		HttpPut httpPut = new HttpPut(buildUrlWith(ticketId));
		httpPut.setHeader(CONTENT_TYPE, APPLICATION_JSON);

		stringEntity = new StringEntity(gson.toJson(ticket));
		httpPut.setEntity(stringEntity);

		httpResponse = httpClient.execute(httpPut);
		return httpResponse.getStatusLine().getStatusCode();
	}

	public Ticket getTicketBy(String ticketId) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(buildUrlWith(ticketId));
		httpGet.setHeader(ACCEPT, APPLICATION_JSON);

		httpResponse = httpClient.execute(httpGet);
		String entityAsString = EntityUtils.toString(httpResponse.getEntity());
		return gson.fromJson(entityAsString, Ticket.class);
	}

	private static String buildUrlWith(String ticketId) {
		return new StringBuilder(ENDPOINT).append("/").append(ticketId).toString();
	}
}
