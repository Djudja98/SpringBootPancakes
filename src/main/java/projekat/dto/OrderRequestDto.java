package projekat.dto;

import java.util.List;

public class OrderRequestDto {
	
	private String description;
	private String time;
	private List<Integer> pancakeIds;

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
	public List<Integer> getPancakeIds() {
		return pancakeIds;
	}
	public void setPancakeIds(List<Integer> pancakeIds) {
		this.pancakeIds = pancakeIds;
	}

}
