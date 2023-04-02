package sampleWebfluxApp.dto;

import jakarta.annotation.sql.DataSourceDefinition;
import lombok.Data;

@Data
public class InputFailedValidationResponse {

	private int errorCode;
	private int input;
	private String message;
	
	
}
