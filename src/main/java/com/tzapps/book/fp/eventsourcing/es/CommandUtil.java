package com.tzapps.book.fp.eventsourcing.es;

public class CommandUtil {
	public final static <T> Aggregate<T> cmd(Class<T> type) {
		return new Aggregate<T>();
	}

	public final static <T> Aggregate<T> cmd(Class<T> type, String aggregateId, String actor, String correlationId, String timestamp) {
		return (Aggregate<T>) new Aggregate<T>()
				.setAgg(Aggregate.FIELD.aggregatId, aggregateId)
				.setAgg(Aggregate.FIELD.actor, actor)
				.setAgg(Aggregate.FIELD.correlationId, correlationId)
				.setAgg(Aggregate.FIELD.timestamp, timestamp)
				;
	}

	public final static <T> Aggregate<T> cmd(Class<T> type, Aggregate<T> from) {

		return (Aggregate<T>) new Aggregate<T>()
				.setAgg(Aggregate.FIELD.aggregatId, from.getAgg(Aggregate.FIELD.aggregatId))
				.setAgg(Aggregate.FIELD.actor, from.getAgg(Aggregate.FIELD.actor))
				.setAgg(Aggregate.FIELD.correlationId, from.getAgg(Aggregate.FIELD.correlationId))
				.setAgg(Aggregate.FIELD.timestamp, from.getAgg(Aggregate.FIELD.timestamp))
				;
	}
}
