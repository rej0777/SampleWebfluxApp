package test;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sampleWebfluxApp.reactor.examples.helper.BookOrder;

public class VirtualTimeTest {

	
	@Test
	public void test1() {
		
		StepVerifier.withVirtualTime(() -> timeConsumingFlux())
		.thenAwait(Duration.ofSeconds(30))
		.expectNext("1a" ,"2a", "3a","4a" )
		.verifyComplete();
	}
	
	@Test
	public void test2() {
		
		StepVerifier.withVirtualTime(() -> timeConsumingFlux())
		.expectSubscription()
		.expectNoEvent(Duration.ofSeconds(4))
		.thenAwait(Duration.ofSeconds(30))
		.expectNext("1a" ,"2a", "3a","4a" )
		.verifyComplete();
	}
	
	
	private Flux<String> timeConsumingFlux(){
		return Flux.range(1, 4)
				.delayElements(Duration.ofSeconds(5))
				.map( (i -> i+ "a"));
	}
	
}
