package sampleWebfluxApp.reactor.flux.operators;

import java.util.function.Function;

import lombok.Data;
import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;
import sampleWebfluxApp.reactor.flux.operators.helper.Person;
	

public class FluxTransform {

	public static void main(String[] args) {
		
		getPerson()
		.transform(aplyFilterMap())
		.subscribe(Util.subscriber());
	}
	
	public static Flux<Person> getPerson() {
		
		return Flux.range(1, 10)
				.map(t -> new Person() );
	}
	
	
	public static Function<Flux<Person>, Flux<Person>> aplyFilterMap() {
		return flux -> flux 
				.filter(t -> t.getAge() >10 )
				.doOnNext(t -> t.setName(t.getName().toUpperCase()))
				.doOnDiscard(Person.class, t -> System.out.println("not allowed >10"+t) );
		
	}
}
