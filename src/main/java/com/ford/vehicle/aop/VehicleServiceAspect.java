/*package com.ford.vehicle.aop;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.ford.vehicle.domain.Vehicle;

@Aspect
@Configuration
public class VehicleServiceAspect {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//What kind of method calls I would intercept
	//execution(* PACKAGE.*.*(..))
	//Weaving & Weaver
	@Before("execution(* com.ford.vehicle.service.*.*(..))")
	public void before(JoinPoint joinPoint){
		//Advice
		logger.info(" before execution for {}", joinPoint);
	}
	@After(value = "execution(* com.ford.vehicle.service.*.*(..))")
	public void after(JoinPoint joinPoint) {
		logger.info("after execution of {}", joinPoint);
	}
	@AfterReturning(value = "execution(* com.ford.vehicle.service.*.*(..))", 
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, List<Vehicle> result) {
		logger.info("{} returned with value {}", joinPoint, result.toString());
	}
	@Around("execution(* com.ford.vehicle.service.VehicleService.getVehicles(..))")
	public List<Vehicle> aroundAdviceGet(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("Before invoking aroundAdviceGet() method");
		List<Vehicle> result = new ArrayList<Vehicle>();
		try {
			 result=(List<Vehicle>) proceedingJoinPoint.proceed();
			System.out.println(result.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("after invoking aroundAdviceGet() method");
		
		return result;
	}
	@Around("execution(* com.ford.vehicle.service.VehicleService.createVechicle(..))")
	public String aroundAdvicePost(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("Before invoking aroundAdvicePost() method");
		String result = null ;
		try {
			 result=(String) proceedingJoinPoint.proceed();
			System.out.println(result.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("after invoking aroundAdvicePost() method");
		
		return result;
	}
}
*/