package sampleWebfluxApp.reactor.flux;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxRange {

	void myMain(){
		
		Flux.range(3, 10)
		.map(i -> Util.faker().name().fullName())
		.subscribe(				
				Util.onNext());
	}
	
}
