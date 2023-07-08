package sampleWebfluxApp.reactor.flux;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxCreateSink {

	void myMain(){
		
		Flux.create( fluxSink -> {
			String country;
			
			do {
				country = Util.faker().country().name();
				fluxSink.next(country);
			} while (!country.toLowerCase().equals("canada"));
			fluxSink.complete();
			
		} ).subscribe(Util.subscriber());
	}
	
	void myMain2(){
		
	}
}
