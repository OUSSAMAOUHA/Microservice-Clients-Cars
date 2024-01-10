package com.ensaj.service.impl;

import static com.ensaj.config.AppConstants.ID;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ensaj.dto.VoitureRequest;
import com.ensaj.exception.ResourceNotFoundException;
import com.ensaj.model.Voiture;
import com.ensaj.repo.VoitureRepository;
import com.ensaj.service.VoitureService;;

@Service
public class VoitureServiceImpl implements VoitureService {

	@Autowired
	private VoitureRepository voitureRepository;

	@Override
	public Voiture save(VoitureRequest voitureRequest) {
		Voiture voiture = new Voiture();
		BeanUtils.copyProperties(voitureRequest, voiture);
		return voitureRepository.save(voiture);
	}

	@Override
	public void deleteById(Long id) {
		voitureRepository.deleteById(id);
	}

	@Override
	public Optional<Voiture> findById(Long id) {
		return voitureRepository.findById(id);
	}

	@Override
	public Page<Voiture> findAll(Pageable pageable) {
		return voitureRepository.findAll(pageable);
	}

	@Override
	public void update(Long id, VoitureRequest voitureRequest) {
		findById(id).map(p -> {
			BeanUtils.copyProperties(voitureRequest, p);
			return voitureRepository.save(p);
		}).orElseThrow(() -> new ResourceNotFoundException("Voiture", ID, id));
	}
}
