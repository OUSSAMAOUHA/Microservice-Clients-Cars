package com.ensaj.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NonNull;

@Data
public class VoitureRequest {
	public VoitureRequest() {
		// Default constructor
	}
	@NotBlank
	private String matricule;
	@NotBlank
	private String marque;
	private String model;
	@NonNull
	private Long client;

}
