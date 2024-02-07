package sampleWebfluxApp.reactor.examples;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;
import sampleWebfluxApp.reactor.examples.helper.OrderProcessor;
import sampleWebfluxApp.reactor.examples.helper.OrderService;
import sampleWebfluxApp.reactor.examples.helper.PurchaseOrder;

public class AssigmentPurchaseOrder {
	
	public static void mymain(String[] args) {
		
		Map<String , Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = Map.of(
				 "Kids" ,OrderProcessor.kidsProcesing(),
				 "Automotive" , OrderProcessor.automotiveProcessing()
			);
		
		Set<String> set = map.keySet();
		
		OrderService.orderStream2()
		.filter(t -> set.contains(t.getCategory()) )
		.groupBy( PurchaseOrder::getCategory ) 
		.flatMap(p -> map.get(p.key()).apply(p) )
		.subscribe(Util.subscriber());
		
		Util.sleepSeconds(60);
		}
	}
//OrderProcesor
//OrderService
//PurchaseOrder


