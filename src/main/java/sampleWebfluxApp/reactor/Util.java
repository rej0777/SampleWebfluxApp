package sampleWebfluxApp.reactor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.reactivestreams.Subscriber;

import com.github.javafaker.Faker;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;
import reactor.core.publisher.Flux;

public class Util {

	public static java.util.function.Consumer<? super Object> onNext(){
		return o -> System.out.println("Received : "+o);		
	}
	
	public static java.util.function.Consumer<? super Throwable> onError(){
		return e -> System.out.println("Received : " +e.getMessage());
	}
	
	public static Runnable onCompleate(){
		return () -> System.out.println("Completed ");
	}
	
	
	public static Faker faker() {
		return Faker.instance();		
	}
	
    public static void sleepSeconds(int seconds){
        sleepMillis(seconds * 1000);
    }
    public static void sleepMillis(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static String getNameSleap1() {
    	sleepSeconds(1);
    	return faker().name().fullName();
    }
    
    static List<String> getNames(int count){
    	List<String>list = new ArrayList<>(count);
    	 for(int i =0; i<count;i++) {
    		 list.add(getNameSleap1());
    	 }
    	 return list;
    }
    
    static Flux<String> getNamesFlux(int count){
    return	Flux.range(0, count)
    	.map(t -> getNameSleap1());
    }
    
    public static List<String> nameGeneratorList(int count) {
    	return getNames(count);
    }
    public static Flux<String> nameGeneratorFlux(int count) {
    	return getNamesFlux(count);
    }
    
    
	public static Flux<Integer>getPrice(){
		AtomicInteger atomicInteger = new AtomicInteger(100);
		return Flux.interval(Duration.ofSeconds(1))
				.map(t -> atomicInteger.getAndAccumulate(
						Util.faker().random().nextInt(-5,5),
						Integer::sum));//(a,b) -> a+b));
	}
    
	
	public static Subscriber<Object> subscriber(){
		return new Defaultsubscriber();
	} 
	
	public static Subscriber<Object> subscriber(String name){
		return new Defaultsubscriber(name);
	} 
	
}
