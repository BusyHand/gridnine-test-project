package com.gridnine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gridnine.commands.Command;
import com.gridnine.factories.FlightBuilder;
import com.gridnine.factories.FlightFilterFactory;
import com.gridnine.models.Flight;

@SpringBootTest
class FilterFlightsTest {

	@Autowired
	private FlightBuilder flightBuilder;

	@Autowired
	private FlightFilterFactory flightFilterFactory;

	private final LocalDateTime referencePoint = LocalDateTime.now().plusDays(3);

	@Test
	void arrival_earlier_departure() {
		// A flight that departs before it arrives
		Flight targetFlight = flightBuilder.createFlight(referencePoint, referencePoint.minusHours(6));
		List<Flight> flights = Arrays.asList(
				targetFlight,
				// Another flights
				flightBuilder.createFlight(referencePoint, referencePoint.plusHours(6)),
				flightBuilder.createFlight(referencePoint, referencePoint.plusHours(4)));

		List<Flight> filterFlights = flightFilterFactory.filterFlights(Command.ARRIVAL_EARLIER_DEPARTURE, flights);
		assertEquals(filterFlights.get(0), targetFlight);
	}

	@Test
	void departure_earlier_current() {
		// A flight that departs before current time
		Flight targetFlight = flightBuilder.createFlight(referencePoint.minusDays(6), referencePoint);
		List<Flight> flights = Arrays.asList(
				targetFlight,
				// Another flights
				flightBuilder.createFlight(referencePoint, referencePoint.plusHours(6)),
				flightBuilder.createFlight(referencePoint, referencePoint.plusHours(4)));

		List<Flight> filterFlights = flightFilterFactory.filterFlights(Command.DEPARTURE_EARLIER_CURRENT, flights);
		assertEquals(filterFlights.get(0), targetFlight);
	}

	@Test
	void ground_time_exceeds_two_hours() {
		// A flight that have more than 2 hours on the ground
		Flight targetFlight = flightBuilder.createFlight(referencePoint, referencePoint.plusHours(1),
				referencePoint.plusHours(6), referencePoint.plusHours(9));
		List<Flight> flights = Arrays.asList(
				targetFlight,
				// Another flights
				flightBuilder.createFlight(referencePoint, referencePoint.plusHours(6)),
				flightBuilder.createFlight(referencePoint, referencePoint.plusHours(4)));

		List<Flight> filterFlights = flightFilterFactory.filterFlights(Command.GROUND_TIME_EXCEEDS_TWO_HOURS, flights);
		assertEquals(filterFlights.get(0), targetFlight);
	}

}
