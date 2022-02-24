package edu.ap.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;

import edu.ap.spring.service.Wallet;
import edu.ap.spring.transaction.Transaction;

@Component
@Aspect
public class BlockChainAspect {

    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping) && execution(public * getBalance(..))")
    public void beforeGetBalance(JoinPoint joinPoint) {
        System.out.println("Before " + joinPoint.getSignature());
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

    @After("@annotation(org.springframework.web.bind.annotation.PostMapping) && execution(public * transaction(..))")
    public void afterTransaction(JoinPoint joinPoint) {
        System.out.println("After " + joinPoint.getSignature());
    }
}
