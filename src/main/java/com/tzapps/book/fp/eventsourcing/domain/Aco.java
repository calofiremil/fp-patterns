package com.tzapps.book.fp.eventsourcing.domain;


public enum Aco {
	name, period;

	public enum AcoCreated {name, period}
	public enum AcoNameUpdated {name}
	public enum AcoPeriodUpdated {period}

}