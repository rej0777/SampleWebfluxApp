package sampleWebfluxApp.reactor.mono;

import reactor.core.publisher.Mono;
import sampleWebfluxApp.reactor.Util;

import java.util.concurrent.CompletableFuture;

public class MonoFromFuture {

	 public static void myMain() {

	        Mono.fromFuture(getName())
	                .subscribe(Util.onNext());

	        Util.sleepSeconds(1);

	    }
	
	private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
    }
}
