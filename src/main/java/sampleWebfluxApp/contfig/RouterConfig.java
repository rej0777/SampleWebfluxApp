package sampleWebfluxApp.contfig;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import sampleWebfluxApp.dto.InputFailedValidationResponse;
import sampleWebfluxApp.exception.InputValidationException;

@Configuration
public class RouterConfig {

	@Autowired
	private RequestHandler requestHandler;

	/*		
	@Bean
	public RouterFunction<ServerResponse> serverResponseRouterFunction(){
		return RouterFunctions.route()
				.GET("router/square/{input}", requestHandler::squareHandler)
				.GET("router/table/{input}", requestHandler::tableHandler)
				.GET("router/table/{input}/stream", requestHandler::tableStreamHandler)
				.POST("router/multiplay", requestHandler::multiplyHandler)
				.GET("router/square/{input}/validation",requestHandler::squareHandlerWithValidation)
				.onError(InputValidationException.class, exepcionHandler())
				.build();				
	}*/
	
	private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>>exepcionHandler(){
		return (err , req)->{
			InputValidationException ex = (InputValidationException) err;
			InputFailedValidationResponse response =  new InputFailedValidationResponse();
			response.setInput(ex.getInput());
			response.setMessage(ex.getMessage());
			response.setErrorCode(ex.getErrorCode());
			return ServerResponse.badRequest().bodyValue(response);
		};
						
	}

	@Bean
	public RouterFunction<ServerResponse> highLevelRouter(){
		return RouterFunctions.route()
				.path("router", this::serverResponseRouterFunction2)
				.build();
				
	}

	/*	
		//@Bean
	private RouterFunction<ServerResponse> serverResponseRouterFunction2(){//public
		return RouterFunctions.route()
				.GET("square/{input}", requestHandler::squareHandler)
				.GET("table/{input}", requestHandler::tableHandler)
				.GET("router/table/{input}/stream", requestHandler::tableStreamHandler)
				.POST("multiplay", requestHandler::multiplyHandler)
				.GET("square/{input}/validation",requestHandler::squareHandlerWithValidation)
				.onError(InputValidationException.class, exepcionHandler())
				.build();				
	}*/
	
	//@Bean
		private RouterFunction<ServerResponse> serverResponseRouterFunction2(){//public
			return RouterFunctions.route()
					.GET("square/{input}",RequestPredicates.path("*/1?").or(RequestPredicates.path("*20")), requestHandler::squareHandler)
					.GET("square/{input}",req -> ServerResponse.badRequest().bodyValue("tylko 10-20"))
					.GET("table/{input}", requestHandler::tableHandler)
					.GET("router/table/{input}/stream", requestHandler::tableStreamHandler)
					.POST("multiplay", requestHandler::multiplyHandler)
					.GET("square/{input}/validation",requestHandler::squareHandlerWithValidation)
					.onError(InputValidationException.class, exepcionHandler())
					.build();				
		}
	
}
