package sampleWebfluxApp.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sampleWebfluxApp.dto.Response;

public class GetMultiResponseTest extends BaseTest {

	@Autowired
	private WebClient webClient;
	
	
	@Test
	public void fluxTest() {
		
		Flux<Response> responseFlex= this.webClient
		.get()
		.uri("math/table/{input}", 5)
		.retrieve()
		.bodyToFlux(Response.class)
		.doOnNext(System.out::println);
		
		StepVerifier.create(responseFlex)
		.expectNextCount(10)
		.verifyComplete();
	}
	
	@Test
	public void fluxStreamTest() {
		
		Flux<Response> responseFlex= this.webClient
		.get()
		.uri("reactiveMath/table/{input}/stream", 5)
		.retrieve()
		.bodyToFlux(Response.class)
		.doOnNext(System.out::println);
		
		StepVerifier.create(responseFlex)
		.expectNextCount(10)
		.verifyComplete();
	}
	
}
