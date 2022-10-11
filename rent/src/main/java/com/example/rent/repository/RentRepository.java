package com.example.rent.repository;
import com.example.rent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.rent.model.Rent;
import java.util.List;
import java.util.UUID;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
	Rent findRentById(UUID id);
	List<Rent> findAllAcquisitionsByUser(User user);
}
