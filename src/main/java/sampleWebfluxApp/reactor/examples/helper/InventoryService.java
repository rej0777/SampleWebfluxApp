package sampleWebfluxApp.reactor.examples.helper;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;
import reactor.core.publisher.Flux;

public class InventoryService {

	
	private Map< String, Integer> db=new  HashMap<>();
	
	public  InventoryService() {
		db.put("ob1", 100);
		db.put("ob2", 100);
	}
	
	public java.util.function.Consumer<? super PurchaseOrder> subscribeOrderStream () {
		return p -> db.computeIfPresent(p.getCategory(), (t, u) -> u - p.getQuantity() ); 
	}
	
	public Flux<String> inventoryStream() {
		return Flux.interval(Duration.ofSeconds(2))
				.map(t -> db.toString() );
	}
}
