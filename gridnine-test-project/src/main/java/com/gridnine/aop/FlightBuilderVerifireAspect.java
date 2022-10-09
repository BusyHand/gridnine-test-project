package com.gridnine.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Class for checking the input of arguments when creating flights from {@link FlightBuilder}
 */
@Component
@Aspect
public class FlightBuilderVerifireAspect {

	@Around("execution(* com.gridnine.factories.FlightBuilder.createFlight(..))")
	public Object argumentsVerifire(ProceedingJoinPoint joinPoint) {
		int numberOfArguments = joinPoint.getArgs().length;
		//check args
		if ((numberOfArguments > 1 && numberOfArguments % 2 != 0)
				|| numberOfArguments == 0) {
			throw new IllegalArgumentException("Количество аргументов при создание перелета должно быть четным");
		}
		return proceed(joinPoint);
	}

	private Object proceed(ProceedingJoinPoint joinPoint) {
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

}
