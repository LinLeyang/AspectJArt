package com.penta.library;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Map;

/**
 * Created by linyueyang on 2018/11/1.
 */

public interface LogAopProtocol {

    void onBefore(ProceedingJoinPoint joinPoint);

    void onAfter(String Method, Map<String, String> paramsMap);

}
