package sampleWebfluxApp.reactor.flux.emitItems;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxGenerate {

	
	
	void myMain(String[] args){
		Flux.generate(synchronousSink -> {
			System.out.println("emmit: ");
			synchronousSink.next(Util.faker().country().name());
			//synchronousSink.next(Util.faker().country().name());  //error onli1 next
		} ).take(10).subscribe(Util.subscriber());
	}
}
