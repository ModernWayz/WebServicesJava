package edu.ap.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import edu.ap.spring.jpa.Wallet;

@Aspect
@Component
public class InterceptableHandler {
    @Around("execution(public * sendCoins(..))")
    public String aroundInterceptable(ProceedingJoinPoint joinPoint) throws Throwable {      
        String result = joinPoint.proceed().toString();

        Wallet wallet = (Wallet) joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        Double value = (Double) args[1];
        Double balance = wallet.getCoins();

        if(balance < value) {
			System.out.println("Not enough coins for transaction");
			throw new Exception();
		}

        return result;
    }
}
