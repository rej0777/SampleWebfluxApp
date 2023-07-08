package sampleWebfluxApp.reactor.miniExampleApp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class StockPricePublisher {

	
	void MyMain() throws InterruptedException{

		CountDownLatch latch = new CountDownLatch(1);

		Util.getPrice().subscribeWith(new Subscriber<Integer>() {
			private Subscription subscription;

			@Override
			public void onSubscribe(Subscription s) {
				this.subscription = subscription;
				s.request(Long.MAX_VALUE);
				
			}

			@Override
			public void onNext(Integer t) {
				System.out.println(LocalDateTime.now() +": Price : "+t);
				if (t >110 || t <90) {
					this.subscription.cancel();
					latch.countDown();
				}
			}

			@Override
			public void onError(Throwable t) {
				latch.countDown();
				
			}

			@Override
			public void onComplete() {
				latch.countDown();
				
			}
		});
		latch.await();
	}
	
	

}
