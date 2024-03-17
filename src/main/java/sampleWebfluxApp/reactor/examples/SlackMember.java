package sampleWebfluxApp.reactor.examples;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;

public class SlackMember {

	private String name;
	private Consumer<String> messageConsumer;
	
	public SlackMember(String name) {
		this.name = name;
	}
	
	 String getName() {
		return name;
	}
	
	 void receivers(String message) {
		System.out.println(message);
	}
	
	 void says(String message) {
		this.messageConsumer.accept(message);
	}

	 void setMessageConsumer(Consumer<String> messageConsumer) {
		this.messageConsumer = messageConsumer;
	}
	
	
}
