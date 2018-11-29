package net.yrral.anagram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary {
	Map<String, Set<String>> anagramMap = new HashMap<>();

	Dictionary(String filename) throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			lines.forEach(this::addWord);
		}
	}
	
	public Set<String> getAnagrams(String word) {
		return anagramMap.get(canonicalize(word));
	}

	private void addWord(String word) {
		String canonical = canonicalize(word);
		anagramMap.computeIfAbsent(canonical, key -> new HashSet<>());
		anagramMap.get(canonical).add(word);
	}
	
	private String canonicalize(String word) {
		return Stream.of(word.toLowerCase().split("")).sorted().collect(Collectors.joining(""));
	}
}
