package projekat.exceptions;

public class IngredientNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public IngredientNotFoundException() {
		super("No ingredient found");
	}
	
	public IngredientNotFoundException(Integer id) {
		super("No ingredient with id:" + id + " found");
	}

	public IngredientNotFoundException(String message) {
		super(message);
	}

}
