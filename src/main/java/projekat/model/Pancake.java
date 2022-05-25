package projekat.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;

@Entity
@Table(name = "pancake")
public class Pancake {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "pancake_ingredient", joinColumns = {@JoinColumn(name = "pancake_id")},
			inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
	private Set<Ingredient> ingredients = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "order_id", nullable = true)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private Order order;
	

	public Pancake() {
		super();
	}

	public Pancake(Set<Ingredient> ingredients, Order order) {
		super();
		this.ingredients = ingredients;
		this.order = order;
	}
	
	public Double calculatePrice() {
		Double result = 0.0;
		for(Ingredient ingredient : ingredients) {
			result += ingredient.getPrice();
		}
		return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
