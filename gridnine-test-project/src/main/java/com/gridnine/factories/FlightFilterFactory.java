package com.gridnine.factories;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gridnine.bpp.CommandFilterDefinitionsMap;
import com.gridnine.commands.Command;
import com.gridnine.models.Flight;
import com.gridnine.service.FlightFilterHandler;

/**
 * А factory that redirects the list of flights to the desired handler thanks to
 * {@link CommandFilterDefinitionsMap}
 */
@Component
public class FlightFilterFactory {

	@Autowired
	private Map<String, FlightFilterHandler> filterHandlers;

	public List<Flight> filterFlights(Command command, List<Flight> flights) {
		String handlerName = CommandFilterDefinitionsMap.getBeanName(command);
		FlightFilterHandler flightsFilter = filterHandlers.get(handlerName);
		return flightsFilter.handleCommand(command, flights);
	}

}
