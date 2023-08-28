package sampleWebfluxApp.reactor.flux.operators;

import sampleWebfluxApp.reactor.Util;
import sampleWebfluxApp.reactor.flux.operators.helper.OrderService;
import sampleWebfluxApp.reactor.flux.operators.helper.UserService;

public class FluxFlatMap {

	public static void mymain(String[] args) {
		
		UserService.getUsers().flatMap(user -> OrderService.getOrders(user.getUserId()) )
		.subscribe(Util.subscriber());
		
	}
	
}
