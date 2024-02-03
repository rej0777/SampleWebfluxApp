package sampleWebfluxApp.reactor.combiningPublishers.helper;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class NameGenerator {

	private List<String> list = new ArrayList<>();
	
	public Flux<String> generateNames() {
		
				
		return Flux.generate(stringSynchronousSink ->{
			System.out.println("##Generated Name Fres");
			Util.sleepSeconds(1);
			String name = Util.faker().name().firstName();
			list.add(name);
			stringSynchronousSink.next(name);
		})
				.cast(String.class)
				.startWith(getFromCach());
		
		
		
	}
	
	private Flux<String> getFromCach(){
		return Flux.fromIterable(list);
		
	}
}
