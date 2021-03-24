package com.poc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.model.Comment;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	Page<Comment> findByVehicleId(Long vehicleId, Pageable pageable);

	Optional<Comment> findByIdAndVehicleId(Long id, Long vehicleId);
}
