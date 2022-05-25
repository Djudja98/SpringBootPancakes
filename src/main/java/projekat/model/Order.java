package projekat.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "`order`")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "`time`")
	private String time;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "order", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Pancake> pancakes;
	

	public Order() {
		super();
	}

	public Order(String description, String time) {
		super();
		this.description = description;
		this.time = time;
	}

	public Order(String description, String time, List<Pancake> pancakes) {
		super();
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
	
}
