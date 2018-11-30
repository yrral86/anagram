package net.yrral.anagram.util;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CharacterCollector implements Collector<Character, StringBuilder, String> {
	private static final Set<Characteristics> CHARACTERISTICS = new HashSet<>();

	@Override
	public Supplier<StringBuilder> supplier() {
		return StringBuilder::new;
	}

	@Override
	public BiConsumer<StringBuilder, Character> accumulator() {
		return (buffer, character) ->
			buffer.append(character);
	}

	@Override
	public BinaryOperator<StringBuilder> combiner() {
		return (buffer1, buffer2) -> buffer1.append(buffer2);
	}

	@Override
	public Function<StringBuilder, String> finisher() {
		return StringBuilder::toString;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return CHARACTERISTICS;
	}
}
