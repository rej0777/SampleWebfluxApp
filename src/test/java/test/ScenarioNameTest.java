package test;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class ScenarioNameTest {

	
	@Test
	public void test1() {
		
		Flux<String> flux = Flux.just("a", "b","c");
		
	StepVerifierOptions scenarioName=	StepVerifierOptions.create().scenarioName("Alfab-test");
	
	StepVerifier.create(flux,scenarioName)
	.expectNextCount(12)
	.verifyComplete();
	
	
	}
	
	
	@Test
	public void test() {
		
		Flux<String> flux = Flux.just("a", "b","c");
	
	StepVerifier.create(flux)
	.expectNext("a")
	.as("Alfab-test")
	.expectNext("b")
	.as("Alfab-test")
	.expectNext("c")
	.as("Alfab-test").verifyComplete();;
	
	
	}
}
