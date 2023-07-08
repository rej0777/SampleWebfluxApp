package sampleWebfluxApp.reactor.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sampleWebfluxApp.reactor.Util;

public class FluxFromMono {

	void myMain(){
		Mono<String> mono= Mono.just("im mono");		
		Flux<String> flux = Flux.from(mono);
		flux.subscribe(Util.onNext());
		
		Flux.range(0, 10)
		.filter(i -> i>3)
		.next()
		.subscribe(Util.onNext(), Util.onError(), Util.onCompleate());
		
	}
	
	void duSommething(Flux<String> f){}
}
