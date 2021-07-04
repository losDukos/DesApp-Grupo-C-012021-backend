package ar.edu.unq.desapp.grupoC.backenddesappapi.util;

import ar.edu.unq.desapp.grupoC.backenddesappapi.BackendDesappApiApplication;
import ar.edu.unq.desapp.grupoC.backenddesappapi.wrapper.UserDetail;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* ar.edu.unq.desapp.grupoC.backenddesappapi.controller..*(..))")
    public Object logEndpointData(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();

        BackendDesappApiApplication.logger.info(
                "\n" +
                getTimestamp() +
                getUserId() +
                getControllerMethod(joinPoint) +
                getMethodParameters(joinPoint) +
                getExecutionTime(startTime) +
                "\n\n\n"
        );

        return proceed;
    }

    private String getTimestamp() {
        return "Date is " + new Date() + "\n";
    }

    private String getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() == "anonymousUser") {
            return "User is anonymous\n";
        } else {
            UserDetail userDetail = (UserDetail) auth.getPrincipal();
            return "User ID is " + userDetail.getId() + "\n";
        }
    }

    private String getControllerMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return "Method called is " + signature.getMethod().getName() + "\n";
    }

    private String getMethodParameters(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return "Parameters are " + Arrays.toString(signature.getParameterNames()) + "\n";
    }

    private String getExecutionTime(Long startTime) {
        return "Execution time is " + (System.currentTimeMillis() - startTime) + " milliseconds" + "\n";
    }
}
