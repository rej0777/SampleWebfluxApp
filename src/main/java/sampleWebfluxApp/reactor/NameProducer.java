package sampleWebfluxApp.reactor;

import java.util.function.Consumer;

import reactor.core.publisher.FluxSink;

public class NameProducer implements Consumer<FluxSink<String>>{

	@Override
	public void accept(FluxSink<String> t) {
		// TODO Auto-generated method stub
		
	}

}
