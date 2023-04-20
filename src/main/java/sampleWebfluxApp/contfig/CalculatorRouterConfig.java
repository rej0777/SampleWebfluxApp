package sampleWebfluxApp.contfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import sampleWebfluxApp.exception.InputValidationException;

@Configuration
public class CalculatorRouterConfig {

	@Autowired
	private CalculatorHandler calculatorHandler;

	@Bean
	public RouterFunction<ServerResponse> RouterCalculator() {
		return RouterFunctions.route().path("calculator", this::serverResponseRouterFunction2).build();

	}

	private RouterFunction<ServerResponse> serverResponseRouterFunction2() {
		return RouterFunctions.route()
				.GET("{a}/{b}", isoperacion("+"), calculatorHandler::additionHandler)
				.GET("{a}/{b}", isoperacion("-"), calculatorHandler::subtractionHandler)
				.GET("{a}/{b}", isoperacion("*"), calculatorHandler::multiplicationHandler)
				.GET("{a}/{b}", isoperacion("/"), calculatorHandler::divisioHandler)
				.GET("{a}/{b}", req -> ServerResponse.badRequest().bodyValue("OP onli +_-*/")) 
				.build();
	}
	//http://localhost:8080/calculator/4/2
	//Headers Key=op VALIUE=+-*/

	private RequestPredicate isoperacion(String operation) {
		return RequestPredicates
				.headers(heders -> operation.equals(heders.asHttpHeaders().toSingleValueMap().get("op")));

	}
}
