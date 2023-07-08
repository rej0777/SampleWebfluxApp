package sampleWebfluxApp.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Defaultsubscriber implements Subscriber<Object>{

	private String name ="";
	
	
	
	
	public Defaultsubscriber(String name) {
		super();
		this.name = name+" - ";
	}
	public Defaultsubscriber() {
	}

	@Override
	public void onSubscribe(Subscription s) {
		s.request(Long.MAX_VALUE);
		
	}

	@Override
	public void onNext(Object t) {
		System.out.println(name + "Received : " + t );
		
	}

	@Override
	public void onError(Throwable t) {
		System.out.println(name + "ERROR : " + t.getMessage() );
		
	}

	@Override
	public void onComplete() {
		System.out.println(name + "COMPLETED : ");
		
	}

	
}
