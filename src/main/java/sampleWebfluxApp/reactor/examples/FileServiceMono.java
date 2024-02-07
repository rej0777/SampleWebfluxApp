package sampleWebfluxApp.reactor.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import reactor.core.publisher.Mono;
import sampleWebfluxApp.reactor.Util;

public class FileServiceMono {

	private static final java.nio.file.Path PATH = Paths.get("src/main/resources");
	
	void testMain(){
	///	FileServiceMono.read("file1.txt").subscribe(Util.onNext(), Util.onError(), Util.onCompleate());
	//	FileServiceMono.write("file3.txt", "plik3333").subscribe(Util.onNext(), Util.onError(), Util.onCompleate());
	//	FileServiceMono.delete("file3.txt").subscribe(Util.onNext(), Util.onError(), Util.onCompleate());
	}
	
	
	public static Mono<String>read(String fileName){
		return Mono.fromSupplier(() -> readFile(fileName));
	}
	
	public static Mono<Void>write(String fileName, String content){
		return Mono.fromRunnable(() -> writeFile(fileName, content));
	}
	public static Mono<Void>delete(String fileName){
		return Mono.fromRunnable(() -> delete(fileName));
	}
	
	
	
	
	private static String readFile(String fileName) {		
		try {
			return Files.readString(PATH.resolve(fileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
	
	private static void writeFile(String fileName, String content) {		
		try {
			 Files.writeString(PATH.resolve(fileName), content);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
	
	private static void deleteFile(String fileName) {		
		try {
			 Files.delete(PATH.resolve(fileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
	
	
	
}
