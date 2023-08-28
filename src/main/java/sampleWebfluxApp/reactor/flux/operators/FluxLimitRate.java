package sampleWebfluxApp.reactor.flux.operators;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxLimitRate {
	
	public static void mymain(String[] args) {
		
		Flux.range(1, 1000)
		.log()
		.limitRate(100) //75%
		.limitRate(100, 80)
		.subscribe(Util.subscriber());
	}

}
