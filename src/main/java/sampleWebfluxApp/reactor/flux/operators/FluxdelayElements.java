package sampleWebfluxApp.reactor.flux.operators;

import java.time.Duration;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxdelayElements {

	
	public static void mymain(String[] args) { 
		
		System.setProperty("reactor.bufferSize.x", "9");
		
		Flux.range(1, 1000)
		.log()
		.delayElements(Duration.ofSeconds(1))
		.subscribe(Util.subscriber());
		
	}
}
