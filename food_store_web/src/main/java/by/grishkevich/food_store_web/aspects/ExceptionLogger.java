package by.grishkevich.food_store_web.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class ExceptionLogger {

    @AfterThrowing("within(by.grishkevich.food_store_data.services.data.implementation.TrademarkJPAService)")
    public void logValueNotFoundExceptions(JoinPoint joinPoint){
      log.info("Exception " +  joinPoint.toString());
    }
}
