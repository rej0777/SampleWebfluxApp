package sampleWebfluxApp.reactor.mono;

import reactor.core.publisher.Mono;
import sampleWebfluxApp.reactor.Util;

public class MonoFromRunable {

	 public static void myMain() {


	        Mono.fromRunnable(timeConsumingProcess())
	                .subscribe(Util.onNext(),
	                        Util.onError(),
	                        () -> {
	                            System.out.println("process is done. Sending emails...");
	                        });


	    }

	    private static Runnable timeConsumingProcess(){
	        return () -> {
	            Util.sleepSeconds(3);
	            System.out.println("Operation completed");
	        };
	    }
}
