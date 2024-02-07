package sampleWebfluxApp.reactor.batching;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sampleWebfluxApp.reactor.Util;

public class WindowExample {
	
	private static AtomicInteger atomicInteger = new AtomicInteger(1);
	
	public static void mymain(String[] args) {
		
		eventStream()
	//.window(5)
		.window(Duration.ofSeconds(2))
	.flatMap(Flux -> saveEvents(Flux))
		.subscribe(Util.subscriber());
		
	}

	
	private static Flux<String> eventStream(){
		return Flux.interval(Duration.ofMillis(500))
				.map(t -> "event" +t);
	}
	
	
	private static Mono<Integer> saveEvents(Flux<String> flux){
		return  flux.doOnNext(e -> System.out.println("save"+e))
				.doOnComplete(() -> {
					System.out.println("paczka zapisana");
					System.out.println("----------------");
					
				})
				//.then();
				.then(Mono.just(atomicInteger.getAndIncrement()));
		
		
	}
}
