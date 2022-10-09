package com.gridnine.testing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.gridnine.commands.Command;
import com.gridnine.factories.FlightBuilder;
import com.gridnine.factories.FlightFilterFactory;
import com.gridnine.models.Flight;

/**
 * Test class 
 */
@Component
public class TestExamples {

	@Autowired
	private FlightBuilder flightBuilder;

	@Autowired
	private FlightFilterFactory flightFilterFactory;

	@EventListener(ContextRefreshedEvent.class)
	public void test() {
		System.out.println();
		List<Flight> createdFlights = flightBuilder.createFlights();

		filter(Command.ARRIVAL_EARLIER_DEPARTURE, createdFlights);

		filter(Command.DEPARTURE_EARLIER_CURRENT, createdFlights);

		filter(Command.GROUND_TIME_EXCEEDS_TWO_HOURS, createdFlights);
	}

	public void filter(Command command, List<Flight> createdFlights) {
		List<Flight> filteredFlights = flightFilterFactory.filterFlights(command, createdFlights);
		print(command, filteredFlights);
	}

	public void print(Command command, List<Flight> flight) {
		System.out.println(command.getDescription());
		flight.forEach(System.out::print);
		System.out.println();
	}

}
