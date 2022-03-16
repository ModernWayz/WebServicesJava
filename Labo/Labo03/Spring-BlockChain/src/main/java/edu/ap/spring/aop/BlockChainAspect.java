package edu.ap.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;

import edu.ap.spring.service.Wallet;
import edu.ap.spring.transaction.Transaction;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class BlockChainAspect {

    @Before("execution(public String transaction(..))")
     public void getBalance(JoinPoint joinPoint) {
        // get http params
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
        System.out.println(request.getMethod());
        System.out.println(request.getRequestURI());

        // get method args
        Object[] args = joinPoint.getArgs();
        for (Object o : args) {
            System.out.println(o.toString());
        }
    }

    @Around("execution(public * sendFunds(..))")
    public Transaction checkBalance(ProceedingJoinPoint joinPoint) throws Throwable {

        Wallet wallet = (Wallet) joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        float value = (float) args[1];
        float balance = wallet.getBalance();

        if(balance < value) {
			System.out.println("# Not Enough funds to send transaction. Transaction Discarded.");
			throw new Exception();
		}

        Transaction result = (Transaction) joinPoint.proceed();

        return result;
    }
}
