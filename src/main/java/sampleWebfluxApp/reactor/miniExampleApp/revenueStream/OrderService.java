package sampleWebfluxApp.reactor.miniExampleApp.revenueStream;

import java.time.Duration;
import java.util.Objects;

import reactor.core.publisher.Flux;

public class OrderService {

	
	private Flux<PurchaseOrder> flux;
	
	
	public Flux<PurchaseOrder> orderStream(){
		if (Objects.isNull(flux)) 
			flux= getOrdersStream();
		return flux;
	}
	
	public Flux<PurchaseOrder> getOrdersStream() {
		return Flux.interval(Duration.ofMillis(100))
				.map(t -> new PurchaseOrder() )
				.publish()
				.refCount(2);
	}
	
}
