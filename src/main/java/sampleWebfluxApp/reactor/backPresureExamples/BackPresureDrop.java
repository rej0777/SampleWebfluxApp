package sampleWebfluxApp.reactor.backPresureExamples;

import java.util.Iterator;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import sampleWebfluxApp.reactor.Util;

public class BackPresureDrop {

	public static void main(String[] args) {
		Flux.create(fluxSink -> {
			for (int i = 0; i < 501; i++) {
				fluxSink.next(i);
				System.out.println("Push :"+ i);
			}
			fluxSink.complete();
		})
		.onBackpressureDrop()
		.publishOn(Schedulers.boundedElastic())
		.doOnNext( t -> {
			Util.sleepMillis(10);
		} )
		.subscribe(Util.subscriber());
		
	Util.sleepSeconds(60);
	}
	
}
