package java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExemploStreams {

	public static void main(String[] args) {

		// populate list
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(new Curso("Python", 45));
		cursos.add(new Curso("JavaScript", 150));
		cursos.add(new Curso("Java 8", 113));
		cursos.add(new Curso("C", 55));

		// sort by quantity using lambda
		cursos.sort(Comparator.comparing(c -> c.getAlunos()));

		// filter using Stream
		cursos.stream().filter(c -> c.getAlunos() >= 50).forEach(c -> System.out.println(c.getNome()));

		// transform to a Stream of String
		Stream<String> nomes = cursos.stream().map(c -> c.getNome());
		nomes.forEach(System.out::println);

		// Stream of integer using method reference
		cursos.stream().filter(c -> c.getAlunos() > 50).map(Curso::getAlunos).forEach(System.out::println);

		// Optional - Get the first element from a stream
		System.out.printf("first element: ");
		cursos.stream().filter(c -> c.getAlunos() > 50).findFirst().ifPresent(c -> System.out.println(c.getNome()));;

		// Optional - Get the any element from a stream
		System.out.printf("any element: ");
		cursos.stream().filter(c -> c.getAlunos() > 50).findAny().ifPresent(c -> System.out.println(c.getNome()));;
		
		// average
		System.out.printf("average: ");
		cursos.stream().mapToInt(c -> c.getAlunos()).average().ifPresent(System.out::println);

		// Stream to List
		List<Curso> cursosList = cursos.stream().filter(c -> c.getAlunos() >= 100).collect(Collectors.toList());
		System.out.printf("stream to list: ");
		cursosList.forEach(c -> System.out.println(c.getNome()));
	}

}
