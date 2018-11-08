package com.penta.aspectjart;

import android.app.Application;
import android.util.Log;

import com.penta.library.LogAopAgent;
import com.penta.library.LogAopProtocol;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Map;

/**
 * Created by linyueyang on 2018/10/26.
 */

public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        app = this;
        super.onCreate();
        LogAopAgent.ins().setProtocol(new LogAopProtocol() {
            @Override
            public void onBefore(ProceedingJoinPoint joinPoint) {
            }

            @Override
            public void onAfter(String Method, Map<String, String> paramsMap) {
                if (paramsMap != null)
                    Log.d("TraceAspect", Method + ":" + paramsMap.toString());
                else
                    Log.d("TraceAspect", Method + ":no params");
            }
        });
    }

    public static App getIns() {
        return app;
    }
}
