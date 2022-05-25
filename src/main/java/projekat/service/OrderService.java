package projekat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.dto.OrderRequestDto;
import projekat.dto.OrderResponseDto;
import projekat.exceptions.BadOrderException;
import projekat.exceptions.OrderNotFoundException;
import projekat.exceptions.PancakeNotFoundException;
import projekat.model.Ingredient;
import projekat.model.Order;
import projekat.model.Pancake;
import projekat.repository.OrderRepository;
import projekat.repository.PancakeRepository;

@Service
public class OrderService {
	
	private static final String BASE_CATEGORY = "baza";
	private static final String FILL_CATEGOTY = "fil";
	private static final int SMALL_DISCOUNT = 5;
	private static final int MEDIUM_DISCOUNT = 10;
	private static final int HEALTHY_DISCOUNT = 15;
	private static final int HEALTHY_MARGIN = 75;
	private static final int SMALL_MARGIN = 20;
	private static final int MEDIUM_MARGIN = 50;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PancakeRepository pancakeRepository;
	
	public OrderResponseDto getOrder(Integer id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException(id));
		Double price = calculatePrice(order);
		OrderResponseDto responseDto = new OrderResponseDto(order.getId(), order.getDescription(),
				order.getTime(), order.getPancakes());
		responseDto.setPrice(price);
		return responseDto;
	}
	
	public Order saveOrder(OrderRequestDto orderRequest) {
		if(!checkOrderValidity(orderRequest)) {
			throw new BadOrderException("Invalid pancakes in order list");
		}
		List<Pancake> pancakes = new ArrayList<>();
		checkIfPancakesTaken(orderRequest.getPancakeIds());
		Order order = new Order(orderRequest.getDescription(),orderRequest.getTime());
		orderRequest.getPancakeIds().forEach(pancakeId -> {
			Pancake pancake = pancakeRepository.findById(pancakeId)
					.orElseThrow(()-> new PancakeNotFoundException(pancakeId));
			pancake.setOrder(order);
			pancakes.add(pancake);
		});
		order.setPancakes(pancakes);
		orderRepository.save(order);
		return order;
	}
	
	private void checkIfPancakesTaken(List<Integer> pancakeIds) {
		for(Integer pancakeId: pancakeIds) {
			Pancake pancake = pancakeRepository.findById(pancakeId)
					.orElseThrow(()-> new PancakeNotFoundException(pancakeId));
			if(pancake.getOrder() != null) {
				throw new BadOrderException("Pancake with id:" + pancakeId + " already taken");
			}
		}
	}
	
	private boolean checkOrderValidity(OrderRequestDto orderRequest) {
		List<Integer> pancakeIdList = orderRequest.getPancakeIds();
		if(pancakeIdList.size() < 1) {
			return false;
		}
		for(Integer pancakeId : pancakeIdList) {
			Pancake pancake = pancakeRepository.findById(pancakeId)
					.orElseThrow(()-> new PancakeNotFoundException());
			int baseIngredientCount = 0;
			int fillIngredientCount = 0;
			for(Ingredient ingredient : pancake.getIngredients()) {
				if(BASE_CATEGORY.equals(ingredient.getCategory().getName())) {
					baseIngredientCount++;
				}
				if(FILL_CATEGOTY.equals(ingredient.getCategory().getName())) {
					fillIngredientCount++;
				}
			}
			if(baseIngredientCount != 1) {
				return false;
			}
			if(fillIngredientCount < 1) {
				return false;
			}
		}
		return true;
	}
	
	//vraca najveci moguci discount od ona 3 za svaku narudzbu
	private Double calculatePrice(Order order) {
		Double result = 0.0;
		Double smallDiscount = 0.0;
		Double mediumDiscount = 0.0;
		Double healthyDiscount = 0.0;
		
		List<Pancake> pancakes = order.getPancakes();
		for(Pancake pancake : pancakes) {
			healthyDiscount += getPancakeHealthyDiscount(pancake);
			result += pancake.calculatePrice();
		}
		if(result > SMALL_MARGIN) {
			smallDiscount = (result * SMALL_DISCOUNT) / 100;
		}
		if(result > MEDIUM_MARGIN) {
			mediumDiscount = (result * MEDIUM_DISCOUNT) / 100;
		}
		System.out.println("Small discount "+ smallDiscount);
		System.out.println("Medium discount "+ mediumDiscount);
		System.out.println("Healthy discount "+ healthyDiscount);
		double discount = DoubleStream.of(smallDiscount, mediumDiscount, healthyDiscount)
				.max()
				.getAsDouble();
		return result - discount;
	}
	
	private Double getPancakeHealthyDiscount(Pancake pancake) {
		Double discount = 0.0;
		int healthyIngredientsNumber = 0;
		Set<Ingredient> ingredients = pancake.getIngredients();
		for(Ingredient ingredient : ingredients) {
			if(ingredient.getIsHealthy()) {
				healthyIngredientsNumber++;
			}
		}
		int healthyPercent = (healthyIngredientsNumber / ingredients.size()) * 100;
		if(healthyPercent > HEALTHY_MARGIN) {
			Double price = pancake.calculatePrice();
			discount = (price * HEALTHY_DISCOUNT) / 100;
		}
		return discount;
	}

}
