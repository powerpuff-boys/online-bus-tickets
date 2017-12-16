package com.fmi.edu.online.bus.tickets.http;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import org.apache.http.util.EntityUtils;

import com.fmi.edu.online.bus.tickets.model.Ticket;
import com.fmi.edu.online.bus.tickets.model.TicketDto;
import com.fmi.edu.online.bus.tickets.model.convertor.TicketConvertor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TicketHttpClient {
	private static final String ENCODING_UTF = "UTF-8";
	private static final String ACCEPT = "Accept";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";
	private static final String ENDPOINT = "http://localhost:8080/bus-tickets/rest/tickets";

	private HttpClient httpClient;
	private HttpResponse httpResponse;
	private StringEntity stringEntity;
	private Gson gson;
	private TicketDto ticketDto;

	public TicketHttpClient() {
		httpClient = HttpClientBuilder.create().build();
		gson = new GsonBuilder().create();
	}

	public Ticket createTicket(Ticket ticket) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(ENDPOINT);
		httpPost.setHeader(CONTENT_TYPE, APPLICATION_JSON);
		httpPost.setHeader(ACCEPT, APPLICATION_JSON);

		stringEntity = new StringEntity(gson.toJson(TicketConvertor.convertToDto(ticket)));
		httpPost.setEntity(stringEntity);

		httpResponse = httpClient.execute(httpPost);
		String string = IOUtils.toString(httpResponse.getEntity().getContent(), ENCODING_UTF);

		return TicketConvertor.convertToTicket(gson.fromJson(string, TicketDto.class));
	}

	public Ticket updateTicket(String ticketId, Ticket ticket) throws ClientProtocolException, IOException {
		HttpPut httpPut = new HttpPut(buildUrlWith(ticketId));
		httpPut.setHeader(CONTENT_TYPE, APPLICATION_JSON);
		httpPut.setHeader(ACCEPT, APPLICATION_JSON);

		stringEntity = new StringEntity(gson.toJson(TicketConvertor.convertToDto(ticket)));
		httpPut.setEntity(stringEntity);

		httpResponse = httpClient.execute(httpPut);
		ticketDto = gson.fromJson(EntityUtils.toString(httpResponse.getEntity()), TicketDto.class);
		return TicketConvertor.convertToTicket(ticketDto);
	}

	public Ticket getTicketBy(String ticketId) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(buildUrlWith(ticketId));
		httpGet.setHeader(ACCEPT, APPLICATION_JSON);

		httpResponse = httpClient.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		String entityString = IOUtils.toString(entity.getContent(), ENCODING_UTF);
		return gson.fromJson(entityString, Ticket.class);
	}

	private static String buildUrlWith(String ticketId) {
		return new StringBuilder(ENDPOINT).append("/").append(ticketId).toString();
	}

	public static void main(String[] args) throws ClientProtocolException, IOException, ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		System.out.println((new TicketHttpClient().createTicket(new Ticket("bus-id1", calendar.getTime(), false))));

	}
}
