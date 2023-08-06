package sampleWebfluxApp.reactor.flux.emitItems;

import java.util.concurrent.atomic.AtomicInteger;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxGenerate {

	
	
	void myMain(String[] args){
		Flux.generate(synchronousSink -> {
			System.out.println("emmit: ");
			synchronousSink.next(Util.faker().country().name());
			//synchronousSink.next(Util.faker().country().name());  //error onli1 next
		})
		.take(10)
		.subscribe(Util.subscriber());
	}
	
	
	void myMain2(String[] args){ 
		Flux.generate( synchronousSink -> {
			String country = Util.faker().country().name();
			System.out.println(country);
			if (country.toLowerCase().equals("canada")) {
				synchronousSink.complete();
				
			}
		}).subscribe(Util.subscriber());
	}
	
	AtomicInteger i= new AtomicInteger(1);
	
	void myMain3(String[] args){ 
		Flux.generate( synchronousSink -> {
			String country = Util.faker().country().name();
			System.out.println(country);
			
			i.incrementAndGet();
			
			if (country.toLowerCase().equals("canada")) {
				synchronousSink.complete();
				
			}
		}).subscribe(Util.subscriber());
	}
	
	
	void myMain4(String[] args){ 
		Flux.generate(()->1 , (counter,sink) ->{ 
			String country = Util.faker().country().name();
			System.out.println(country);
			
			sink.next(country);
			if (counter >=10 || country.toLowerCase().equals("canada")) {
				sink.complete();
				
			}
			
			return counter +1;
		} ).take(4).subscribe(Util.subscriber());
}
	
}
