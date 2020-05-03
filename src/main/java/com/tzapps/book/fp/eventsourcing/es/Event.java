package com.tzapps.book.fp.eventsourcing.es;

import java.util.Map;

/*
 *
 * this class is optional
 * you can always use:
 *            cmd(AcoCreated.class, UUID.randomUUID().toString(), "System", UUID.randomUUID().toString(), LocalDateTime.now().toString())
 *
 * */
public class Event {

	public static <T, U> Aggregate<U> applyEvent(IAggregate<T> event, Aggregate<U> agg) {
		agg.getData().putAll(event.getData());
		return agg;
	}

	public static <T, U> Map applyEvent(Class<T> eventType, Map event, Class<U> aggType, Map agg) {
		agg.putAll(event);
		return agg;
	}

}
