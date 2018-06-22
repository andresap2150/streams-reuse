package com.adaa;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;

import static org.asynchttpclient.Dsl.asyncHttpClient;

public class ServiceConsumer {
	
	public static void main(String...strings ) {
		Pattern pat = Pattern.compile("\\n");
		//AsyncHttpClient asyncHttpClient = asyncHttpClient();
		CompletableFuture<Stream<String>> csv = asyncHttpClient()
				.prepareGet("http://api.worldweatheronline.com/premium/v1/past-weather.ashx?q=37.017,-7.933&date=2018-03-01&enddate=2018-03-31&tp=24&format=csv&key=dc1639184b4745ddab5191857182206")
				.execute()
				.toCompletableFuture()
				.thenApply(Response::getResponseBody)
				.thenApply(pat::splitAsStream);		
		System.out.println(csv);
	}
}
