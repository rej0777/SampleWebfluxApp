package sampleWebfluxApp.reactor;

import java.util.function.Consumer;

import reactor.core.publisher.FluxSink;

public class NameProducer implements Consumer<FluxSink<String>>{

	private FluxSink<String> fluxSink;
	
	@Override
	public void accept(FluxSink<String> t) {
		this.fluxSink = t;
		
	}

	public void produce() {
		String name = Util.faker().name().firstName();
		String thread = Thread.currentThread().getName();
		this.fluxSink.next(thread + " : "+name);
	}
	
}
