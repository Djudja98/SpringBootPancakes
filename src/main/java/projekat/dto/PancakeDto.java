package projekat.dto;

import java.util.HashSet;
import java.util.Set;


public class PancakeDto {
	
	private Set<Integer> ingredients = new HashSet<>();
	
	public Set<Integer> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Set<Integer> ingredients) {
		this.ingredients = ingredients;
	}

}
