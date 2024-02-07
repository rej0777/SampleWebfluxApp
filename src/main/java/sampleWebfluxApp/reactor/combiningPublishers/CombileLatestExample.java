package sampleWebfluxApp.reactor.combiningPublishers;

import java.time.Duration;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class CombileLatestExample {

	
	public static void main(String[] args) {
		Flux.combineLatest(getString(), getNumber(), (s,i) -> s+i )
		.subscribe(Util.subscriber());
		
		Util.sleepSeconds(10);
	}
	
	
	private static Flux<String> getString(){
		return Flux.just("A","B","C","D")
				.delayElements(Duration.ofSeconds(1));
		
	}
	
	private static Flux<String> getNumber(){
		return Flux.just("1","2","3","4")
				.delayElements(Duration.ofSeconds(3));
		
	}
}
