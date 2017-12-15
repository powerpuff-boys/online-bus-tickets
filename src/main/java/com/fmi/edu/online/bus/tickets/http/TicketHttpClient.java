package com.fmi.edu.online.bus.tickets.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class TicketHttpClient {
	private static final String ENDPOINT = "rest-endpoint-goes-here";
	private HttpClient httpClient;

	public TicketHttpClient() {
		httpClient = HttpClientBuilder.create().build();
	}

	/* create http entity by giving it string entity(json string) */
	public int createTicket() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(ENDPOINT);
		// httpPost.setHeader();
		StringEntity stringEntity = new StringEntity("SOME_TICKET_JSON");

		// httpPost.setEntity();
		HttpResponse httpResponse = httpClient.execute(httpPost);
		return httpResponse.getStatusLine().getStatusCode();
	}

	// put - build URL and set it as endpoint

}
