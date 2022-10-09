package com.gridnine.service;

import java.lang.reflect.Method;
import java.util.List;

import com.gridnine.bpp.CommandFilterDefinitionsMap;
import com.gridnine.bpp.annotations.Filter;
import com.gridnine.commands.Command;
import com.gridnine.exception.IllegalReturnValueException;
import com.gridnine.models.Flight;

/**
 * Interface that invokes methods that are bound to commands by using
 * {@link CommandFilterDefinitionsMap}. Must implement every class that wants to
 * use the annotation {@link Filter}
 */
public interface FlightFilterHandler {

	default public List<Flight> handleCommand(Command command, List<Flight> flights) {
		Method method = CommandFilterDefinitionsMap.getMethod(command);
		method.setAccessible(true);
		return invokeFilter(method, flights);
	}

	@SuppressWarnings("unchecked")
	private List<Flight> invokeFilter(Method method, List<Flight> flights) {
		try {
			Object filteredFlights = method.invoke(this, flights);
			if (filteredFlights instanceof List<?>) {
				return (List<Flight>) filteredFlights;
			}
			throw new IllegalReturnValueException("Return type of method: " + method.getName()
					+ " must be cast to List<Flight>");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
