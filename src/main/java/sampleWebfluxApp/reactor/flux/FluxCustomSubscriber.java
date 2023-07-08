package sampleWebfluxApp.reactor.flux;

import java.util.concurrent.atomic.AtomicReference;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxCustomSubscriber {

	private void myMain() {
		
		AtomicReference<Subscription>atomicReference = new AtomicReference<>();
		
		Flux.range(3, 10)
		.log()
		.subscribeWith(new Subscriber<Integer>() {

			@Override
			public void onSubscribe(Subscription s) {
				System.out.println("retrived subs : "+ s);
				atomicReference.set(s);
				
			}

			@Override
			public void onNext(Integer t) {
				System.out.println("onNext : "+ t);
				
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("onError : "+ t.getMessage());
				
			}

			@Override
			public void onComplete() {
				System.out.println("CompliteD : ");
				
			}
			
		});
		
		
		Util.sleepSeconds(3);
		atomicReference.get().request(3);
		Util.sleepSeconds(5);
		atomicReference.get().request(3);
		Util.sleepSeconds(5);
		
		System.out.println("endSubs");
		atomicReference.get().cancel();
		
		atomicReference.get().request(4);//when subscripcion is cancel no more items
		Util.sleepSeconds(5);
		
	}
}
