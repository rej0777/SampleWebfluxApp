package sampleWebfluxApp.reactor.flux;

import reactor.core.publisher.Flux;

public class FluxMultipleSubscribers {

	void myMain(){
		
	Flux<Integer> infFlux =Flux.just(1,2,3,4);		
	Flux<Integer> eventFlux	= infFlux.filter(i -> i%2==0);
		
	
	infFlux.subscribe(t -> System.out.println("sub1 : "+ t));
	eventFlux.subscribe(t -> System.out.println("sub1 : "+ t));
	}
}
