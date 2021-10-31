package com.fpe.jls;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fpe.jls.model.Persons;
import com.fpe.jls.model.Products;

/**
 * Pruebas con Labdas y Streams
 *
 */
public class App {
	public static void main(String[] args) {
		Persons p1 = new Persons(1, "Mito", LocalDate.of(1991, 1, 21));
		Persons p2 = new Persons(2, "Code", LocalDate.of(1990, 2, 21));
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

		// 1.- Filter (param: Predicate) (Predicate = true/false)
		// stream permite trabajar con colecciones de una forma más cómoda
		// p.e. Mostremos todas las personas mayores a 18 años

		// filter devuelve otro stream, de ahí que usemos .collect(Collectors.toList()
		List<Persons> filtereedList1 = persons.stream().filter(p -> App.getAge(p.getBirthDate()) > 18)
				.collect(Collectors.toList());

//		App.printList(filtereedList1);

		// 2.- Map (param: Function) (Funcion = resultado devuelto de una llamada a una
		// función o método)

		// .map devuelve enteros pq recolecta sobre el último operador empleado,
		// en este caso getAge(p.getBirthDate() que devuelve un entero (la edad)
		// por lo que el tipo de la lista debe ser int
		List<Integer> filtereedList2 = persons.stream().map(p -> App.getAge(p.getBirthDate()))
				.collect(Collectors.toList());

//		App.printList(filtereedList2);

		// lo mismo que lo de arriba pero añadiéndole que sólo devuelva los mayores a 18
		// años,
		// es tan simple como añadirle el filtered antes del Map
		List<Integer> filtereedList3 = persons.stream().filter(p -> App.getAge(p.getBirthDate()) > 18)
				.map(p -> App.getAge(p.getBirthDate())).collect(Collectors.toList());

//		App.printList(filtereedList3);

		// hagamos ahora que nos devuelva los nombres de las personas con un prefijo

		List<String> filtereedList4 = persons.stream().map(p -> "Don " + p.getName()).collect(Collectors.toList());

//		App.printList(filtereedList4);

		// hagamos justo lo mismo que lo anterior pero creándonos una Function para
		// ello,
		// por si necesitamos añadir el prefijo en otras ocasiones

		Function<String, String> donFunction = name -> "Don " + name; // Funcion<Parámetro entrada, parámetro salida)

		List<String> filtereedList5 = persons.stream() // aquí devuelve personas
				.map(p -> p.getName()) // aquí ya devuelve String
				.map(donFunction) // aquí ya puedo llamar a donFuncion pq le estoy pasando el resultado de getName
									// que es un String
				.collect(Collectors.toList());

//		App.printList(filtereedList5);

		// mismo que lo anterior pero como map(p -> p.getName()) usa el mismo parámetro
		// a ambos lados tb se puede hacer así:
		List<String> filtereedList6 = persons.stream() // aquí devuelve personas
				.map(Persons::getName) // devuelve String del getter de Persons.getName()
				.map(donFunction) // aquí ya puedo llamar a donFuncion pq le estoy pasando el resultado de getName
									// que es un String
				.collect(Collectors.toList());

//		App.printList(filtereedList6);

		// 3.- Sorted (param: Comparator)

		// primero necesitamos crear un comparador dado que estamos trabajando con un
		// objeto (Persons)
		Comparator<Persons> byNameAsc = (Persons obj1, Persons obj2) -> obj1.getName().compareTo(obj2.getName());

		List<Persons> filtereedList7 = persons.stream().sorted(byNameAsc).collect(Collectors.toList());
		
//		App.printList(filtereedList7);
		
		//4.- Match (param: Predicate)   (No existe el match, sólo anyMatch, allMatch y noneMatch 
		
		// anyMatch: No valua todo el stream, termina en la primera coincidencia
		
		boolean resultado1 = persons.stream().anyMatch(p -> p.getName().startsWith("J")); //retorna True o False
		
//		System.out.println(resultado1);
		
		// allMatch: Evalua todo el stream bajo la condición (sólo es True si todo el predicate cumple la condición)
		
		boolean resultado2 = persons.stream().allMatch(p -> p.getName().startsWith("J")); //retorna True o False
		
//		System.out.println(resultado2);
		
		// noneMatch: Evalua todo el stream bajo la condición (sólo es True si todo el predicate NO cumple la condición)
		
		boolean resultado3 = persons.stream().noneMatch(p -> p.getName().startsWith("J")); //retorna True o False
		
//		System.out.println(resultado3);
		
		// Using Predicate
		// las últmas tres funciones empleadas con los Match utilizan el mismo Predicate, por lo que podríamos crear
		// un Predicate genérico para usarlo en dichos Match
		
		Predicate<Persons> startsWithPredicate = p -> p.getName().startsWith("J");
		
		boolean resultado4 = persons.stream().anyMatch(startsWithPredicate); //retorna True o False

//		System.out.println(resultado4);
		
		 // 5.- Limit/Skip
		
		int pageNumber = 0;
		int pageSize = 2;
		List<Persons> filteredList4 = persons.stream().skip(pageNumber * pageSize) // comienza desde la posición 0 de la lista
													  .limit(pageSize) // sólo se trae dos elementos
													  .collect(Collectors.toList());
		
//		System.out.println(filteredList4);
		
		// 6.- Collector
		// GroupBy
		
		products.stream().filter(p -> p.getPrice() > 20)
						 .collect(Collectors.groupingBy(Products::getPrice));

	}

	public static int getAge(LocalDate birthDate) {
		return Period.between(birthDate, LocalDate.now()).getYears();

	}

	public static void printList(List<?> list) {
		list.forEach(System.out::println);
	}

}
