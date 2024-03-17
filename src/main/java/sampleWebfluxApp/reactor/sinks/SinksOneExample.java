package sampleWebfluxApp.reactor.sinks;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import sampleWebfluxApp.reactor.Util;

public class SinksOneExample {

	
	public static void mymain(String[] args) {
		
		
		Sinks.One<Object> sink =Sinks.one();
		
		Mono<Object> mono = sink.asMono();
		
		mono.subscribe(Util.subscriber("sam"));
		mono.subscribe(Util.subscriber("sam2"));
		
		//sink.tryEmitValue("hi");
		sink.emitValue("hi" , (signalType , emitResult) ->{
			System.out.println(signalType.name());
			System.out.println(emitResult.name());
			return false;
		});
		
		/*
		//generate error
		sink.emitValue("hi" , (signalType , emitResult) ->{
			System.out.println(signalType.name());
			System.out.println(emitResult.name());
			return false;
		});*/
		
	}
	
}
