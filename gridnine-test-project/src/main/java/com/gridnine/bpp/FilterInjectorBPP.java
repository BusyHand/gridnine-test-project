package com.gridnine.bpp;

import java.lang.reflect.Method;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.gridnine.bpp.annotations.Filter;
import com.gridnine.commands.Command;
import com.gridnine.exception.CommandConflictException;
import com.gridnine.service.FlightFilterHandler;

/**
 * BeanPostProcessor that substitute command handlers with {@link Filter}
 * annotation
 */
@Component
public class FilterInjectorBPP implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		if (bean instanceof FlightFilterHandler) {
			mapFilter(bean, beanName);
		}
		return bean;
	}

	private void mapFilter(Object bean, String beanName) {
		Method[] methods = bean.getClass().getDeclaredMethods();
		for (Method method : methods) {
			Filter commandAnnotation = method.getAnnotation(Filter.class);
			if (commandAnnotation != null) {
				Command command = commandAnnotation.value();
				if (CommandFilterDefinitionsMap.isNotPresent(command)) {
					CommandFilterDefinitionsMap.put(command, beanName, method);
				} else {
					throw new CommandConflictException("Command '" + command + "' not obvious for injection");
				}
			}
		}
	}

}
