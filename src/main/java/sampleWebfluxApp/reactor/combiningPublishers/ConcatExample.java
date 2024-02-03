package sampleWebfluxApp.reactor.combiningPublishers;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class ConcatExample {

public static void mymain(String[] args) {
	
	Flux<String> flux1 = Flux.just("a","b");
	Flux<String> flux2 = Flux.just("c","d","e");
	Flux<String> flux3 = Flux.error(new RuntimeException("ops"));
	
	//Flux<String> flux = flux1.concatWith(flux2);
	//Flux<String> flux = Flux.concat(flux1,flux3, flux2);
	Flux<String> flux = Flux.concatDelayError(flux1,flux3, flux2);
	
	flux.subscribe(Util.subscriber());
	
}	
	
}
