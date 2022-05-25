package projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.dto.OrderRequestDto;
import projekat.dto.OrderResponseDto;
import projekat.model.Order;
import projekat.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderResponseDto> getOrder(@PathVariable(name = "id")Integer id){
		OrderResponseDto order = orderService.getOrder(id);
		return ResponseEntity.ok(order);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> saveOrder(@RequestBody OrderRequestDto orderRequest){
		Order order = orderService.saveOrder(orderRequest);
		return ResponseEntity.ok(order);
	}

}
