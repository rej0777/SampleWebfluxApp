package sampleWebfluxApp.webClientTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sampleWebfluxApp.dto.Response;

@SpringBootTest
@AutoConfigureWebTestClient
public class WebTestClient1 {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	public void stepVeriffierTest() {
		
		Flux<Response> response= this.webTestClient
		.get()
		.uri("/reactiveMath/square/{input}", 5)///:) :) /react
		.exchange().expectStatus().is2xxSuccessful()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.returnResult(Response.class)
		.getResponseBody();
		//.retrieve()
		//.bodyToMono(Response.class);
		//.block();
		
		StepVerifier.create(response)
		.expectNextMatches(r -> r.getOutput() == 25)
		.verifyComplete();
	}
	
	
	@Test
	public void fluentAssertionTest() {
		this.webTestClient
		.get()
		.uri("/reactiveMath/square/{input}", 5)///:) :) /react
		.exchange().expectStatus().is2xxSuccessful()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody(Response.class)
		.value(r -> Assertions.assertThat(r.getOutput()).isEqualTo(25));
		//.retrieve()
		//.bodyToMono(Response.class);
		//.block();
		
		
	}
	
}
