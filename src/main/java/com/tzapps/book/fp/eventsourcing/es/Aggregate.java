package com.tzapps.book.fp.eventsourcing.es;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class Aggregate<T> implements AggregateUtil<T> {

	public enum FIELD {aggregatId, actor, correlationId, timestamp}

	Map<String, Object> data = new HashMap<>();

	@Override
	public AggregateUtil<T> set(T key, Object value) {
		data.put(key.toString(), value);
		return this;
	}

	@Override
	public Object get(T key) {
		return data.getOrDefault(key.toString(), null);
	}

	@Override
	public AggregateUtil<T> setAgg(FIELD key, Object value) {
		data.put(key.name(), value);
		return this;
	}

	@Override
	public Map<String, Object> getData() {
		return data;
	}

	@Override
	public Object getAgg(FIELD key) {
		return data.getOrDefault(key.name(), null);
	}
}
