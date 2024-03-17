package sampleWebfluxApp.reactor.context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import sampleWebfluxApp.reactor.Util;

public class HelperBookService {

	public static Map<String , Integer > map = new HashMap<>();
	
	static {
		map.put("std", 2);
		map.put("prime", 3);
	}
	
	
	public static Mono<String> getBook(){
		return Mono.deferContextual(ctx -> {
			if ((boolean) ctx.get("allow")) {
				return Mono.just(Util.faker().book().title());
			}else {
				return Mono.error(new RuntimeException("not Allowed"));
			}
		}).contextWrite(rateLimiterContext());
	}
	

	
	private static Function<Context, Context> rateLimiterContext(){
		return ctx ->{
			if (ctx.hasKey("category")) {
				String category = ctx.get("category").toString();
				Integer attemps = map.get(category);
				if (attemps >0) {
					map.put(category, attemps -1);
					return ctx.put("allow", true);
				}
			}
			return ctx.put("allow", false);
		};
	}
}
