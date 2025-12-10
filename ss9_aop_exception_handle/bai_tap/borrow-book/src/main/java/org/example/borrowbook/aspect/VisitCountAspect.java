package org.example.borrowbook.aspect;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class VisitCountAspect {
    private static final Logger logger = LoggerFactory.getLogger(VisitCountAspect.class);
    private final AtomicInteger visitCounter = new AtomicInteger(0);

    // Bắt tất cả controller public methods trong package controller
    @Before("execution(* org.example.borrowbook.controller..*(..))")
    public void countVisit(JoinPoint jp) {
        int v = visitCounter.incrementAndGet();
        logger.info("[VISIT] Visit #{} - {}#{}",
                v, jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
        // nếu muốn persist: inject repository và lưu vào DB (AuditLog) — omitted for brevity
    }

    public int getVisitCount() { return visitCounter.get(); }
}