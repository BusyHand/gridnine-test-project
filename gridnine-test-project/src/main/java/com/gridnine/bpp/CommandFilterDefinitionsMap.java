package com.gridnine.bpp;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.gridnine.commands.Command;
import com.gridnine.models.FilterDefenition;

/**
 * Class for substitution to commands of their handlers. {@link FilterInjectorBPP}
 * inject {@link FilterDefenition} to {@link Command}
 */
public final class CommandFilterDefinitionsMap {

	private static final Map<Command, FilterDefenition> commandMap = new HashMap<>();

	public static final void put(Command command, String beanName, Method method) {
		commandMap.put(command, new FilterDefenition(beanName, method));
	}

	public static final Method getMethod(Command command) {
		return commandMap.get(command).getMethod();
	}

	public static final String getBeanName(Command command) {
		return commandMap.get(command).getBeanName();
	}

	public static final boolean isNotPresent(Command command) {
		return !commandMap.containsKey(command);
	}

}
