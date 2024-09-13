package com.corejava.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DownstreamCollectors {

	public static class City {

		private String name;

		private String state;

		private int population;

		public City(String name, int population, String state) {
			super();
			this.name = name;
			this.state = state;
			this.population = population;
		}

		public String getName() {
			return name;
		}

		public String getState() {
			return state;
		}

		public int getPopulation() {
			return population;
		}
	}

	public static Stream<City> readCities(String filename) throws IOException {
		return Files.lines(Paths.get(filename)).map(l -> l.split(","))
				.map(a -> new City(a[0], Integer.parseInt(a[1]), a[2]));
	}

	public static void main(String[] args) throws IOException {
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());

		Map<String, Set<Locale>> countryToLocaleSet = locales
				.collect(Collectors.groupingBy(Locale::getCountry, Collectors.toSet()));
		System.out.println("countryToLocaleSet: " + countryToLocaleSet);

		Stream<City> cities = readCities("/Users/sumankhara/Documents/PersonalProjects/Java/cities.txt");
		Map<String, Integer> stateToCityPopulation = cities
				.collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
		System.out.println("stateToCityPopulation: " + stateToCityPopulation);

		cities = readCities("/Users/sumankhara/Documents/PersonalProjects/Java/cities.txt");
		Map<String, Optional<String>> stateToLongestCityName = cities.collect(Collectors.groupingBy(City::getState,
				Collectors.mapping(City::getName, Collectors.maxBy(Comparator.comparing(String::length)))));
		System.out.println("stateToLongestCityName: " + stateToLongestCityName);
		
		cities = readCities("/Users/sumankhara/Documents/PersonalProjects/Java/cities.txt");
		Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities
				.collect(Collectors.groupingBy(City::getState, Collectors.summarizingInt(City::getPopulation)));
		System.out.println(stateToCityPopulationSummary.get("Maharashtra"));
	}
}
