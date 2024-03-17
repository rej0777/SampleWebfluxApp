package sampleWebfluxApp.reactor.flux.reapet_Retry;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import sampleWebfluxApp.reactor.Util;

public class RetryWhenAdvancedExample {

	public static void mymain(String[] args) {
		orderService(Util.faker().business().creditCardNumber())
		.doOnError(t -> System.out.println(t))
		.retryWhen( Retry.from( 
				flux ->  flux.doOnNext( s -> {
					System.out.println(s.totalRetries());
					System.out.println(s.failure());
				} )
				.handle( (ss , synch) ->{
					if (ss.failure().getMessage().equals("500")) {
						synch.next(1);
					}
				})
				))
		.subscribe(Util.subscriber());
		
		Util.sleepSeconds(60);
	}
	
	
	private static Mono<String> orderService(String ccNumber) {
		return Mono.fromSupplier( () -> {
			processPayment(ccNumber);
			return Util.faker().idNumber().valid();
		});
	}
	
	private static void processPayment(String ccNumber) {
		
		int random = Util.faker().random().nextInt(1,10);
		if (random <8 ) {
			throw new RuntimeException("500");
		}else if (random < 10) {
			throw new RuntimeException("404");
		}
	}
	
	
}
