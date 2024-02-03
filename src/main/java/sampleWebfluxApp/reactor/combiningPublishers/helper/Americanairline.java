package sampleWebfluxApp.reactor.combiningPublishers.helper;

import java.time.Duration;
import java.util.function.Function;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class Americanairline {

	public static Flux<String> getFlight(){
		
		return Flux.range(1, Util.faker().random().nextInt(1, 5))
				.delayElements(Duration.ofSeconds(1))
				.map(i -> "Americanairline" + Util.faker().random().nextInt(100, 999))
				.filter(i -> Util.faker().random().nextBoolean());
	}
}
