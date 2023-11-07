package sampleWebfluxApp.reactor.treads_schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import sampleWebfluxApp.reactor.Util;

public class PublishOnExample {
	
	public static void mYmain(String[] args) {
		
		Flux<Object> flux = Flux.create(fluxSink -> {
			printThreadName("ThreadName_create");
			
			for (int i = 0; i < 20; i++) {
				fluxSink.next(i);
				
			}
			fluxSink.complete();
		})
				//.subscribeOn(Schedulers.newParallel("vins"))
				.doOnNext(t -> printThreadName("next"+t) ); 
		
		
		  flux		// 	Runnable runnable =() ->
			 .publishOn(Schedulers.boundedElastic())	
			 .doOnNext(t -> printThreadName("next2"+t) )
			 .publishOn(Schedulers.parallel())	
			 .subscribe(t -> printThreadName( "sub"+t ));
		 
	
		 Util.sleepSeconds(5);
	}
	
	

	private static void printThreadName(String msg) {
		System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
		
	}
	}
	



		
	


