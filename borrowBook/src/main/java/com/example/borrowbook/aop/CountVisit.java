package com.example.borrowbook.aop;

import com.example.borrowbook.service.CounterService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;

@Slf4j
public class CountVisit {
    private final CounterService counterService;

    public CountVisit(CounterService counterService) {
        this.counterService = counterService;
    }

    @Before("within(@org.springframework.stereotype.Controller *) && execution(* com.example.library.controller..*(..))")
    public void countVisit(JoinPoint jp) {
        long count = counterService.incrementAndGet();
        log.info("Visitor count: {}, at: {}", count, jp.getSignature().toShortString());
    }
}
