package test;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ErrorTest {

	
	
	@Test
	public void test1() {
		
		Flux<Integer> just = Flux.just(1,2,3) ;
		Flux<Integer> error = Flux.error(new RuntimeException("opps"));
		Flux<Integer> concat = Flux.concat(just , error);
		
		StepVerifier.create(concat)
		.expectNext(1,2,3)
		.verifyError();
	}
	
	@Test
	public void test2() {
		
		Flux<Integer> just = Flux.just(1,2,3) ;
		Flux<Integer> error = Flux.error(new RuntimeException("opps"));
		Flux<Integer> concat = Flux.concat(just , error);
		
		StepVerifier.create(concat)
		.expectNext(1,2,3)
		//.verifyError(IllegalStateException.class);
		.verifyError(RuntimeException.class);
	}
	
	@Test
	public void test3() {
		
		Flux<Integer> just = Flux.just(1,2,3) ;
		Flux<Integer> error = Flux.error(new RuntimeException("opps"));
		Flux<Integer> concat = Flux.concat(just , error);
		
		StepVerifier.create(concat)
		.expectNext(1,2,3)
		//.verifyError(IllegalStateException.class);
		.verifyError(RuntimeException.class);
	}
}
