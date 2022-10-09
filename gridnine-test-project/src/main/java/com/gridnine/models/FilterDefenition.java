package com.gridnine.models;

import java.lang.reflect.Method;

import com.gridnine.bpp.CommandFilterDefinitionsMap;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FilterDefenition that used in {@link CommandFilterDefinitionsMap}
 */
@Data
@AllArgsConstructor
public class FilterDefenition {
	
	private String beanName;
	private Method method;

}
