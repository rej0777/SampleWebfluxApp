package sampleWebfluxApp.reactor.context;

import java.util.Map;
import java.util.function.Function;

import reactor.util.context.Context;

public class HelperUserService {

	private static final Map<String, String> MAP = Map.of(
			"sam" , "std",
			"mike", "prime");
	
	public static Function<Context, Context> userCategoryContext(){
		return ctx -> {
			String user = ctx.get("user").toString();
			String category = MAP.get(user);
			return ctx.put("category", category);
		};
	}
}
