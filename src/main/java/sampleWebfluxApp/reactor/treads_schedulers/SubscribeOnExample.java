package sampleWebfluxApp.reactor.treads_schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import sampleWebfluxApp.reactor.Util;
import sampleWebfluxApp.reactor.flux.operators.FluxdelayElements;

public class SubscribeOnExample {

	
	public static void Mymain(String[] args) {
		
		Flux<Object> flux = Flux.create(fluxSink -> {
			printThreadName("create");			
		}).doOnNext(t -> printThreadName("next"+t) ); 
		
		 flux
		 	 .doFirst(() ->  printThreadName("first2"))
			 .subscribeOn(Schedulers.boundedElastic())
			 .doFirst(() ->  printThreadName("first1"))
			 .subscribe(t -> printThreadName( "sub"+t ));
		 
		 for (int i = 0; i < 2; i++) {
			
		}
		 
		 Util.sleepSeconds(5);
	}
	
	

	private static void printThreadName(String msg) {
		System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
		
	}
}
