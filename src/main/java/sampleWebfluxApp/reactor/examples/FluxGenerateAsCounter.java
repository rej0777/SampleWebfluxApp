package sampleWebfluxApp.reactor.examples;

import java.util.concurrent.atomic.AtomicInteger;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxGenerateAsCounter {

	void myMain(){
		AtomicInteger atomicInt = new AtomicInteger(0);
		
		Flux.generate(synchronousSink -> {
			String country = Util.faker().country().name();
			System.out.println("emiting : "+ country);
			synchronousSink.next(country);
			
			atomicInt.incrementAndGet();
			if (country.toLowerCase().equals("canada")) {
				synchronousSink.complete();
			}
		}).subscribe(Util.subscriber());
	}
	
	
	
	void myMain2(){
		
		Flux.generate( ()->1 , (counter,sink) ->{
			String country = Util.faker().country().name();
			System.out.println("emiting : "+ country);
			
			sink.next(country);
			
			if (country.toLowerCase().equals("canada") || counter>=10 ) {
				sink.complete();
			}
			return counter+1;
		} ).subscribe(Util.subscriber());
		
	}
	
}
