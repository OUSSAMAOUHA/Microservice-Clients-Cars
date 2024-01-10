package com.ensaj.controller;

import static com.ensaj.config.AppConstants.ID;

import javax.validation.Valid;

import com.ensaj.linstener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ensaj.config.AppConstants;
import com.ensaj.dto.ApiResponse;
import com.ensaj.dto.VoitureRequest;
import com.ensaj.exception.ResourceNotFoundException;
import com.ensaj.model.Voiture;
import com.ensaj.service.VoitureService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(path = "/api/voitures")
@RequiredArgsConstructor
public class VoitureController {

	private final VoitureService voitureService;
	@Autowired
	private MessageListener messageListener;
	@GetMapping
	public Page<Voiture> getVoitureList(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		Pageable paging = PageRequest.of(page, size);
		return voitureService.findAll(paging);
	}

	@GetMapping("/{id}")
	public Voiture getVoiture(@PathVariable Long id) {
		return voitureService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Voiture", ID, id));
	}

	@PostMapping
	public ResponseEntity<?> createVoiture(@Valid @RequestBody VoitureRequest voiture) {
		voitureService.save(voiture);
		return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateVoiture(@PathVariable Long id, @Valid @RequestBody VoitureRequest voitureRequest) {
		voitureService.update(id, voitureRequest);
		return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVoiture(@PathVariable Long id) {
		return voitureService.findById(id).map(p -> {
			voitureService.deleteById(id);
			return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
		}).orElseThrow(() -> new ResourceNotFoundException("Voiture", ID, id));
	}
	@GetMapping("/temporarily-received")
	public ResponseEntity<List<Voiture>> getTemporarilyReceivedVoitures() {
		List<Voiture> temporarilyReceivedVoitures = messageListener.getLastReceivedVoitures();
		return ResponseEntity.ok().body(temporarilyReceivedVoitures);
	}
}
