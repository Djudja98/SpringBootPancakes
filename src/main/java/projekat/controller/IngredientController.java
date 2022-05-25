package projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.model.Ingredient;
import projekat.service.IngredientService;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping
	public ResponseEntity<List<Ingredient>> getAllIngredients(){
		List<Ingredient> ingredients = ingredientService.getAllIngredients();
		return ResponseEntity.ok(ingredients);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient){
		Ingredient newIngredient = ingredientService.addIngredient(ingredient);
		return new ResponseEntity<Ingredient>(newIngredient, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingredient> updateIngredient(@PathVariable(value = "id") Integer id,
			@RequestBody Ingredient ingredient){
		Ingredient updatedIngredient = ingredientService.updateIngredient(ingredient);
		return new ResponseEntity<Ingredient>(updatedIngredient, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingredient> deleteIngredient(@PathVariable(value = "id") Integer id){
		Ingredient ingredient = ingredientService.deleteIngredient(id);
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
	}

}
