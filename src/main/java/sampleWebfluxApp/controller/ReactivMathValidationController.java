package sampleWebfluxApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import sampleWebfluxApp.dto.Response;
import sampleWebfluxApp.exception.InputValidationException;
import sampleWebfluxApp.service.ReactiveMathService;

@RestController
@RequestMapping("reactiveMath")
public class ReactivMathValidationController {

	
	@Autowired
	private ReactiveMathService  reactiveMathService;
	
	 @GetMapping("square/{input}/throw")
	public Mono<Response> findSquareError (@PathVariable int input) {
		
		 if(input <10 || input >20)
			 throw new InputValidationException(input);
		 
		return this.reactiveMathService.findSquare(input);  //.Block uzycie nie jest reactive
	}
	//http://localhost:8080/reactiveMath/square/8/throw
	//powinno zwrucic
	 //{ "errorCode": 100, "input": 8, "message": "dozwolony zakrs pomiędzy 1 - 20"}
	 
	 
	@GetMapping("square/{input}/MonoError")
	public Mono<Response> findSquareMonoError(@PathVariable int input) {
		return Mono
				.just(input)
				.handle((integer, sink) -> {
			if (integer >= 10 && integer <= 20)
				sink.next(integer);
			else
				sink.error(new InputValidationException(integer));
		}).cast(Integer.class)
				.flatMap(i -> this.reactiveMathService.findSquare(i));
	}
	//http://localhost:8080/reactiveMath/square/8/MonoError

}
