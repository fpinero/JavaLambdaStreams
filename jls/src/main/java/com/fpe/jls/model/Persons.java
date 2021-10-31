package com.fpe.jls.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persons {

	private Integer id;
	private String Name;
	private LocalDate birthDate;
	
}
