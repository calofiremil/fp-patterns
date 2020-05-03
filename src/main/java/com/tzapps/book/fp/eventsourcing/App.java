package com.tzapps.book.fp.eventsourcing;


import com.tzapps.book.fp.eventsourcing.domain.Aco;
import com.tzapps.book.fp.eventsourcing.domain.Aco.AcoCreated;
import com.tzapps.book.fp.eventsourcing.es.Aggregate;
import com.tzapps.book.fp.eventsourcing.es.AggregateUtil;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.tzapps.book.fp.eventsourcing.domain.Aco.*;
import static com.tzapps.book.fp.eventsourcing.domain.Aco.AcoNameUpdated;
import static com.tzapps.book.fp.eventsourcing.domain.AcoCommand.acoNameUpdated;
import static com.tzapps.book.fp.eventsourcing.domain.AcoEvent.applyAcoNameUpdated;
import static com.tzapps.book.fp.eventsourcing.es.CommandUtil.cmd;
import static com.tzapps.book.fp.eventsourcing.es.EventUtil.applyEvent;

public class App {

	public static void main(String[] args) {

		//Create simple cmd
		var acoNameUpdated = acoNameUpdated(UUID.randomUUID().toString(), "System", UUID.randomUUID().toString(), LocalDateTime.now().toString())
				.set(AcoNameUpdated.name, "MyName");

        System.out.println("******************************\n");
		System.out.println(acoNameUpdated);
		System.out.println(acoNameUpdated.get(AcoNameUpdated.name));

		//Create new CMD and Apply the event on a new Aggregate
		AggregateUtil<AcoCreated> createAco = cmd(AcoCreated.class, UUID.randomUUID().toString(), "System", UUID.randomUUID().toString(), LocalDateTime.now().toString())
				.set(AcoCreated.name, "MyName")
				.set(AcoCreated.period, "18");

		var newAco = applyEvent(createAco, new Aggregate<Aco>());
        System.out.println("******************************\n");
		System.out.println(newAco);


		//Create new CMD and Apply the event on a existing Aggregate
		var updateName = acoNameUpdated(UUID.randomUUID().toString(), "System", UUID.randomUUID().toString(), LocalDateTime.now().toString())
				.set(AcoNameUpdated.name, "My new Aco Name");

		var newAco2 = applyAcoNameUpdated(updateName, newAco);
		System.out.println("******************************\n");
		System.out.println(newAco2);


		//Create new CMD and Apply the event on a existing Aggregate
		var updatePeriod = cmd(AcoPeriodUpdated.class, UUID.randomUUID().toString(), "System", UUID.randomUUID().toString(), LocalDateTime.now().toString())
				.set(AcoPeriodUpdated.period, 55);

		var newAco3 = applyEvent(AcoPeriodUpdated.class, updatePeriod.getData(), Aco.class, newAco.getData());
        System.out.println("******************************\n");
		System.out.println(newAco3);
	}
}



