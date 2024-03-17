package sampleWebfluxApp.reactor.sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import sampleWebfluxApp.reactor.Util;

public class SinkReply {

	
	public static void main(String[] args) {
		
		Sinks.Many<Object> sink = Sinks.many().replay().all();//cache for all maesagec for ewrywane
		
		Flux<Object> flux = sink.asFlux();
		
		sink.tryEmitNext("mesage1");
		sink.tryEmitNext("mesage2");
		
		flux.subscribe(Util.subscriber("Samm"));
		flux.subscribe(Util.subscriber("MIKE"));
		
		sink.tryEmitNext("xxx");
		
		flux.subscribe(Util.subscriber("Sub33"));
		
	}
}
