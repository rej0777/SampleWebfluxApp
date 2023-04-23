package sampleWebfluxApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("job")
public class ParamQueryController {

	@GetMapping("serch")
	public Flux<Integer> serchJobs(
			@RequestParam("count")int count, 
			@RequestParam("pagr")int page) {
		
		
		
		return Flux.just(count, page);
	}
}