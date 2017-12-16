package com.fmi.edu.online.bus.tickets.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class ControllerHttpClient {
	private static final String ACCEPT = "Accept";
	private static final String APPLICATION_JSON = "application/json";

	private HttpClient httpClient;

	public ControllerHttpClient() {
		httpClient = HttpClientBuilder.create().build();
	}

	public int checkTicket(String ticketId) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(HttpUtil.buildUrlWith(ticketId));
		httpGet.setHeader(ACCEPT, APPLICATION_JSON);

		HttpResponse httpResponse = httpClient.execute(httpGet);
		return httpResponse.getStatusLine().getStatusCode();
	}
}
