package com.tzapps.book.fp.eventsourcing.es;


import java.util.Map;
import java.util.Optional;

public interface IAggregate<T> {
	Object get(T key);

	IAggregate<T> set(T key, Object value);

	Object getAgg(Aggregate.FIELD key);

	IAggregate<T> setAgg(Aggregate.FIELD key, Object value);

	Map<String, Object> getData();


	default <T> String getString(IAggregate<T> aggregate, T key) {
		return Optional.ofNullable(aggregate.getData().get(key.toString())).map(Object::toString).orElse(null);
	}

	default <T> Integer getInteger(IAggregate<T> aggregate, T key) {
		return Optional.ofNullable(aggregate.getData().get(key.toString())).map(t -> Integer.valueOf(t.toString())).orElse(null);
	}

	default <T> Long getLong(IAggregate<T> aggregate, T key) {
		return Optional.ofNullable(aggregate.getData().get(key.toString())).map(t -> Long.valueOf(t.toString())).orElse(null);
	}
}
