package com.ensaj.controller;

import static com.ensaj.config.AppConstants.ID;

import javax.validation.Valid;

import com.ensaj.configs.MQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ensaj.config.AppConstants;
import com.ensaj.dto.ApiResponse;
import com.ensaj.dto.ClientRequest;
import com.ensaj.exception.ResourceNotFoundException;
import com.ensaj.model.Client;
import com.ensaj.service.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/clients")
@RequiredArgsConstructor
public class ClientController {
	@Autowired
	private RabbitTemplate template;
	private final ClientService clientService;
	@GetMapping
	public Page<Client> getClientList(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		Pageable paging = PageRequest.of(page, size);
		return clientService.findAll(paging);
	}
	@GetMapping("/{id}")
	public Client getClient(@PathVariable Long id) {
		return clientService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client", ID, id));
	}

	@PostMapping
	public ResponseEntity<?> createClient(@Valid @RequestBody ClientRequest client) {
		clientService.save(client);
		return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateClient(@PathVariable Long id, @Valid @RequestBody ClientRequest clientRequest) {
		clientService.update(id, clientRequest);
		return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable Long id) {
		return clientService.findById(id).map(p -> {
			clientService.deleteById(id);
			return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.SUCCESS));
		}).orElseThrow(() -> new ResourceNotFoundException("Client", ID, id));
	}
	@PostMapping("/send-id")
	public ResponseEntity<String> sendClientId(@RequestParam("id") String id) {
		// Your logic here
		template.convertAndSend(MQConfig.EXCHANGEREQUEST, MQConfig.ROUTING_KEY_REQUEST, id);
		return ResponseEntity.ok("ID sent successfully!");
	}
}
