package sampleWebfluxApp.reactor.combiningPublishers;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class ZipFluzExample {

	
	public static void main(String[] args) {
		
		
		Flux.zip(getBody(), getEngine(), getTiers())
		.subscribe(Util.subscriber());
		
	}
	
	private static Flux<String>getBody(){
		return Flux.range(1, 5)
				.map(i-> "body");
	}
	
	
	private static Flux<String>getEngine(){
		return Flux.range(1, 2)
				.map(i-> "engine");
	}
	
	private static Flux<String>getTiers(){
		return Flux.range(1, 6)
				.map(i-> "tiers");
	}
}
