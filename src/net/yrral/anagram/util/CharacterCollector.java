package net.yrral.anagram.util;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CharacterCollector implements Collector<Character, StringBuffer, String> {
	private static final HashSet<Characteristics> CHARACTERISTICS = new HashSet<>();

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
}
