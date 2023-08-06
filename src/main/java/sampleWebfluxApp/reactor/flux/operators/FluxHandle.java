package sampleWebfluxApp.reactor.flux.operators;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;

public class FluxHandle {

	//handle = filter +map
	


	public static void mymain(String[] args) {
		
		Flux.range(1, 20)
		.handle((integer, synchronousSink) ->  {
			
			if(integer % 2 ==0)
			synchronousSink.next(integer);
			else
				synchronousSink.next(integer+"a");
						
			if(integer==7)
				synchronousSink.complete();
			
		}).subscribe(Util.subscriber());
	}
	
	
	public static void my2main(String[] args) {
		
		Flux.generate( synchronousSink -> synchronousSink.next(Util.faker().country().name()) )
		.map(Object::toString)
		.handle( ((s, synchronousSink) ->  { 
			synchronousSink.next(s);
			if (s.toLowerCase().equals("canada")) 
				synchronousSink.complete();
		}   )).subscribe(Util.subscriber()) ;
		
	}
}
