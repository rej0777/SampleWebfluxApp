package sampleWebfluxApp.contfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sampleWebfluxApp.dto.Response;
import sampleWebfluxApp.service.ReactiveMathService;

@Service
public class RequestHandler {

	@Autowired
	private ReactiveMathService matchService;
	
	public Mono<ServerResponse>squareHandler(ServerRequest serverRequest){
		
		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		Mono<Response> responseMono = this.matchService.findSquare(input);
		return ServerResponse.ok().body(responseMono, Response.class);		
	}
	
public Mono<ServerResponse>tableHandler(ServerRequest serverRequest){
		
		int input = Integer.parseInt(serverRequest.pathVariable("input"));
		Flux<Response> responseFlex = this.matchService.multiplicationTable(input);
		return ServerResponse.ok().body(responseFlex, Response.class);		
	}

public Mono<ServerResponse>tableStreamHandler(ServerRequest serverRequest){
	
	int input = Integer.parseInt(serverRequest.pathVariable("input"));
	Flux<Response> responseFlex = this.matchService.multiplicationTable(input);
	return ServerResponse.ok()
			.contentType(MediaType.TEXT_EVENT_STREAM)
			.body(responseFlex, Response.class);		
}
}
