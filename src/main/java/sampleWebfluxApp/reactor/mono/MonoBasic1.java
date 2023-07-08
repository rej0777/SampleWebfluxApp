package sampleWebfluxApp.reactor.mono;

import java.util.concurrent.CompletableFuture;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Supplier;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import sampleWebfluxApp.reactor.Util;

public class MonoBasic1 {


	public static void main1() {
		userRepo(1).subscribe(
				Util.onNext(),
				Util.onError(),
				Util.onCompleate());
		
		
		Util.sleepSeconds(1);
	}
	
private static Mono<String>userRepo(int userId){
		
		if (userId ==1) {
			return Mono.just(Util.faker().name().firstName());
		}else if(userId==2){
			return Mono.empty();
		}else
			return Mono.error(new RuntimeException("not in the alow range"));		
}
	
public void mMonojust() {
		
		Mono<Integer> mono= Mono.just("ball")
				.map(String::length)
				.map(l-> l/0);		
			
		mono.subscribe(
				//item -> System.out.println(item),
				//err -> System.out.println(err.getMessage()),
				//() -> System.out.println("complit")
				Util.onNext(),
				Util.onError(),
				Util.onCompleate()
				);
	}

//Mono from suplier Interface
	private static Mono<Object> getName() {
		System.out.println("enter getName Metod");
		return Mono.fromSupplier( () -> {
			System.out.println("Generating Name..: ");
			//Util.sleapSeconds(3) TODO
			return Util.faker().name().fullName();
		}).map(String::toUpperCase);		
	}
	
	
}
