/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.penta.library.aspect;


import com.penta.library.LogAopAgent;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect representing the cross cutting-concern: Method and Constructor Tracing.
 */
@Aspect
public class LogAspect {

    private static final String POINTCUT_METHOD =
            "execution(@com.penta.library.annotation.ApjLog * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.penta.library.annotation.ApjLog *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedDebugTrace() {
    }

    @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        LogAopAgent.ins().logAopProtocol.onBefore(joinPoint);
        Object result = joinPoint.proceed();
        LogAopAgent.ins().logAopProtocol.onAfter(joinPoint);
        return result;
    }

//    /**
//     * Create a log message.
//     *
//     * @param methodName A string with the method name.
//     * @param methodDuration Duration of the method in milliseconds.
//     * @return A string representing message.
//     */
//    private static String buildLogMessage(String methodName, long methodDuration) {
//        StringBuilder message = new StringBuilder();
//        message.append("Gintonic --> ");
//        message.append(methodName);
//        message.append(" --> ");
//        message.append("[");
//        message.append(methodDuration);
//        message.append("ms");
//        message.append("]");
//
//        return message.toString();
//    }
}
