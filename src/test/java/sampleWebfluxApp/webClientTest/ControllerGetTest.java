package sampleWebfluxApp.webClientTest;

import java.time.Duration;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sampleWebfluxApp.controller.ReactiveMathController;
import sampleWebfluxApp.controller.ParamQueryController;
import sampleWebfluxApp.dto.Response;
import sampleWebfluxApp.service.MatchService;
import sampleWebfluxApp.service.ReactiveMathService;

@WebFluxTest(controllers = {ReactiveMathController.class, ParamQueryController.class})
@Import(MatchService.class)
public class ControllerGetTest {



//	@MockBean
//	MatchController matchController;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private ReactiveMathService reactiveMathService;
	
	@Test
	public void flunetAssertionTestSingle() {
		
		Mockito.when(reactiveMathService.findSquare(Mockito.anyInt())).thenReturn(Mono.just(new Response(25)));
		//Mockito.when(reactiveMathService.findSquare(Mockito.anyInt())).thenReturn(Mono.empty());
		
	
		this.webTestClient
        .get()
        .uri("/reactiveMath/square/{input}", 5)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody(Response.class)
        .value(r -> Assertions.assertThat(r.getOutput()).isEqualTo(25));//-1
	}
	
	@Test
	public void flunetAssertionTestMulti() {
		
		Flux<Response> flux = Flux.range(1, 3).map(Response::new);
		
		Mockito.when(reactiveMathService.multiplicationTable(Mockito.anyInt())).thenReturn(flux);
	
		this.webTestClient
		.get()
		.uri("/reactiveMath/table/{imput}", 5)///:) :) /react
		.exchange().expectStatus().is2xxSuccessful()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)		
		.expectBodyList(Response.class)
		.hasSize(3);
	}
	
	@Test
	public void flunetAssertionTestStreaming() {
		
		Flux<Response> flux = Flux.range(1, 3)
				.map(Response::new)
				.delayElements(Duration.ofMillis(100));
		
		Mockito.when(reactiveMathService.multiplicationTable(Mockito.anyInt()))
		.thenReturn(flux);
	
		this.webTestClient
		.get()
		.uri("/reactiveMath/table/{imput}/stream", 5)///:) :) /react
		.exchange().expectStatus().is2xxSuccessful()
		.expectHeader().contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM)	
		.expectBodyList(Response.class)
		.hasSize(3);
	}
	
	 @Test
	    public void paramsTest(){

	        Map<String, Integer> map = Map.of(
	                "count", 10,
	                "page", 20
	        );

	        this.webTestClient
	                .get()
	                .uri(b -> b.path("/job/serch").query("count={count}&page={page}").build(map))
	                .exchange()
	                .expectStatus().is2xxSuccessful()
	                .expectBodyList(Integer.class)
	                .hasSize(2).contains(10, 20);
	    }
}
