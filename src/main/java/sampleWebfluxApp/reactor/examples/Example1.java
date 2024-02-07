package sampleWebfluxApp.reactor.examples;

import java.time.Duration;

//import ch.qos.logback.classic.pattern.Util;
import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class Example1 {

	public static void mymain(String[] args) {
	
		final int carPrice = 10000;
		Flux.combineLatest(monthStream(), demandStream(), (month, demand) -> {
			return (carPrice -(month *100)) *demand;
		})
		.subscribe(Util.subscriber());
		
		Util.sleepSeconds(2);
	}
	
	private static Flux<Long> monthStream(){
		return Flux.interval(Duration.ZERO , Duration.ofSeconds(1));
	}
	
	private static Flux<Double> demandStream(){
		return Flux.interval(Duration.ofSeconds(3))
				.map(i -> sampleWebfluxApp.reactor.Util.faker().random().nextInt(80, 120) /100d)
				.startWith(1d);
	}
	
}
