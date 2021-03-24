package com.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.poc.exception.ResourceNotFoundException;
import com.poc.model.Comment;
import com.poc.repository.CommentRepository;
import com.poc.repository.VehicleRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value = "Comment", description = "Rest API for Comment operations", tags = "Comment API")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@ApiOperation(value = "Get paginated All Comments on Vehicle")
	@GetMapping("/vehicles/{vehicleId}/comments")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Page<Comment> getAllCommentsByVehicleId(@PathVariable(value = "vehicleId") Long vehicleId,
			Pageable pageable) {
		return commentRepository.findByVehicleId(vehicleId, pageable);
	}

	@ApiOperation(value = "Post Comment on Vehicle")
	@PostMapping("/vehicles/{vehicleId}/comments")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Comment createComment(@PathVariable(value = "vehicleId") Long vehicleId,
			@Valid @RequestBody Comment comment) {
		return vehicleRepository.findById(vehicleId).map(vehicle -> {
			comment.setVehicle(vehicle);
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("VehicleId " + vehicleId + " not found"));
	}

	@ApiOperation(value = "Update Comment on Vehicle")
	@PutMapping("/vehicles/{vehicleId}/comments/{commentId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Comment updateComment(@PathVariable(value = "vehicleId") Long vehicleId,
			@PathVariable(value = "commentId") Long commentId, @Valid @RequestBody Comment commentRequest) {
		if (!vehicleRepository.existsById(vehicleId)) {
			throw new ResourceNotFoundException("VehicleId " + vehicleId + " not found");
		}

		return commentRepository.findById(commentId).map(comment -> {
			comment.setComment_data(commentRequest.getComment_data());
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
	}

	@ApiOperation(value = "Delete Comment by ID on Vehicle")
	@DeleteMapping("/vehicles/{vehicleId}/comments/{commentId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> deleteComment(@PathVariable(value = "vehicleId") Long vehicleId,
			@PathVariable(value = "commentId") Long commentId) {
		return commentRepository.findByIdAndVehicleId(commentId, vehicleId).map(comment -> {
			commentRepository.delete(comment);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				"Comment not found with id " + commentId + " and vehicleId " + vehicleId));
	}

}
