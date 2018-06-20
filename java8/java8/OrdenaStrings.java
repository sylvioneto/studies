package java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class OrdenaStrings {

	public static void main(String[] args) {

		// sorting words based on length
		System.out.println("Sorting using new J8 sort in List");
		List<String> wordList = new ArrayList<String>();
		wordList.add("general eletric");
		wordList.add("chevrolet motors");
		wordList.add("coca cola");
		wordList.add("samsung");
		// sort without lambda
		wordList.sort(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.length() < s2.length())
					return -1;
				if (s1.length() > s2.length())
					return 1;
				return 0;
			}
		});

		// sort with lambda
		wordList.sort((s1, s2) -> {
			if (s1.length() < s2.length())
				return -1;
			if (s1.length() > s2.length())
				return 1;
			return 0;
		});

		// sort with lambda version 2
		wordList.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

		// sort with lambda version 3
		wordList.sort(Comparator.comparing(s -> s.length()));

		// sort with lambda version 4
		wordList.sort(Comparator.comparing(String::length));

		System.out.println(wordList);
		System.out.println("--------------------------");

		// for each before J8
		System.out.println("Printing using old for each");
		for (String word : wordList) {
			System.out.println(word);
		}
		System.out.println("--------------------------");

		// for each in J8
		System.out.println("Printing using J8 but without lambda");
		wordList.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		System.out.println("--------------------------");

		// for each in J8 lambda
		System.out.println("Printing using J8 with Lambda");
		wordList.forEach(myString -> System.out.println(myString));
		System.out.println("--------------------------");

	}

}