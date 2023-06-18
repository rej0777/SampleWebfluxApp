package sampleWebfluxApp.exception;

import lombok.Getter;

public class InputValidationException extends RuntimeException {

	private static final long serialVersionUID = -3842895449808351364L;
	private static final String MSG ="dozwolony zakrs pomiędzy 10 - 20";
	
	@Getter
	private  final int errorCode=100;
	
	@Getter
	private final int input;

	public InputValidationException(int input) {
		super(MSG);
		this.input = input;
	}
	
	
}
