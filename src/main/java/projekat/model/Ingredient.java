package projekat.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GenerationType;

@Entity
@Table(name = "ingredient")
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "isHealthy")
	private Boolean isHealthy;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST,CascadeType.MERGE},
			mappedBy = "ingredients")
	@JsonIgnore
	private Set<Pancake> pancakes = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(foreignKey = @ForeignKey(name = "category"), name = "category")
	private Category category;
	

	public Ingredient() {
		super();
	}
	
	public Ingredient(String name, Double price, Boolean isHealthy, Set<Pancake> pancakes, Category category) {
		super();
		this.name = name;
		this.price = price;
		this.isHealthy = isHealthy;
		this.pancakes = pancakes;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getIsHealthy() {
		return isHealthy;
	}

	public void setIsHealthy(Boolean isHealthy) {
		this.isHealthy = isHealthy;
	}

	public Set<Pancake> getPancakes() {
		return pancakes;
	}

	public void setPancakes(Set<Pancake> pancakes) {
		this.pancakes = pancakes;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
