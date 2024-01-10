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
@Table(name = "client")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Client extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1493867573442690205L;
	@NonNull
	private String name;

	@NonNull
	private String age;



}
