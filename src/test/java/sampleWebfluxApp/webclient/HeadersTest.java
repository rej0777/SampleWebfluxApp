package sampleWebfluxApp.webclient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sampleWebfluxApp.dto.MultiplyRequestDto;
import sampleWebfluxApp.dto.Response;


public class HeadersTest extends BaseTest {
	/**/
	
	@Autowired
	private WebClient webClient;

	@Test
	public void headersTest() {
		
	Mono<Response> responseMono =	this.webClient
		.post()
		.uri("reactiveMath/multiply")
		.bodyValue(requestDto(4,8))
		//.headers(h -> h.set("MyKeay1", "MyVal1"))
		.headers(h -> h.setBasicAuth("Uname", "Upasword"))
		.retrieve()
		.bodyToMono(Response.class)
		.doOnNext(System.out::println);
		
		StepVerifier.create(responseMono)
		.expectNextCount(1)
		.verifyComplete();
	}

	
	private MultiplyRequestDto requestDto(int a, int b) {
		
		MultiplyRequestDto dto = new MultiplyRequestDto();
		dto.setFirst(a);
		dto.setSecond(b);
		return dto;
	}
		
}
