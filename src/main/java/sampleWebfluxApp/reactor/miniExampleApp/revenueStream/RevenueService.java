package sampleWebfluxApp.reactor.miniExampleApp.revenueStream;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;
import reactor.core.publisher.Flux;

public class RevenueService {

	private Map< String, Double> db=new  HashMap<>();
	
	public  RevenueService() {
		db.put("ob1", 0.0);
		db.put("ob2", 0.0);
	}
	
	public java.util.function.Consumer<? super PurchaseOrder> subscribeOrderStream () {
		return p -> db.computeIfPresent(p.getCategory(), (t, u) -> u + p.getPrice() ); 
	}
	
	public Flux<String> revenueStream() {
		return Flux.interval(Duration.ofSeconds(2))
				.map(t -> db.toString() );
	}
	
}
