package test;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import sampleWebfluxApp.reactor.examples.helper.BookOrder;

public class AssertTest {

	
	@Test
	public void test1() {
		
		Mono<BookOrder> mono = Mono.fromSupplier(() -> new BookOrder())
				.delayElement(Duration.ofSeconds(3));
		
		StepVerifier.create(mono)
		.assertNext( t -> Assertions.assertNotNull(t.getAutor()))
		//.verifyComplete();
		.expectComplete()
		.verify(Duration.ofSeconds(4));

	}
}
