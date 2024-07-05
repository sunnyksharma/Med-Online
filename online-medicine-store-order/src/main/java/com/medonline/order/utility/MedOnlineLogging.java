package com.medonline.order.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MedOnlineLogging {

    @AfterThrowing(pointcut = "execution(* com.medonline.order.service.*Impl.*(..))", throwing = "exception")
    public void LogExceptionFromService(Exception exception) throws Exception {
        log(exception);
    }

    private void log(Exception exception) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        if (exception.getMessage() != null) {
            logger.error(exception.getMessage());
        } else {
            logger.error(exception.getMessage(), exception);
        }
    }
}
