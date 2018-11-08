/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.penta.library.aspect;


import android.util.Log;

import com.penta.library.LogAopAgent;
import com.penta.library.LogAttrModelProtocol;
import com.penta.library.LogModelProtocol;
import com.penta.library.LogParaModelProtocol;
import com.penta.library.ReflectUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.HashMap;
import java.util.List;

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


        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getName();
        String methodName = methodSignature.getName();
        String context = className + "#" + methodName;
        Log.d("TraceAspect", context);
        LogModelProtocol logModel = LogAopAgent.ins().getLogMap().get(context);
        HashMap hashMap = null;
        if (logModel != null) {
            List<? extends LogAttrModelProtocol> attributes = logModel.getAttributeParams();
            List<? extends LogParaModelProtocol> parameters = logModel.getParameterParams();

            if (null != logModel.getParams()) {
                hashMap = new HashMap(logModel.getParams());
            } else {
                hashMap = new HashMap();
            }

            Object o = joinPoint.getThis();
            for (LogAttrModelProtocol attr : attributes) {
                String value = ReflectUtil.getFiled(o, attr.getPath());
                hashMap.put(attr.getKey(), value);
            }

            Object[] args = joinPoint.getArgs();
            if (null != args && args.length > 0) {
                for (LogParaModelProtocol param : parameters) {
                    if (param.getPosition() < args.length) {
                        String value = ReflectUtil.getFiled(args[param.getPosition()], param.getPath());
                        hashMap.put(param.getKey(), value);
                    }
                }
            }

        }
        LogAopAgent.ins().logAopProtocol.onAfter(context, hashMap);
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
