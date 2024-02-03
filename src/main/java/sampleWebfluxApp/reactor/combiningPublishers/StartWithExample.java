package sampleWebfluxApp.reactor.combiningPublishers;

import sampleWebfluxApp.reactor.Util;
import sampleWebfluxApp.reactor.combiningPublishers.helper.NameGenerator;

public class StartWithExample {

	public static void mymain(String[] args) {
		NameGenerator generator = new NameGenerator();
		generator.generateNames()
		.take(2)
		.subscribe(Util.subscriber());
	}
}
