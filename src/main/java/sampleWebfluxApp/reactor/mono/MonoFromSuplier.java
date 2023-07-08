package sampleWebfluxApp.reactor.mono;

import sampleWebfluxApp.reactor.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;
public class MonoFromSuplier {

	 public static void myMain() {

	        // use just only when you have data already
	       // Mono<String> mono = Mono.just(getName());
			/*
					 
				 
	        Supplier<String> stringSupplier = () -> getName();
	        Mono<String> mono = Mono.fromSupplier(stringSupplier);
	        mono.subscribe(
	                Util.onNext()
	        );

	        Callable<String> stringCallable = () -> getName();
	        Mono.fromCallable(stringCallable)
	                .subscribe(
	                        Util.onNext()
	                );
*/	

	    }
	
	
	    private static Mono<String> getName(){
	        System.out.println("entered getName method");
	        return Mono.fromSupplier(() -> {
	            System.out.println("Generating name..");
	            Util.sleepSeconds(3);
	            return Util.faker().name().fullName();
	        }).map(String::toUpperCase);
	    }
	    
}
