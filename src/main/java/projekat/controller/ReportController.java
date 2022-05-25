package projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.model.Ingredient;
import projekat.service.IngredientService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingredient> getMostOrdered(){
		Ingredient ingredient = ingredientService.getMostOrdered();
		return ResponseEntity.ok(ingredient);
	}
	
	@GetMapping(value = "/healthy", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ingredient> getMostOrderedHealthy(){
		Ingredient ingredient = ingredientService.getMostOrderedHealthy();
		return ResponseEntity.ok(ingredient);
	}

}
