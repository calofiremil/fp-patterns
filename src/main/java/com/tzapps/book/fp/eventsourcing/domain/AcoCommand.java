package com.tzapps.book.fp.eventsourcing.domain;

import com.tzapps.book.fp.eventsourcing.es.Aggregate;
import com.tzapps.book.fp.eventsourcing.es.Command;


/*
*
* this class is optional
* you can always use:
*            cmd(AcoCreated.class, UUID.randomUUID().toString(), "System", UUID.randomUUID().toString(), LocalDateTime.now().toString())
*
 * */
public class AcoCommand extends Command {
	public final static Aggregate<Aco.AcoCreated> acoCreated(String aggregateId, String actor, String correlationId, String timestamp) {
		return cmd(Aco.AcoCreated.class, aggregateId, actor, correlationId, timestamp);
	}

	public final static Aggregate<Aco.AcoNameUpdated> acoNameUpdated(String aggregateId, String actor, String correlationId, String timestamp) {
		return cmd(Aco.AcoNameUpdated.class, aggregateId, actor, correlationId, timestamp);
	}
}
