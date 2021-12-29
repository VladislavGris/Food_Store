package by.grishkevich.food_store_web.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ExceptionLogger {

    @Pointcut("execution(* by.grishkevich.food_store_data.services.data.implementation.ProductJPAService.save(..))")
    public void aspectSaveProduct(){}

    @Before("aspectSaveProduct()")
    public void logBefore(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("Before: " + methodName);
    }

    @After("aspectSaveProduct()")
    public void logAfter(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("After: " + methodName);
    }

    @AfterReturning(pointcut = "aspectSaveProduct()", returning = "returned")
    public void logAfterReturning(boolean returned){
        if(returned){
            log.info("After returning (save): Product saved" );
        }else{
            log.info("After returning (save): Product not saved" );
        }
    }

    public void logAfterThrowing(JoinPoint jp, Exception e){
        log.info("AfterThrowing (save): " + e.getMessage());
    }

}
