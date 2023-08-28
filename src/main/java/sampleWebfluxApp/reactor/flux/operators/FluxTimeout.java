package sampleWebfluxApp.reactor.flux.operators;

import java.time.Duration;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxTimeout {

	
	public static void mymain(String[] args) {
		
		getOrderNumbers()
		.timeout(Duration.ofSeconds(2), falback())
		.subscribe(Util.subscriber());
		
		
		Util.sleepMillis(20);
	}
	
	
	private static Flux<Integer> getOrderNumbers() {

		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(4));//ofSeconds(1)
	}
	
	private static Flux<Integer> falback() {
		return Flux.range(100, 10);

	}
}
