package com.ensaj.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "voiture")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Voiture extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1493867573442690205L;
	@NonNull
	private String matricule;

	@NonNull
	private String marque;

	@NonNull
	private String model;

	@NonNull
	private Long client;


}
