package sampleWebfluxApp.reactor.examples.helper;

import com.github.javafaker.Book;

import lombok.Getter;
import lombok.ToString;
import sampleWebfluxApp.reactor.Util;

@Getter
@ToString
public class BookOrder {
	
	private String title;
	private String autor;
	private String category;
	private Double price;
	
	public BookOrder() {
		
		Book book = Util.faker().book();
		this.title = book.title();
		this.autor = book.author();
		this.category= book.genre();
		this.price = Double.parseDouble(Util.faker().commerce().price());
	}

}
