package com.ensaj.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ensaj.model.Voiture;

import java.util.List;

@Repository
public interface VoitureRepository extends PagingAndSortingRepository<Voiture, Long> {

    List<Voiture> findByClient(Long client);
}
