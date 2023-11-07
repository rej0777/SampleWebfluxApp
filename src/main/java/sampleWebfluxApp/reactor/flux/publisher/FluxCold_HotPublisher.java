package sampleWebfluxApp.reactor.flux.publisher;

import java.time.Duration;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxCold_HotPublisher {

	
	public static void mymain(String[] args) {
		//cold  hot every subscriber will bee data producer
		//hot only one data publisher how will bi late then not get data  //good for broadcast messages for multiple 
		
		Flux<String> moviesStream = Flux.fromStream( () -> getMovi() )
				.delayElements(Duration.ofSeconds(2))
				//.share();/// hot cold publisher
				.publish()
				//.refCount(1) //if first subscriber ends emit for second could
				//.autoConnect(1) // if first end second not get data !!on (0) not need subscriber to emit data  could
				.cache() //(2)how many elements to rememger //  remember history for late subscribers
				;
				
				
		moviesStream.subscribe(Util.subscriber("sam"));		
		Util.sleepSeconds(5);
		moviesStream.subscribe(Util.subscriber("mik"));
		
		Util.sleepSeconds(60);
	}
	
	
	private static Stream<String> getMovi(){
		
		System.out.println("Movi Stream");
		return Stream.of(
				"Scene_1",
				"Scene_2",
				"Scene_3",
				"Scene_4",
				"Scene_5",
				"Scene_6",
				"Scene_7",
				"Scene_8",
				"Scene_9"
				);
	}
}
