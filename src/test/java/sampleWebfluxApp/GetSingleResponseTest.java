package sampleWebfluxApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sampleWebfluxApp.dto.Response;

public class GetSingleResponseTest extends BaseTest {

	@Autowired
	public WebClient webClient;


	@Test
	public void blockTest() {
		
		Response response= this.webClient
		.get()
		.uri("reactiveMath/square/{input}", 5)
		.retrieve()
		.bodyToMono(Response.class)//Mono clas response
		.block();
		
		System.out.println("##"+ response);
	}
	

	@Test
	public void stepVeriffierTest() {
		
		Mono<Response> response= this.webClient
		.get()
		.uri("reactiveMath/square/{input}", 5)
		.retrieve()
		.bodyToMono(Response.class);
		//.block();
		
		StepVerifier.create(response)
		.expectNextMatches(r -> r.getOutput() == 25)
		.verifyComplete();
	}
}
