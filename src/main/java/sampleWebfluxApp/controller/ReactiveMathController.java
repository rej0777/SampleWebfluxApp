package sampleWebfluxApp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sampleWebfluxApp.dto.MultiplyRequestDto;
import sampleWebfluxApp.dto.Response;
import sampleWebfluxApp.service.ReactiveMathService;

@RestController
@RequestMapping("reactiveMath")
public class ReactiveMathController {
	
	@Autowired
	private ReactiveMathService  reactiveMathService;
	
	 @GetMapping("square/{input}")
	public Mono<Response> findSquare (@PathVariable int input) {
		return this.reactiveMathService.findSquare(input);  //.Block uzycie nie jest reactive
	}
	 
	 @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
		public Flux<Response> multiplicationTableStream (@PathVariable int input) {
			return this.reactiveMathService.multiplicationTable(input);
		}

	@PostMapping("multiply")
	public Mono<Response> multiplyPost(@RequestBody Mono<MultiplyRequestDto> requestDtoMono,
			@RequestHeader Map<String, String> heders) { // @RequestBody MultiplyRequestDto dto
		System.out.println(heders);
		return this.reactiveMathService.multiply(requestDtoMono);
// postman
//post http://localhost:8080/reactiveMath/multiply
//Headers klucz wartość
//	body json  {"first": 35, "second":2
		}
	
	
	}
