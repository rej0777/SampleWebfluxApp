package sampleWebfluxApp.reactor.combiningPublishers;

import ch.qos.logback.classic.pattern.Util;
import reactor.core.publisher.Flux;
import sampleWebfluxApp.reactor.combiningPublishers.helper.Americanairline;
import sampleWebfluxApp.reactor.combiningPublishers.helper.Emigrates;
import sampleWebfluxApp.reactor.combiningPublishers.helper.Quatar;

public class MergeExample {

	
	public static void mymain(String[] args) {
		
		Flux<String> merge= Flux.merge(
				Quatar.getFlight(),
				Emigrates.getFlight(),
				Americanairline.getFlight()
				);
		
		merge.subscribe(sampleWebfluxApp.reactor.Util.subscriber());
		
	}
}
