package com.corejava.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelStream {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("/Users/sumankhara/Documents/PersonalProjects/Java/alice.txt");
		var contents = Files.readString(path);
		List<String> wordList = List.of(contents.split("\\PL+"));

		// Very bad code ahead
		var shortWords = new int[10];
		wordList.parallelStream().forEach(word -> {
			if (word.length() < 10) {
				shortWords[word.length()]++;
			}
		});
		System.out.println(Arrays.toString(shortWords));
		
		// Try again - the results will likely be different
		Arrays.fill(shortWords, 0);
		wordList.parallelStream().forEach(word -> {
			if (word.length() < 10) {
				shortWords[word.length()]++;
			}
		});
		System.out.println(Arrays.toString(shortWords));
		
		// Remedy: group and count
		Map<Integer, Long> shortWordCount = wordList.parallelStream()
				.filter(s -> s.length() < 10)
				.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		System.out.println(shortWordCount);
		
		// Downstream order not deterministic
		Map<Integer, List<String>> result = wordList.parallelStream().distinct()
				.collect(Collectors.groupingByConcurrent(String::length));
		System.out.println(result.get(14));
		
		Map<Integer, Long> wordCount = wordList.parallelStream()
				.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		System.out.println(wordCount);
	}
}
