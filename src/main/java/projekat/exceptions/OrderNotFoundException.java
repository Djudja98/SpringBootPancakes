package projekat.exceptions;

public class OrderNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public OrderNotFoundException() {
		super("Order not found");
	}
	
	public OrderNotFoundException(String message) {
		super(message);
	}
	
	public OrderNotFoundException(Integer id) {
		super("Order wiht id: " + id + " not found");
	}

}
