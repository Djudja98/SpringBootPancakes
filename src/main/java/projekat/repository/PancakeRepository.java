package projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projekat.model.Pancake;

@Repository
public interface PancakeRepository extends JpaRepository<Pancake, Integer>{
	List<Pancake> findByOrderId(Integer orderId);
}
