package sampleWebfluxApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sampleWebfluxApp.dto.InputFailedValidationResponse;
import sampleWebfluxApp.dto.Response;

public class ExchangeTest extends BaseTest {

	
	@Autowired
	private WebClient webClient;
	
	
	@Test
	public void badRequestTest() {
		
		Mono<Object> responseMono = this.webClient
		.get()
		.uri("reactiveMath/square/{input}/throw", 5)
		.exchangeToMono(this::exchange)		
		.doOnNext(System.out::print)
		.doOnError(System.out::print);
		 
		 
		StepVerifier.create(responseMono)
		.expectNextCount(1).verifyComplete();
	}
	
	private Mono<Object> exchange (ClientResponse cr){
		if (cr.statusCode() == HttpStatusCode.valueOf(400) ) {
			return cr.bodyToMono(InputFailedValidationResponse.class);
		} else {
			return cr.bodyToMono(Response.class);
		}
	}
	
}
