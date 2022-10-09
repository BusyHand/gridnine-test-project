package com.gridnine.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import lombok.EqualsAndHashCode;

/**
 * Bean that represents a flight segment.
 */
@EqualsAndHashCode
public class Segment {
	private final LocalDateTime departure;

	private final LocalDateTime arrival;

	public Segment(final LocalDateTime departure, final LocalDateTime arrival) {
		this.departure = Objects.requireNonNull(departure);
		this.arrival = Objects.requireNonNull(arrival);
	}

	public LocalDateTime getDeparture() {
		return departure;
	}

	public LocalDateTime getArrival() {
		return arrival;
	}

	/**
	 * For minimize code in filter class
	 */
	public boolean arrivalBeforeDeparture() {
		return arrival.isBefore(departure);
	}

	/**
	 * For minimize code in filter class
	 */
	public boolean departureBeforeNow() {
		return departure.isBefore(LocalDateTime.now());
	}

	@Override
	public String toString() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM-dd HH:mm");
		return "[↗" + departure.format(fmt) + "|↘" + arrival.format(fmt)
				+ ']' + " \t";
	}
}
