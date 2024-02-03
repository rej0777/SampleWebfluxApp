package sampleWebfluxApp.reactor.backPresureExamples;

import java.util.Iterator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink.OverflowStrategy;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import sampleWebfluxApp.reactor.Util;

public class BackPresureDrop_Latest_Error_Buffer {

	public static void main(String[] args) {
		Flux.create(fluxSink -> {
			for (int i = 0; i < 501; i++) {
				fluxSink.next(i);
				System.out.println("Push :"+ i);
			}
			fluxSink.complete();
		},OverflowStrategy.BUFFER)
		
		.onBackpressureBuffer(50, t -> System.out.println("Dropt : " +t))//.onBackpressureError()//.onBackpressureLatest()//.onBackpressureDrop()
		.publishOn(Schedulers.boundedElastic())
		.doOnNext( t -> {
			Util.sleepMillis(10);
		} )
		.subscribe(Util.subscriber());
		
	Util.sleepSeconds(60);
	}
	
}
