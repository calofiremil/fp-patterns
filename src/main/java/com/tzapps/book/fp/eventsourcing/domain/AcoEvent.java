package com.tzapps.book.fp.eventsourcing.domain;

import com.tzapps.book.fp.eventsourcing.es.Aggregate;
import com.tzapps.book.fp.eventsourcing.es.AggregateUtil;

import static com.tzapps.book.fp.eventsourcing.domain.Aco.AcoNameUpdated;
import static com.tzapps.book.fp.eventsourcing.domain.Aco.name;

/*
 *
 * this class is optional
 * you can always use:
 *            cmd(AcoCreated.class, UUID.randomUUID().toString(), "System", UUID.randomUUID().toString(), LocalDateTime.now().toString())
 *
 * */
public class AcoEvent {

	public static Aggregate<Aco> applyAcoNameUpdated(AggregateUtil<AcoNameUpdated> event, Aggregate<Aco> agg) {
		Long period = event.getLong(agg, Aco.period);
		if (period > 10)
			agg.set(name, event.get(AcoNameUpdated.name).toString().toUpperCase());

		return agg;
	}

}
