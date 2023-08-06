package sampleWebfluxApp.reactor.flux.emitItems;

import java.util.Iterator;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.NameProducer;
import sampleWebfluxApp.reactor.Util;

public class FluxCreateSink_Push {

	void myMain() {

		// onli one instance of flux sink 
		//multi tread , tread safe
		Flux.create(fluxSink -> {
			String country;

			do {
				country = Util.faker().country().name();
				fluxSink.next(country);
			} while (!country.toLowerCase().equals("canada") && fluxSink.isCancelled());
			fluxSink.complete();

		}).take(3).subscribe(Util.subscriber());
	}

	// public static void main(String[] args) {
	// }
	void myMainV2() {
		NameProducer nameProducer = new NameProducer();
		Flux.create(nameProducer).subscribe(Util.subscriber());
		Runnable runable = nameProducer::produce;// () ->nameProducer.produce();

		for (int i = 0; i < 10; i++) {
			new Thread(runable).start();
		}
		Util.sleepSeconds(2);
	}

	void myMain3() {

		// onli one instance of flux sink
		// the same as Create but NOT tread Safe. push onli in one tread
		NameProducer nameProducer = new NameProducer();
		Flux.push(nameProducer).subscribe(Util.subscriber());
		Runnable runable = nameProducer::produce;// () ->nameProducer.produce();

		for (int i = 0; i < 10; i++) {
			new Thread(runable).start();
		}
		Util.sleepSeconds(2);
	}

}
