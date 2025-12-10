package org.example.borrowbook.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {
    private static final Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    // pointcut: mọi phương thức annotated @StateChanging
    @Around("@annotation(org.example.borrowbook.aspect.StateChanging)")
    public Object aroundStateChange(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        StateChanging annotation = signature.getMethod().getAnnotation(StateChanging.class);
        String action = annotation.value().isBlank() ? signature.getName() : annotation.value();

        // trước khi thay đổi: log cục bộ
        logger.info("[AUDIT] BEFORE {} at {} - method: {}",
                action, LocalDateTime.now(), signature.getName());
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
            // sau khi thành công:
            logger.info("[AUDIT] SUCCESS {} at {}", action, LocalDateTime.now());
        } catch (Throwable ex) {
            logger.error("[AUDIT] FAILED {} at {} - cause: {}", action, LocalDateTime.now(), ex.getMessage());
            throw ex;
        }
        return result;
    }
}