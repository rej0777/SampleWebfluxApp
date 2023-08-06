package sampleWebfluxApp.reactor.flux.operators;

import org.springframework.boot.SpringApplication;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxDoCallbacks {

	
	public static void main(String[] args) {
		
		Flux.create(fluxSink -> {
			System.out.println("inside create ");
			
			for (int i=0; i<5; i++) {
				fluxSink.next(i);
			}
			
			fluxSink.complete();
			System.out.println("-- complited ");
		})
		.doOnComplete(() ->  System.out.println("-- doOnComplete ")  )
			.doFirst(() -> System.out.println("-- doFirst 1"))
		.doOnNext(o ->  System.out.println("-- doOnNext "+o) )
		.doOnSubscribe(t -> System.out.println("-- doOnSubscribe 1 "+t) )
			.doFirst(() -> System.out.println("-- doFirst 2"))
		.doOnRequest( l -> System.out.println("-- doOnRequest "+l) )
		.doOnError(err -> System.out.println("-- doOnError "+err))
		.doOnTerminate(() -> System.out.println("-- doOnTerminate "))
		.doOnCancel(() -> System.out.println("-- doOnCancel "))
		.doFinally(signal  -> System.out.println("-- doFinally "+signal))
			.doOnSubscribe(t -> System.out.println("-- doOnSubscribe 2 "+t) )
			.doFirst(() -> System.out.println("-- doFirst 3"))
		.doOnDiscard(Object.class , t ->System.out.println("-- doOnRequest "+t)  )
		.subscribe(Util.subscriber());
		
		
		
	}
	
	
}
