package com.gridnine.bpp.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.gridnine.commands.Command;

/**
 * Annotation to define the command handler 
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Filter {
	Command value();
}
