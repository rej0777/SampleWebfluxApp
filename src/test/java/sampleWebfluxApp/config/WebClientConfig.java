package sampleWebfluxApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

	
	@Bean
	public WebClient webClient() {
		return WebClient
				.builder()
				.baseUrl("http://localhost:8080")
				//.defaultHeader("Uname", "Upasword")
				//.filter(((clientRequest, exchangeFunction) -> sessionToken(clientRequest, exchangeFunction)  ))
				.filter(this::sessionToken)
				.build();				
	}
/*	
	private Mono<ClientResponse> sessionToken(ClientRequest rq, ExchangeFunction ex) {
		System.out.println("#### Generowanie Token");
		ClientRequest clientRequest = ClientRequest.from(rq).headers(h -> h.setBearerAuth("to-Jest-Tokej:) ")).build();
		return ex.exchange(clientRequest);
	}
	*/
	
	
private Mono<ClientResponse> sessionToken(ClientRequest rq, ExchangeFunction ex) {
	// autch -> basic or autch
	ClientRequest clientRequest = 
			rq.attribute("auth")
			.map(v -> v.equals("basic") ? wBasiceAuth(rq) : witch0Auth(rq))
			.orElse(rq);
	
	return ex.exchange(clientRequest);
}
	
	private ClientRequest wBasiceAuth(ClientRequest request) {
		return ClientRequest.from(request)
				.headers(h -> h.setBasicAuth("Uname","Upasword")).build();	
	}
	
	private ClientRequest witch0Auth(ClientRequest request) {
		return ClientRequest.from(request)
				.headers(h -> h.setBearerAuth("to-Jest-Token :):)")).build();
	}
}
