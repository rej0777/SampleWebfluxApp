package sampleWebfluxApp.reactor.treads_schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import sampleWebfluxApp.reactor.Util;

public class SubscribeOnMultipleItems {

	
public static void main(String[] args) {
		
		Flux<Object> flux = Flux.create(fluxSink -> {
			printThreadName("ThreadName_create");
			
			for (int i = 0; i < 20; i++) {
				fluxSink.next(i);
				Util.sleepSeconds(1);
			}
			fluxSink.complete();
		})
				//.subscribeOn(Schedulers.newParallel("vins"))
				.doOnNext(t -> printThreadName("next"+t) ); 
		
		
		Runnable runnable =() ->  flux		 	
			 .subscribeOn(Schedulers.boundedElastic())			
			 .subscribe(t -> printThreadName( "sub"+t ));
		 
		for (int i = 0; i <5; i++) {
			new Thread(runnable).start();
		}
		 
		 Util.sleepSeconds(5);
	}
	
	

	private static void printThreadName(String msg) {
		System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
		
	}
}

