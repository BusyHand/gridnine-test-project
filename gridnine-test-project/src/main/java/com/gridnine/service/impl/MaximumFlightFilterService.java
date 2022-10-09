package com.gridnine.service.impl;

import static com.gridnine.commands.Command.GROUND_TIME_EXCEEDS_TWO_HOURS;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gridnine.bpp.annotations.Filter;
import com.gridnine.models.Flight;
import com.gridnine.service.FlightFilterHandler;

/**
 * A class with filtering methods, separated from
 * {@link MinimalFlightFilterService} class to demonstrate the ability to group
 * filters by their similar properties. Instead of writing each class under one
 * filter or one class under all filters
 */
@Component
public class MaximumFlightFilterService implements FlightFilterHandler {

	@Filter(GROUND_TIME_EXCEEDS_TWO_HOURS)
	private List<Flight> groundTimeExceedsTwoHours(List<Flight> flights) {
		return flights.stream()
				.filter(Flight::isGroundTimeMoreThanTwoHours)
				.collect(toList());
	}

}
