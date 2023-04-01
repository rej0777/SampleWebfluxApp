package sampleWebfluxApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sampleWebfluxApp.dto.Response;
import sampleWebfluxApp.service.MatchService;

@RestController
@RequestMapping("math")
public class MatchController {
	
	@Autowired
	private MatchService matchService;

	
	 @GetMapping("square/{input}")
	public Response findSquare (@PathVariable int input) {
		return this.matchService.findSquare(input);
	}
	 
	 @GetMapping("table/{input}")
		public List<Response> multiplicationTable (@PathVariable int input) {
			return this.matchService.multiplicationTable(input);
		}
}
