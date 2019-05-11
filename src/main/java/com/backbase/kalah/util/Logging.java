package com.backbase.kalah.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

@Slf4j
public class Logging {
    public static void writeLog(LogStatus status , JoinPoint joinPoint){
        writeLog(status , joinPoint , null);
    }

    public static void writeLog(LogStatus status , JoinPoint joinPoint, Throwable ex){
        writeLog(status,joinPoint,ex,null,null);
    }

    public static void writeLog(LogStatus status ,JoinPoint joinPoint,Object returnVal ,Long time){
        writeLog(status,joinPoint,null,returnVal,time);
    }

    private static void writeLog(LogStatus status ,JoinPoint joinPoint,Throwable ex ,Object returnVal ,Long time){

        StringBuffer logMessage = new StringBuffer();

        logMessage.append(status.getStatus());
        logMessage.append(" METHOD : "+joinPoint.getSignature().toString());

        if(status != LogStatus.END)
            logMessage.append(" ,ARGS : "+ Arrays.toString(joinPoint.getArgs()));
        else{
            logMessage.append(" ,Return : "+ ((returnVal == null)? "void" : returnVal.toString()));
            logMessage.append(" execution time: "+time+" ms");
        }

        if(ex != null)
            logMessage.append(" ,EXCEPTION : "+ ex.getMessage());


        switch (status){
            case Exception:
                log.error(logMessage.toString());
                break;
            default:
                log.info(logMessage.toString());
        }
    }


}
