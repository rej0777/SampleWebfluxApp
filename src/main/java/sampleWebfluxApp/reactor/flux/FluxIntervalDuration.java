package sampleWebfluxApp.reactor.flux;

import java.time.Duration;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxIntervalDuration {

   void	myMain(){
	   Flux.interval(Duration.ofSeconds(1))
	   .subscribe(Util.onNext());
	   
	   Util.sleepSeconds(5);
   }
}
