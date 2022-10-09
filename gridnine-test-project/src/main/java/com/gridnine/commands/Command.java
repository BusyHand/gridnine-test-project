package com.gridnine.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Commands that provide all possible filtering functions in the program. It is
 * assumed that they come from other sources. For example as http request or
 * json object. The commands themselves are written by the program in the file
 * {@link src.main.resources.Command-Open-API.txt} thanks to this they can be
 * conveniently provided to anyone. Something like OPEN API
 */
@Getter
@AllArgsConstructor
public enum Command {

	DEPARTURE_EARLIER_CURRENT("departure_earlier_current", "Вылет до текущего момента времени"),

	ARRIVAL_EARLIER_DEPARTURE("arrival_earlier_departure", "Сегменты с датой прилёта раньше даты вылета"),

	GROUND_TIME_EXCEEDS_TWO_HOURS("ground_time_exceeds_two_hours",
			"Общее время, проведённое на земле превышает два часа");

	private final String name;
	private final String description;

}
