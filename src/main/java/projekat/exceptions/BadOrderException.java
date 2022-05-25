package projekat.exceptions;

public class BadOrderException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BadOrderException() {
		super();
	}
	
	public BadOrderException(String message) {
		super(message);
	}

}
