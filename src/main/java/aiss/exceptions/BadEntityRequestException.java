package aiss.exceptions;

public class BadEntityRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BadEntityRequestException(String message) {
		super(message);
	}
}
