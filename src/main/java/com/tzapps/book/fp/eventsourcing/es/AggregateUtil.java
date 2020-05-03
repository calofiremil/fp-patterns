package com.tzapps.book.fp.eventsourcing.es;

import java.util.Map;
import java.util.Optional;

public interface AggregateUtil<T> {
	Object get(T key);

	AggregateUtil<T> set(T key, Object value);

	Object getAgg(Aggregate.FIELD key);

	AggregateUtil<T> setAgg(Aggregate.FIELD key, Object value);

	Map<String, Object> getData();


	default <T> String getString(AggregateUtil<T> aggregate, T key) {
		return Optional.ofNullable(aggregate.getData().get(key.toString())).map(Object::toString).orElse(null);
	}

	default <T> Integer getInteger(AggregateUtil<T> aggregate, T key) {
		return Optional.ofNullable(aggregate.getData().get(key.toString())).map(t -> Integer.valueOf(t.toString())).orElse(null);
	}

	default <T> Long getLong(AggregateUtil<T> aggregate, T key) {
		return Optional.ofNullable(aggregate.getData().get(key.toString())).map(t -> Long.valueOf(t.toString())).orElse(null);
	}
}
