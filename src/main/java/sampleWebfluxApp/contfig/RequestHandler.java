package sampleWebfluxApp.contfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sampleWebfluxApp.dto.InputFailedValidationResponse;
import sampleWebfluxApp.dto.MultiplyRequestDto;
import sampleWebfluxApp.dto.Response;
import sampleWebfluxApp.exception.InputValidationException;
import sampleWebfluxApp.service.ReactiveMathService;

@Service
public class RequestHandler {

	@Autowired
	private ReactiveMathService matchService;

	public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {

		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		Mono<Response> responseMono = this.matchService.findSquare(input);
		return ServerResponse
				.ok()
				.body(responseMono, Response.class);
	}

	public Mono<ServerResponse> tableHandler(ServerRequest serverRequest) {

		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		Flux<Response> responseFlex = this.matchService.multiplicationTable(input);
		return ServerResponse
				.ok()
				.body(responseFlex, Response.class);
	}

	public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest) {

		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		Flux<Response> responseFlex = this.matchService.multiplicationTable(input);
		return ServerResponse
				.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(responseFlex, Response.class);
	}

	public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest) {

		Mono<MultiplyRequestDto> requestDtoMono = serverRequest.bodyToMono(MultiplyRequestDto.class);
		Mono<Response> responseMono = this.matchService.multiply(requestDtoMono);		
		return ServerResponse
				.ok()
				.body(responseMono, Response.class);
		// POST http://localhost:8080/router/multiplay
		//{ "first": 10, "second":2}
	}
	
	public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest) {

		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		if(input <10|| input >20) {
			return Mono.error(new InputValidationException(input)); 
		}
		Mono<Response> responseMono = this.matchService.findSquare(input);
		return ServerResponse
				.ok()
				.body(responseMono, Response.class);
	}
}
