package sampleWebfluxApp.reactor.context;

import reactor.util.context.Context;
import sampleWebfluxApp.reactor.Util;
import sampleWebfluxApp.reactor.flux.operators.helper.UserService;

public class ContextRateLimiterDemo {

	public static void main(String[] args) {
		
		//HelperBookService.getBook().subscribe(Util.subscriber()); //ERROR : not Allowed
		HelperBookService.getBook()
		.repeat(2)
		.contextWrite(HelperUserService.userCategoryContext())
		.contextWrite(Context.of("user", "sam"))
		.subscribe(Util.subscriber());
		
		
		HelperBookService.getBook()
		.repeat(3)
		.contextWrite(HelperUserService.userCategoryContext())
		.contextWrite(Context.of("user", "mike"))
		.subscribe(Util.subscriber());

	}

}
