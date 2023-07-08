package sampleWebfluxApp.reactor.flux;

import java.util.Arrays;
import java.util.List;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxFromArrayOrListOrLiter {

	void MyMain(){
		
		List<String> strings = Arrays.asList("a", "qq","ss");
		
		Flux.fromIterable(strings).subscribe(Util.onNext());
		
	}
	
}
