package com.example.borrowbook.aop;

import com.example.borrowbook.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AuditAspect {
    private final AuditLogService auditLogService;

    public AuditAspect(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @AfterReturning(pointcut = "execution(* com.example.borrowbook.service.BookService.borrow(..)) && args(bookId) ")
    public void afterBorrow(Long bookId) {
        auditLogService.saveLog(bookId, "BORROW", -1, "borrow()");
    }

    @AfterReturning(pointcut = "execution(* com.example.borrowbook.service.BookService.returnByCode(..)) && args(code)")
    public void afterReturn(String code) {
        auditLogService.saveLog(null, "RETURN", +1, "return code=" + code);
    }

    @AfterThrowing(pointcut = "execution(* com.example.borrowbook.service.BookService.*(..))", throwing = "ex")
    public void afterError(Exception ex) {
        log.warn("Service error: {}", ex.getMessage());
    }
}
