package projekat.exceptions;

public class BadIngredientCategory extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BadIngredientCategory() {
		super();
	}
	
	public BadIngredientCategory(String categoryName) {
		super("Bad category:" + categoryName + "; try from baza,preliv,fil,voce");
	}

}
