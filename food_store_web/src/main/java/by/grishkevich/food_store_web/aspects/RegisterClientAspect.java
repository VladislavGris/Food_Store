package by.grishkevich.food_store_web.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class RegisterClientAspect {

    @Pointcut("execution(* by.grishkevich.food_store_data.services.data.implementation.ClientJPAService.save(..))")
    public void aspectSaveClient(){}

    @After("aspectSaveClient()")
    public void logAfterSave(JoinPoint jp){

        log.info("User");
    }

}
