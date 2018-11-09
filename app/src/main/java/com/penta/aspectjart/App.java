package com.penta.aspectjart;

import android.app.Application;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.penta.aspectjart.logmodel.LogModel;
import com.penta.library.LogAopAgent;
import com.penta.library.LogAopProtocol;

import org.aspectj.lang.ProceedingJoinPoint;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by linyueyang on 2018/10/26.
 */

public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        app = this;
        super.onCreate();
        getData();
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


    private void getData() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://rap2api.taobao.org/app/mock/7065/logData")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();

                JSONObject jsonObject = JSON.parseObject(htmlStr);

                List<LogModel> logModelList = JSON.parseArray(jsonObject.getString("logs"), LogModel.class);
                LogAopAgent.ins().addLogs(logModelList);
            }
        });
    }

    public static App getIns() {
        return app;
    }
}
