package sampleWebfluxApp.reactor.examples.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import lombok.ToString;

@ToString
public class RevenueReport {

	private LocalDateTime localDateTime = LocalDateTime.now();
	private Map<String, Double> revenue;
	
	
	public RevenueReport( Map<String, Double> revenue) {
	//	super();
//		this.localDateTime = localDateTime;
		this.revenue = revenue;
	}
	
	
	
}
