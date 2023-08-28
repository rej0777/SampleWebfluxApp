package sampleWebfluxApp.reactor.flux.operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sampleWebfluxApp.reactor.Util;

public class FluxOnError {
	
	public static void mymain(String[] args) {
		
		Flux.range(1, 10)
		.log()
		.map(i -> 10/ (5-i))
		//.onErrorReturn(-1)
		//.onErrorResume(t -> fallback() )
		.onErrorContinue((err, obj) -> {
			
		})
		.subscribe(Util.subscriber());
		
	}
	
	private static Mono<Integer> fallback(){
		return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
	}

}
