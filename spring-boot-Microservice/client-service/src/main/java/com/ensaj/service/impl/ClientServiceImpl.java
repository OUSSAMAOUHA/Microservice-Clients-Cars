package com.ensaj.service.impl;

import static com.ensaj.config.AppConstants.ID;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ensaj.dto.ClientRequest;
import com.ensaj.exception.ResourceNotFoundException;
import com.ensaj.model.Client;
import com.ensaj.repo.ClientRepository;
import com.ensaj.service.ClientService;;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client save(ClientRequest clientRequest) {
		Client client = new Client();
		BeanUtils.copyProperties(clientRequest, client);
		return clientRepository.save(client);
	}

	@Override
	public void deleteById(Long id) {
		clientRepository.deleteById(id);
	}

	@Override
	public Optional<Client> findById(Long id) {
		return clientRepository.findById(id);
	}

	@Override
	public Page<Client> findAll(Pageable pageable) {
		return clientRepository.findAll(pageable);
	}

	@Override
	public void update(Long id, ClientRequest clientRequest) {
		findById(id).map(p -> {
			BeanUtils.copyProperties(clientRequest, p);
			return clientRepository.save(p);
		}).orElseThrow(() -> new ResourceNotFoundException("Client", ID, id));
	}
}
