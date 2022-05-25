package projekat.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.exceptions.BadIngredientCategory;
import projekat.exceptions.IngredientNotFoundException;
import projekat.model.Ingredient;
import projekat.repository.CategoryRepository;
import projekat.repository.IngredientRepository;

@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	public Ingredient addIngredient(Ingredient ingredient) {
		categoryRepository.findById(ingredient.getCategory().getName())
				.orElseThrow(() -> new BadIngredientCategory(ingredient.getCategory().getName()));
		Ingredient savedIngredient = ingredientRepository.save(ingredient);
		return savedIngredient;
	}
	
	@Transactional
	public Ingredient updateIngredient(Ingredient ingredientRequest) {
		Ingredient ingredient = ingredientRepository.findById(ingredientRequest.getId())
				.orElseThrow(() -> new IngredientNotFoundException(ingredientRequest.getId()));
		ingredient.setCategory(ingredientRequest.getCategory());
		ingredient.setIsHealthy(ingredientRequest.getIsHealthy());
		ingredient.setName(ingredientRequest.getName());
		ingredient.setPrice(ingredientRequest.getPrice());
		Ingredient updatedIngredient = ingredientRepository.save(ingredient);
		return updatedIngredient;
	}
	
	public Ingredient deleteIngredient(Integer id) {
		Ingredient toDeleteIngredient = ingredientRepository.findById(id)
				.orElseThrow(() -> new IngredientNotFoundException());
		ingredientRepository.delete(toDeleteIngredient);
		return toDeleteIngredient;
	}
	
	public List<Ingredient> getAllIngredients(){
		List<Ingredient> ingredientList = new ArrayList<>();
		ingredientList = ingredientRepository.findAll();
		return ingredientList;
	}
	
	public Ingredient getMostOrdered() {
		return ingredientRepository.getMostOrderedIngredient();
	}
	
	public Ingredient getMostOrderedHealthy() {
		return ingredientRepository.getMostOrderedIngredient();
	}

}
