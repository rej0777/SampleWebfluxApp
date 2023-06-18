package sampleWebfluxApp.webClientTest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;
import sampleWebfluxApp.controller.ReactiveMathController;
import sampleWebfluxApp.dto.MultiplyRequestDto;
import sampleWebfluxApp.dto.Response;
import sampleWebfluxApp.service.ReactiveMathService;

@WebFluxTest(ReactiveMathController.class)
public class PostControllerTest {
	
	@Autowired
	private WebTestClient webTestClient; 

	@MockBean
	private ReactiveMathService reactiveMathService;
	
	   @Test
	    public void postTest(){
	        Mockito.when(reactiveMathService.multiply(Mockito.any()))
	        .thenReturn(Mono.just(new Response(1)));

	        this.webTestClient
	                .post()
	                .uri("/reactiveMath/multiply")
	                .accept(MediaType.APPLICATION_JSON)
	                .headers(h -> h.setBasicAuth("username", "password"))
	                .headers(h -> h.set("somekey", "somevalue"))
	                .bodyValue(new MultiplyRequestDto())
	                .exchange()
	                .expectStatus().is2xxSuccessful();
	    }

	
}
