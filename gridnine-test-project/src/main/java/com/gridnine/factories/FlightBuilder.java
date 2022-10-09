package com.gridnine.factories;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gridnine.models.Flight;
import com.gridnine.models.Segment;

import lombok.extern.slf4j.Slf4j;

/**
 * Factory class to get sample list of flights.
 */
@Component
@Slf4j
public class FlightBuilder {

	@Autowired
	private SegmentsBuilder segmentsBuilder;

	public Flight createFlight(final LocalDateTime... dates) {
		List<Segment> segments = segmentsBuilder.createSegments(dates);
		Flight flight = new Flight(segments);
		log.info("New Flight: {}", flight);
		return flight;
	}

	/**
	 * For testing purpose
	 */
	public List<Flight> createFlights() {
		LocalDateTime referencePoint = LocalDateTime.now().plusDays(3);
		return Arrays.asList(
				// A normal flight with two hour duration
				createFlight(referencePoint, referencePoint.plusHours(2)),

				// A normal multi segment flight
				createFlight(referencePoint, referencePoint.plusHours(2), referencePoint.plusHours(3),
						referencePoint.plusHours(5)),

				// A flight departing in the past
				createFlight(referencePoint.minusDays(6), referencePoint),

				// A flight that departs before it arrives
				createFlight(referencePoint, referencePoint.minusHours(6)),

				// A flight with more than two hours ground time
				createFlight(referencePoint, referencePoint.plusHours(2), referencePoint.plusHours(5),
						referencePoint.plusHours(6)),

				// Another flight with more than two hours ground time
				createFlight(referencePoint, referencePoint.plusHours(2), referencePoint.plusHours(3),
						referencePoint.plusHours(4), referencePoint.plusHours(6), referencePoint.plusHours(7)));
	}
}
