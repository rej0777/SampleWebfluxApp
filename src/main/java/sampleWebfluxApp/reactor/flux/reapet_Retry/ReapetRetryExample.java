package sampleWebfluxApp.reactor.flux.reapet_Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;
import sampleWebfluxApp.reactor.Util;

public class ReapetRetryExample {
	private static AtomicInteger atomicInteger =new AtomicInteger(1);
	
	public static void mymain(String[] args) {
		
	 
		
		getIntegers()
		//.repeat()//infinity
		//.repeat(2)//poprostu odczytuje caego flix  + jescze2razy
		//.repeat(() -> atomicInteger.get()<14 )
		//.retry(2)//On error retry 2 times , if no rror normal execution
		.retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(2)))
		.subscribe(Util.subscriber()); 
		
		Util.sleepSeconds(60);
	}
	
	private static Flux<Integer> getIntegers(){
		return Flux.range(1, 3)
				.doOnSubscribe(t -> System.out.println("Subscribed") )
				.doOnComplete(() -> System.out.println("Compeated") )
				.map(t -> atomicInteger.getAndIncrement());
	}
}
