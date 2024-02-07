package sampleWebfluxApp.reactor.examples.helper;

import sampleWebfluxApp.reactor.Util;

public class MyMain {

	public static void main(String[] args) {
		
		OrderService orderService =new OrderService();
		RevenueService revenueService = new RevenueService();
		InventoryService inventoryService= new InventoryService();
		
		orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
		orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());
		
		inventoryService.inventoryStream().subscribe(Util.subscriber());
		revenueService.revenueStream().subscribe(Util.subscriber());
		
		
		Util.sleepSeconds(20);
	}
}
