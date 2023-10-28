package sampleWebfluxApp.reactor.miniExampleApp.revenueStream;

import lombok.Data;
import sampleWebfluxApp.reactor.Util;


@Data
public class PurchaseOrder {

	private String item;
	private double price;
	private String category;
	private int quantity;
	
	
	public PurchaseOrder(String item, double price) {
		
		this.item = Util.faker().commerce().productName();
		this.price = Double.parseDouble(Util.faker().commerce().price());
		this.category = Util.faker().commerce().department();
		this.quantity = Util.faker().random().nextInt(1,10);
	}


	public PurchaseOrder() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
