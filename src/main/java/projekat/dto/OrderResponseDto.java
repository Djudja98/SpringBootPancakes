package projekat.dto;

import java.util.List;

import projekat.model.Pancake;

public class OrderResponseDto {
	
	private Integer id;
	private String description;
	private String time;
	private Double price;
	private List<Pancake> pancakes;
	
	public OrderResponseDto(Integer id, String description, String time, List<Pancake> pancakes) {
		super();
		this.id = id;
		this.description = description;
		this.time = time;
		this.pancakes = pancakes;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<Pancake> getPancakes() {
		return pancakes;
	}
	public void setPancakes(List<Pancake> pancakes) {
		this.pancakes = pancakes;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
