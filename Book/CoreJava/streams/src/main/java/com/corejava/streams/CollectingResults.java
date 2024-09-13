package com.corejava.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {

	public static Stream<String> noVowels() throws IOException {
		Path path = Paths.get("/Users/sumankhara/Documents/PersonalProjects/Java/alice.txt");
		var contents = Files.readString(path);
		
		List<String> wordList = List.of(contents.split("\\PL+"));
		return wordList.stream().map(s -> s.replaceAll("[AEIOUaeiou]", ""));
	}
	
	public static <T> void show(String label, Set<T> set) {
		System.out.println(label + ": " + set.getClass().getName());
		System.out.println("[" + set.stream().limit(10).map(Object::toString).collect(Collectors.joining(",")) + "]");
	}
	
	public static void main(String[] args) throws IOException {
		Iterator<Integer> iterate = Stream.iterate(0, n -> n + 1).limit(10).iterator();
		while(iterate.hasNext()) {
			System.out.println(iterate.next());
		}
		
		Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
		System.out.println("Object array: " + numbers);
		
		try {
			var number = (Integer) numbers[0];
			System.out.println("number: " + number);
			System.out.println("The following statement throws an exception");
			var numbers2 = (Integer[]) numbers;
		} catch(ClassCastException ex) {
			System.out.println(ex);
		}
		
		Integer[] numbers3 = Stream.iterate(1, n -> n + 1).limit(10).toArray(Integer[]::new);
		System.out.println("Integer array: " + numbers3);
		
		Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
		show("no vowels", noVowelSet);
		
		Set<String> noVowelTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
		show("noVowelTreeSet", noVowelTreeSet);
		
		String result = noVowels().limit(10).collect(Collectors.joining());
		System.out.println("Joining: " + result);
		result = noVowels().limit(10).collect(Collectors.joining(","));
		System.out.println("Joining with comma: " + result);
		
		IntSummaryStatistics summary = noVowels().collect(Collectors.summarizingInt(s -> s.length()));
		System.out.println("Average word length: " + summary.getAverage());
		System.out.println("Max word length:" + summary.getMax());
		
		System.out.println("for each:");
		noVowels().limit(10).forEach(System.out::println);
	}
}
