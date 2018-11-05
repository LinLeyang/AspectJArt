package com.penta.aspectjart;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.penta.aspectjart.okhttp.OkHttpUtils;
import com.penta.library.LogAopAgent;
import com.penta.library.LogAopProtocol;
import com.penta.library.LogStorage;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by linyueyang on 2018/10/26.
 */

public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        app = this;
        super.onCreate();
        OkHttpUtils.initClient(null);
        LogAopAgent.ins().setProtocol(new LogAopProtocol() {
            @Override
            public void onBefore(ProceedingJoinPoint joinPoint) {

            }

            @Override
            public void onAfter(ProceedingJoinPoint joinPoint) {
                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                String className = methodSignature.getDeclaringType().getName();
                String methodName = methodSignature.getName();
                String context = className + "#" + methodName;
                Log.d("TraceAspect", context);
                String params = LogAopAgent.ins().getLogMap().get(context);
                if (!TextUtils.isEmpty(params)) {
                    Log.d("TraceAspect", className + "#" + methodName + ":" + params);
                }
            }
        });
    }

    public static App getIns() {
        return app;
    }
}
