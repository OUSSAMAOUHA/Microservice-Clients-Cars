package com.ensaj.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ensaj.model.Client;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

}
