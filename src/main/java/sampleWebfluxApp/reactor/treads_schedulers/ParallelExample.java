package sampleWebfluxApp.reactor.treads_schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import sampleWebfluxApp.reactor.Util;

public class ParallelExample {

	public static void mYmain(String[] args) {
		
		 
		
		
		  Flux.range(1, 10)
			.parallel()
			.runOn(Schedulers.parallel())
			 .doOnNext(t -> printThreadName("next2"+t) )	
			 .subscribe(t -> printThreadName( "sub"+t ));
		 
	
		 Util.sleepSeconds(5);
	}
	
	

	private static void printThreadName(String msg) {
		System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
		
	}
	
}
	



	

