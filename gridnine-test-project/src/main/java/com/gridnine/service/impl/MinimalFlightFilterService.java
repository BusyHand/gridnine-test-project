package com.gridnine.service.impl;

import static com.gridnine.commands.Command.ARRIVAL_EARLIER_DEPARTURE;
import static com.gridnine.commands.Command.DEPARTURE_EARLIER_CURRENT;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gridnine.bpp.annotations.Filter;
import com.gridnine.models.Flight;
import com.gridnine.models.Segment;
import com.gridnine.service.FlightFilterHandler;

/**
 * A class with filtering methods, separated from
 * {@link MaximumFlightFilterService} class to demonstrate the ability to group
 * filters by different properties. instead of writing each class under one
 * filter or one class under all filters
 */
@Component
public class MinimalFlightFilterService implements FlightFilterHandler {

	@Filter(DEPARTURE_EARLIER_CURRENT)
	private List<Flight> departureAtCurrent(List<Flight> flights) {
		return flights.stream()
				.filter(flight -> {
					return flight.getSegments().stream()
							.anyMatch(Segment::departureBeforeNow);
				})
				.collect(toList());
	}

	@Filter(ARRIVAL_EARLIER_DEPARTURE)
	private List<Flight> arrivalEarlierDeparture(List<Flight> flights) {
		return flights.stream()
				.filter(flight -> {
					return flight.getSegments().stream()
							.anyMatch(Segment::arrivalBeforeDeparture);
				})
				.collect(toList());
	}
}
