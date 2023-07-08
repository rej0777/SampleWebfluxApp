package sampleWebfluxApp.reactor.flux;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxBasic1 {

	void myMain(){
		
		Flux <Object> flux = 
				Flux.just(1,2,3,4, "MySTRING", Util.faker().name().fullName());
		
		flux.subscribe(
				Util.onNext(),
				Util.onError(),
				Util.onCompleate());
	}
	
}
