package sampleWebfluxApp.fluxTest;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.flux.operators.helper.Person;


public class FluxTest {
    Person michael = new Person();
    Person fiona = new Person();
    Person sam = new Person();
    Person jesse = new Person();
    
    
    @Test
   
    public void fluxTestDelay() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

		/*
		
        people.delayElements(Duration.ofSeconds(1))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.sayMyName())); //
*/
        countDownLatch.await();

    }
}
