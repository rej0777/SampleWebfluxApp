package sampleWebfluxApp.reactor.sinks;

import java.time.Duration;
import java.util.Iterator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import sampleWebfluxApp.reactor.Util;

public class SinkMultiDirectAll {

	
	public static void mymain(String[] args) {
		
		System.setProperty("reactor.bufferSize.small", "16");
		
	//	Sinks.Many<Object> sink= Sinks.many().multicast().directAllOrNothing();//Slow subscriber influence oders subscribers
		Sinks.Many<Object> sink= Sinks.many().multicast().directBestEffort();
		Flux<Object> flux = sink.asFlux();
		
		
		flux.subscribe(Util.subscriber("Sam"));
		flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));
		
		
		
		for (int i = 0; i < 100; i++) {
			sink.tryEmitNext(i);
		}
		
		Util.sleepSeconds(10);
	}
}
