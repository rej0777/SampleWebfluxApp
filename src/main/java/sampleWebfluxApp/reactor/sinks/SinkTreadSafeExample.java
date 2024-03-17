package sampleWebfluxApp.reactor.sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import sampleWebfluxApp.reactor.Util;

public class SinkTreadSafeExample {

	public static void mymain(String[] args) {
		
		Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
		
		Flux<Object> flux = sink.asFlux();		
		List<Object> list = new ArrayList<>();
		
		flux.subscribe(list::add);
		
		/*	for(int i=0; i<100; i++) {
			final int j =i;
			CompletableFuture.runAsync( () -> {
				sink.tryEmitNext(j);
			});
		}	
		*/
		
		for(int i=0; i<100; i++) {
			final int j =i;
			CompletableFuture.runAsync( () -> {
				sink.emitNext(j, (s,e) ->true);
			});
		}	
		
		
		Util.sleepSeconds(3);
		System.out.println(list.size());
	}
	
}
