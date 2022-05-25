package projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import projekat.model.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
	
	@Query(value = "SELECT i.id ,i.name, i.price, i.isHealthy, i.category FROM pancakes.pancake p "
			+ "INNER JOIN pancakes.pancake_ingredient pi "
			+ "ON p.id = pi.pancake_id "
			+ "INNER JOIN pancakes.ingredient i "
			+ "ON i.id = pi.ingredient_id "
			+ "inner join pancakes.`order` o "
			+ "ON p.order_id = o.id "
			+ "AND YEAR(o.`time`) = YEAR(CURRENT_DATE) "
			+ "AND MONTH(o.`time`) = MONTH(CURRENT_DATE) "
			+ "group by ingredient_id "
			+ "order by COUNT(*) DESC "
			+ "LIMIT 1;",	nativeQuery = true)
	Ingredient getMostOrderedIngredient();
	
	@Query(value = "SELECT i.id ,i.name, i.price, i.isHealthy, i.category FROM pancakes.pancake p "
			+ "INNER JOIN pancakes.pancake_ingredient pi "
			+ "ON p.id = pi.pancake_id "
			+ "INNER JOIN pancakes.ingredient i "
			+ "ON i.id = pi.ingredient_id "
			+ "inner join pancakes.`order` o "
			+ "ON p.order_id = o.id "
			+ "AND i.isHealthy = 1 "
			+ "AND YEAR(o.`time`) = YEAR(CURRENT_DATE) "
			+ "AND MONTH(o.`time`) = MONTH(CURRENT_DATE) "
			+ "group by ingredient_id "
			+ "order by COUNT(*) DESC "
			+ "LIMIT 1;",	nativeQuery = true)
	Ingredient getMostOrderedHealthyIngredient();

}
