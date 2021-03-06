package net.yrral.anagram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Stream;

import net.yrral.anagram.util.CharacterCollector;

public class Dictionary {
	private final Map<String, Set<String>> anagramMap = new ConcurrentHashMap<>();
	private static final Set<String> emptySetSingleton = new HashSet<>();
	private static final Collector<Character, StringBuilder, String> characterCollector = new CharacterCollector();
	/**
	 * Anagram Dictionary
	 * @param filename Path to the dictionary file.
	 * @throws IOException
	 */
	Dictionary(String filename) throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			lines.parallel().forEach(this::addWord);
		}
	}

	/**
	 * @param word Word to find anagrams for.
	 * @return Set of words that anagram to the word parameter.
	 * Returns the word itself if it is in the dictionary.
	 */
	public Set<String> getAnagrams(String word) {
		String canonical = canonicalize(word);
		return anagramMap.getOrDefault(canonical, emptySetSingleton);
	}

	private void addWord(String word) {
		String canonical = canonicalize(word);
		anagramMap.computeIfAbsent(canonical, this::emptySet);
		anagramMap.get(canonical).add(word);
	}

	private String canonicalize(String word) {
		return word.toLowerCase().chars().sorted().mapToObj(c -> (char)c).collect(characterCollector);
	}

	private Set<String> emptySet(String key) {
		return ConcurrentHashMap.newKeySet();
	}
}
