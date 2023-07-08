package sampleWebfluxApp.reactor.flux;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxLogs {
	
void myMain(){
		
		Flux.range(3, 10)
		.log()
		.map(i -> Util.faker().name().fullName())
		.log()
		.subscribe(				
				Util.onNext());
	}

}
