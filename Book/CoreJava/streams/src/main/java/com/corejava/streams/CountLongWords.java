package com.corejava.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CountLongWords {

	public static void main(String[] args) throws IOException {
		var contents = Files.readString(Paths.get("/Users/sumankhara/Documents/PersonalProjects/Java/alice.txt"));
		
		List<String> words = List.of(contents.split("\\PL+"));
		
		long count = 0;
		for(String word: words) {
			if(word.length() > 12) {
				count++;
			}
		}
		System.out.println(count);
		
		count = words.stream().filter(w -> w.length() > 12).count();
		System.out.println(count);
		
		count = words.parallelStream().filter(w -> w.length() > 12).count();
		System.out.println(count);
	}
}
