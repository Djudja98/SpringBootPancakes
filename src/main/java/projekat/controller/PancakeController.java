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

import projekat.dto.PancakeDto;
import projekat.model.Pancake;
import projekat.service.PancakeService;

@RestController
@RequestMapping("/api/pancakes")
public class PancakeController {
	
	@Autowired
	private PancakeService pancakeService;
	
	@GetMapping
	public ResponseEntity<List<Pancake>> getAllPancakes(){
		List<Pancake> pancakes = pancakeService.getAllPancakes();
		return ResponseEntity.ok(pancakes);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pancake> getPancake(@PathVariable(name = "id")Integer id){
		Pancake pancake = pancakeService.getOne(id);
		return ResponseEntity.ok(pancake);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pancake> addPancake(@RequestBody PancakeDto pancakeDto){
		Pancake newPancake = pancakeService.addPancake(pancakeDto);
		return new ResponseEntity<Pancake>(newPancake, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pancake> updatePancake(@PathVariable(name = "id")Integer id,
			@RequestBody PancakeDto pancakeDto){
		Pancake newPancake = pancakeService.update(id,pancakeDto);
		return new ResponseEntity<Pancake>(newPancake, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePancake(@PathVariable(name = "id")Integer id){
		String message = pancakeService.delete(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
