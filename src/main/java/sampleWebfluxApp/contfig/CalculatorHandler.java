package sampleWebfluxApp.contfig;

import java.util.function.BiFunction;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Service
public class CalculatorHandler {

	public Mono<ServerResponse> additionHandler(ServerRequest request){			
		return process(request, (a,b ) -> ServerResponse.ok().bodyValue(a+b));
	}
	
	public Mono<ServerResponse> subtractionHandler(ServerRequest request){					
		return process(request, (a,b ) -> ServerResponse.ok().bodyValue(a-b));	
	}
	
	public Mono<ServerResponse> multiplicationHandler(ServerRequest request){					
		return process(request, (a,b ) -> ServerResponse.ok().bodyValue(a*b));	
	}
	
	public Mono<ServerResponse> divisioHandler(ServerRequest request){					
		return process(request, (a,b ) -> 
			b != 0 ? ServerResponse.ok().bodyValue(a/b) :
				ServerResponse.badRequest().bodyValue("b conot bi 0"));
		}
	
	
	private Mono<ServerResponse> process(ServerRequest request,
			BiFunction<Integer, Integer, Mono<ServerResponse>>opLogic){
		
		int a = getValiue(request, "a");
		int b = getValiue(request, "b");
		return opLogic.apply(a,a);
	}
	
	
	private int getValiue(ServerRequest request, String key) {
		return Integer.parseInt(request.pathVariable(key));
	}
}
