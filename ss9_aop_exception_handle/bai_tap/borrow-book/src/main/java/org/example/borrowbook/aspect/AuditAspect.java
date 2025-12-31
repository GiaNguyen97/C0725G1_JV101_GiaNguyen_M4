package org.example.borrowbook.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class AuditAspect {
    private static final Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    // pointcut: mọi phương thức annotated @StateChanging
    @Around("@annotation(org.example.borrowbook.aspect.StateChanging)")
    public Object aroundStateChange(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        StateChanging annotation = signature.getMethod().getAnnotation(StateChanging.class);
        // Lấy tên hành động, ví dụ: "Mượn sách", "Trả sách"
        String action = annotation.value().isBlank() ? signature.getName() : annotation.value();

        Object result;
        try {
            // Log TRƯỚC khi thực hiện hành động
            // Nội dung đơn giản: Bắt đầu hành động...
            logger.info("[AUDIT] BẮT ĐẦU: Thực hiện hành động \"{}\"...", action);

            result = proceedingJoinPoint.proceed();

            // Log SAU KHI thành công
            // Nội dung: Hành động... đã thành công.
            logger.info("[AUDIT] THÀNH CÔNG: Hành động \"{}\" đã hoàn tất.", action);
        } catch (Throwable ex) {
            // Log KHI THẤT BẠI
            // Nội dung: Hành động... thất bại. Lỗi: ...
            logger.error("[AUDIT] THẤT BẠI: Hành động \"{}\" không thành công. Nguyên nhân: {}", action, ex.getMessage());
            throw ex;
        }
        return result;
    }
}