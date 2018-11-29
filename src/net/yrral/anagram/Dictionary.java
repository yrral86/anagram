package net.yrral.anagram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary {
	private final Map<String, Set<String>> anagramMap = new HashMap<>();
	private static final Set<String> emptySetSingleton = new HashSet<>();
	private static final Collector<Character, StringBuffer, String> characterCollector = new Collector<Character, StringBuffer, String>() {
		private final HashSet<Characteristics> CHARACTERISTICS = new HashSet<>();

		@Override
		public Supplier<StringBuffer> supplier() {
			return StringBuffer::new;
		}

		@Override
		public BiConsumer<StringBuffer, Character> accumulator() {
			return (buffer, character) ->
				buffer.append(character);
		}

		@Override
		public BinaryOperator<StringBuffer> combiner() {
			return (buffer1, buffer2) -> buffer1.append(buffer2);
		}

		@Override
		public Function<StringBuffer, String> finisher() {
			return StringBuffer::toString;
		}

		@Override
		public Set<Characteristics> characteristics() {
			return CHARACTERISTICS;
		}
	};

	/**
	 * Anagram Dictionary
	 * @param filename Path to the dictionary file.
	 * @throws IOException
	 */
	Dictionary(String filename) throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			lines.forEach(this::addWord);
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
		return word.toLowerCase().chars().mapToObj(c -> (char)c).sorted().collect(characterCollector);
	}

	private Set<String> emptySet(String key) {
		return new HashSet<>();
	}
}
