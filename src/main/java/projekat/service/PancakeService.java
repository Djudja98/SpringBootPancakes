package projekat.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.dto.PancakeDto;
import projekat.exceptions.BadPancakeException;
import projekat.exceptions.IngredientNotFoundException;
import projekat.exceptions.PancakeNotFoundException;
import projekat.model.Ingredient;
import projekat.model.Pancake;
import projekat.repository.IngredientRepository;
import projekat.repository.PancakeRepository;

@Service
public class PancakeService {
	
	@Autowired
	private PancakeRepository pancakeRepository;
	@Autowired
	private IngredientRepository ingredientRepository;
	
	public Pancake addPancake(PancakeDto pancakeDto) {
		Pancake pancake = new Pancake();
		mapDtoToEntity(pancakeDto, pancake);
		pancakeRepository.save(pancake);
		return pancake;
	}
	
	public List<Pancake> getAllPancakes(){
		List<Pancake> pancakeList = new ArrayList<>();
		pancakeList = pancakeRepository.findAll();
		return pancakeList;
	}
	
	public Pancake getOne(Integer id) {
		Pancake pancake = pancakeRepository.findById(id)
				.orElseThrow(() -> new PancakeNotFoundException(id));
		return pancake;
	}
	
	public Pancake update(Integer id, PancakeDto pancakeDto) {
		Pancake pancake = pancakeRepository.findById(id)
				.orElseThrow(() -> new PancakeNotFoundException(id));
		pancake.setIngredients(new HashSet<>()); //isprazni postojece sastojke
		mapDtoToEntity(pancakeDto, pancake); //dodaj nove
		pancakeRepository.save(pancake);
		return pancake;
	}
	
	public String delete(Integer id) {
		pancakeRepository.deleteById(id);
		return "Pancake with id: " + id + " deleted successfully";
	}

	
	private void mapDtoToEntity(PancakeDto pancakeDto, Pancake pancake) {
		if(pancakeDto.getIngredients() == null || pancakeDto.getIngredients().size() < 1) {
			throw new BadPancakeException("Empty list of ingredients");
		}
		pancakeDto.getIngredients().stream().forEach(ingredientId ->{
			Ingredient ingredient = ingredientRepository.findById(ingredientId)
					.orElseThrow(() -> new IngredientNotFoundException(ingredientId));
			pancake.getIngredients().add(ingredient);
		});
	}
}
