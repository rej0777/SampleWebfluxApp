package sampleWebfluxApp.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.core.ReactiveAdapter;
import org.springframework.stereotype.Service;

import sampleWebfluxApp.dto.Response;

@Service
public class MatchService {

	public Response findSquare(int input) {
		return new Response(input * input);	
	}
	
	public  List<Response> multiplicationTable(int input) {
		return IntStream.rangeClosed(1, 10)
				.peek(i -> SleepUtil.sleapSeconds(1))
				.peek(i -> System.out.println("matchServise obsuga elementu : "+i))
				.mapToObj(i -> new Response(i * input))
				.collect(Collectors.toList());						
	}
}
