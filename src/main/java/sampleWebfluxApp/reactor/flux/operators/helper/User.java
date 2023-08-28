package sampleWebfluxApp.reactor.flux.operators.helper;

import lombok.Data;
import lombok.ToString;
import sampleWebfluxApp.reactor.Util;

@Data
@ToString
public class User {

	private int userId;
	private String name;
	
	public User(int userId) {
		this.userId = userId;
		this.name = Util.faker().name().firstName();
	}
	
}
