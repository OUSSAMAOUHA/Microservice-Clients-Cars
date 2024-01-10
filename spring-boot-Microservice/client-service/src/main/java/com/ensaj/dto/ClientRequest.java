package com.ensaj.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClientRequest {
	@NotBlank
	private String name;
	@NotBlank
	private String age;

}
