package com.corejava.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveTypeStreams {

	public static void show(String title, IntStream stream) {
		final int SIZE = 10;
		int[] firstElements = stream.limit(SIZE + 1).toArray();
		System.out.println(title + ":");
		for(int i = 0; i < firstElements.length; i++) {
			if(i > 0) {
				System.out.print(", ");
			}
			if(i < SIZE) {
				System.out.print(firstElements[i]);
			}
			else {
				System.out.print("...");
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		IntStream is1 = IntStream.generate(() -> (int) (Math.random() * 100));
		show("is1", is1);
		
		IntStream is2 = IntStream.range(5, 100);
		show("is2", is2);
		
		IntStream is3 = IntStream.rangeClosed(5, 100);
		show("is3", is3);
		
		Path path = Paths.get("/Users/sumankhara/Documents/PersonalProjects/Java/alice.txt");
		var contents = Files.readString(path);
		List<String> words = List.of(contents.split("\\PL+"));
		
		IntStream is4 = words.stream().mapToInt(String::length);
		show("is4", is4);
		
		Stream<Integer> integers = IntStream.range(5, 100).boxed();
		IntStream is5 = integers.mapToInt(Integer::intValue);
		show("is5", is5);
		
		Random random = new Random();
		IntStream is6 = random.ints();
		show("is6", is6);
		
		IntStream is7 = random.ints(1, 100);
		show("is7", is7);
	}
}
