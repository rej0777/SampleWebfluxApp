package sampleWebfluxApp.reactor.flux.operators;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxDefaultIf_Switchif_Empty {

	
	public static void mymain(String[] args) {
		
		getOrderNumbers()
		.filter(t -> t >10 )
		.defaultIfEmpty(-100)
		.subscribe(Util.subscriber());
		
		getOrderNumbers()
		.filter(t -> t >10 )
		.switchIfEmpty(falback())
		.subscribe(Util.subscriber());
		
	}
	
	//cach
	private static  Flux<Integer> getOrderNumbers() {
		return Flux.range(1, 10);
	}
	
	//db
	private static Flux<Integer> falback() {		
		return Flux.range(20, 5);
	}
	
	
}
