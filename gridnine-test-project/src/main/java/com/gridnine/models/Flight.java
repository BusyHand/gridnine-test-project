package com.gridnine.models;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;

/**
 * Bean that represents a flight.
 */
@EqualsAndHashCode
public class Flight {
	private final List<Segment> segments;

	public Flight(final List<Segment> segs) {
		segments = segs;
	}

	public List<Segment> getSegments() {
		return segments;
	}

	/**
	 * For minimize code in filter class
	 */
	public boolean isGroundTimeMoreThanTwoHours() {
		if (segments.size() <= 1)
			return false;
		long sumMinutes = 0;
		LocalDateTime arrival = segments.get(0).getArrival();
		for (int i = 1; i < segments.size() && sumMinutes < 120; i++) {
			Segment segment = segments.get(i);
			LocalDateTime departure = segment.getDeparture();
			sumMinutes += arrival.until(departure, ChronoUnit.MINUTES);
			arrival = segment.getArrival();
		}
		return sumMinutes > 120 ? true : false;
	}

	@Override
	public String toString() {
		return segments.stream().map(Object::toString)
				.collect(Collectors.joining(" ")) + "\n";
	}
}