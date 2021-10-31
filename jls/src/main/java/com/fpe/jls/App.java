package com.fpe.jls;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

import com.fpe.jls.model.Persons;
import com.fpe.jls.model.Products;

/**
 * Pruebas con Labdas y Streams
 *
 */
public class App {
	public static void main(String[] args) {
		Persons p1 = new Persons(1, "Mito", LocalDate.of(1991, 1, 21));
		Persons p2 = new Persons(2, "Code", LocalDate.of(1991, 2, 21));
		Persons p3 = new Persons(3, "Jaime", LocalDate.of(1980, 6, 23));
		Persons p4 = new Persons(4, "Duke", LocalDate.of(2019, 5, 15));
		Persons p5 = new Persons(5, "James", LocalDate.of(2010, 1, 4));

		Products pr1 = new Products(1, "Ceviche", 15.0);
		Products pr2 = new Products(1, "Chilaquies", 25.50);
		Products pr3 = new Products(1, "Bandeja Paisa", 35.50);
		Products pr4 = new Products(1, "Ceviche", 15.0);

		List<Persons> persons = Arrays.asList(p1, p2, p3, p4, p5);
		List<Products> products = Arrays.asList(pr1, pr2, pr3, pr4);
		
		// for (paradigma imperativo)
		
//		for (Persons p : persons) {
//			System.out.println(p);
//		}
		
		// Lambda (función anónima, paradigma funcional)
		
//		persons.forEach(p -> System.out.println(p));

		// Lambda si el parametro de la iteración (p) es el mismo a imprimir, 
		// se puede omitir. A eso se le llama Método de referencia
		
//		persons.forEach(System.out::println);
		
		// 1.- Filter (param: Predicate)
		// stream permite trabajar con colecciones de una forma más cómoda
		// p.e. Mostremos todas las personas mayores a 18 años
		
		persons.stream().filter(null);
		
		
		
		
		
		
			
		}
	
	public static int getAge(LocalDate birthDate) {
		return Period.between(birthDate, LocalDate.now()).getYears();
		
	}
	
	public static void printList(List<?> list) {
		list.forEach(System.out::println);
	}
	
	
}


