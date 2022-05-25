package projekat.exceptions;

public class BadPancakeException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BadPancakeException() {
		super();
	}
	
	public BadPancakeException(String message) {
		super(message);
	}
	
}
