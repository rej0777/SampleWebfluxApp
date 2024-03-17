package sampleWebfluxApp.reactor.sinks;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import sampleWebfluxApp.reactor.Util;

public class SinkUnicastMulticastExample {

	public static void mymain(String[] args) {
		/*
		
		Sinks.Many<Object> sink= Sinks.many().unicast().onBackpressureBuffer();
		
		Flux<Object> flux = sink.asFlux();
		
		flux.subscribe(Util.subscriber("my"));
		
		sink.tryEmitNext("hi1");
		sink.tryEmitNext("hi2");
	*/
		
		
//		Sinks.Many<Object> sink= Sinks.many().unicast().onBackpressureBuffer();
//		Sinks.Many<Object> sink= Sinks.many().multicast().onBackpressureBuffer().;
		Sinks.Many<Object> sink= Sinks.many().multicast().directAllOrNothing();//turn off buffered history
		
		Flux<Object> flux = sink.asFlux();
			
		List<Object> list = new ArrayList<>();
		
		flux.subscribe(Util.subscriber("Sam"));
		flux.subscribe(Util.subscriber("mike"));
		
		sink.tryEmitNext("hi222");
		sink.tryEmitNext("hi");
		//the first subscriber will sea buferes besages
		//second subscriber will see onli lasr mesage
		sink.tryEmitNext("hallo");
	}
	
	
}
