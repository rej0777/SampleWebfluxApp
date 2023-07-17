package sampleWebfluxApp.reactor.flux.emitItems;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxTake {

	
	
	void myMain(){
		
		Flux.range(1, 10)
		.take(3)
		.log()
		.subscribe(Util.subscriber());
	}
	
}
