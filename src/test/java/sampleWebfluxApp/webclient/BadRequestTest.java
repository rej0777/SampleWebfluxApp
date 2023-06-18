package sampleWebfluxApp.webclient;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sampleWebfluxApp.dto.Response;

public class BadRequestTest extends BaseTest {

	
	@Autowired
	private WebClient webClient;
	
	
	@Test
	public void badRequestTest() {
		
		Mono<Response> responseMono = this.webClient
		.get()
		.uri("reactiveMath/square/{input}/throw", 5)
		.retrieve()
		.bodyToMono(Response.class)
		.doOnNext(System.out::print)
		.doOnError(System.out::print);
		 
		 
		StepVerifier.create(responseMono)
		.verifyError(WebClientResponseException.class);
	}
	
	
}
