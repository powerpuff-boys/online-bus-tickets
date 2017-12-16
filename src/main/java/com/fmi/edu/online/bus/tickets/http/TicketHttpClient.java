package com.fmi.edu.online.bus.tickets.http;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fmi.edu.online.bus.tickets.model.TicketDto;
import com.google.gson.Gson;

public class TicketHttpClient {
	private static final String ACCEPT = "Accept";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";
	private static final String ENDPOINT = "http://localhost:8080/bus-tickets-0.0.1-SNAPSHOT/rest/tickets";

	private HttpClient httpClient;
	private HttpResponse httpResponse;
	private StringEntity stringEntity;
	private Gson gson;

	public TicketHttpClient() {
		httpClient = HttpClientBuilder.create().build();
		gson = new Gson();
	}

	public int createTicket(TicketDto ticketDto) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(ENDPOINT);
		httpPost.setHeader(CONTENT_TYPE, APPLICATION_JSON);
		httpPost.setHeader(ACCEPT, APPLICATION_JSON);

		stringEntity = new StringEntity(gson.toJson(ticketDto));
		httpPost.setEntity(stringEntity);

		httpResponse = httpClient.execute(httpPost);
		return httpResponse.getStatusLine().getStatusCode();
	}

	public int updateTicket(String ticketId, TicketDto ticketDto) throws ClientProtocolException, IOException {
		HttpPut httpPut = new HttpPut(buildUrlWith(ticketId));
		httpPut.setHeader(CONTENT_TYPE, APPLICATION_JSON);

		stringEntity = new StringEntity(gson.toJson(ticketDto));
		httpPut.setEntity(stringEntity);

		httpResponse = httpClient.execute(httpPut);
		return httpResponse.getStatusLine().getStatusCode();
	}

	public TicketDto getTicketBy(String ticketId) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(buildUrlWith(ticketId));
		httpGet.setHeader(ACCEPT, APPLICATION_JSON);

		httpResponse = httpClient.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		String entityString = IOUtils.toString(entity.getContent(), "UTF-8");
		return gson.fromJson(entityString, TicketDto.class);
	}

	private static String buildUrlWith(String ticketId) {
		return new StringBuilder(ENDPOINT).append("/").append(ticketId).toString();
	}
	
}
