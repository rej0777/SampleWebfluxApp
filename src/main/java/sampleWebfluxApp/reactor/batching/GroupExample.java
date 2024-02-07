package sampleWebfluxApp.reactor.batching;

import java.time.Duration;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class GroupExample {

	public static void mymain(String[] args) {
		
		Flux.range(1, 30)
		.delayElements(Duration.ofSeconds(1))
		.groupBy(t -> t% 2 )
		.subscribe(t -> procesor(t, t.key()) );
		
		Util.sleepSeconds(60);
	}
	
	private static void procesor(Flux<Integer> flux, int key) {
		
		flux.subscribe(t -> System.out.println("Key : " +key + "Item : " +t ) );
		
	}
}
