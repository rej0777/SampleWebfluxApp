package sampleWebfluxApp.reactor.batching;

import java.time.Duration;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class OverlapAndDropExample {

	
	public static void mymain(String[] args) {
		
		eventStream()
		.buffer(5, 2) //(5, 7)  dalej zwraca jako lista
	//	.buffer(Duration.ofSeconds(2))
	//	.bufferTimeout(5, Duration.ofSeconds(2))
		.subscribe(Util.subscriber());
		
	}

	
	private static Flux<String> eventStream(){
		return Flux.interval(Duration.ofMillis(300))
				.map(t -> "event" +t);
	}
	
}
