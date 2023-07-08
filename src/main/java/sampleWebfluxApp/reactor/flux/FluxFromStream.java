package sampleWebfluxApp.reactor.flux;

import java.util.List;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxFromStream {

	void myMain(){
		
		List<Integer>list = List.of(1,2,3,4,5,6,7);
	Stream<Integer>stream = list.stream();
	
//	Flux<Integer> integerFlux = Flux.fromStream(stream);
//	Flux<Integer> integerFlux2 = Flux.fromStream(() -> stream); tylko jedno wywołanie strean
	
	Flux<Integer> integerFlux = Flux.fromStream(() -> list.stream());
	
	integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onCompleate());
	integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onCompleate());
	}
	
	
	

}
