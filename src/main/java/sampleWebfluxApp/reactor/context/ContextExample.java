package sampleWebfluxApp.reactor.context;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import sampleWebfluxApp.reactor.Util;

public class ContextExample {

	public static void mymain(String[] args) {
		getWelcomeMesage()
		.contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
		//.contextWrite(Context.of("user", "sam"))
		.subscribe(Util.subscriber());
		
	}
	
	private static  Mono<String> getWelcomeMesage(){
		return Mono.deferContextual( ctx -> {
			if (ctx.hasKey("user")) {
				return Mono.just("welkom__"+ ctx.get("user"));
			}else {
				return Mono.error(new RuntimeException("unauticated no user"));
			}
		});
			
	}
	
}
