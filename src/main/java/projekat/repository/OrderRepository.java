package projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projekat.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
