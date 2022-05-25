package projekat.exceptions;

public class PancakeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PancakeNotFoundException() {
		super("Pancake not found");
	}
	
	public PancakeNotFoundException(Integer id) {
		super("Pancake with id: "+ id + " not found");
	}

	public PancakeNotFoundException(String message) {
		super(message);
	}

}
