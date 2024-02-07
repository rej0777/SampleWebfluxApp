package sampleWebfluxApp.reactor.examples;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.fasterxml.jackson.datatype.jsr310.util.DurationUnitConverter;

import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.Util;
import sampleWebfluxApp.reactor.examples.helper.BookOrder;
import sampleWebfluxApp.reactor.examples.helper.RevenueReport;

public class Example2Book {

	
	public static void main(String[] args) {
		
		Set<String> allowedCategories = Set.of(
				"Science fiction",
				"Fantasy",
				"Suspense/Thriller");
		
		bookStream()
		.filter(book -> allowedCategories.contains(book.getCategory()))
		.buffer(Duration.ofSeconds(5))
		.map(list -> revenueCalculator(list))
		.subscribe(Util.subscriber());
			
		Util.sleepSeconds(60);
	}
	
	private static RevenueReport revenueCalculator(List<BookOrder> books) {
		Map<String, Double> map= books.stream()
				.collect(Collectors.groupingBy(
						BookOrder::getCategory,
						Collectors.summingDouble(BookOrder::getPrice)
						));
		return new RevenueReport(map);
	}
	
	private static Flux<BookOrder> bookStream(){
		return Flux.interval(Duration.ofMillis(200))
				.map(t -> new BookOrder() );
	}
	
}
