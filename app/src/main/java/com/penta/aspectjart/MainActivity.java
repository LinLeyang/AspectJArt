package com.penta.aspectjart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.penta.aspectjart.okhttp.OkHttpUtils;
import com.penta.aspectjart.okhttp.callback.Callback;
import com.penta.library.annotation.ApjLog;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    Button button2;

    @ApjLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                button1Click();
                break;
            case R.id.button2:
                button2Click();
                break;
            default:
                break;
        }

    }

    @ApjLog
    private void button1Click() {
        Toast.makeText(this, "button1", Toast.LENGTH_SHORT).show();
    }

    @ApjLog
    private void button2Click() {
        Toast.makeText(this, "button2", Toast.LENGTH_SHORT).show();
    }

    private void getData() {
        OkHttpUtils.get().url("http://rap2api.taobao.org/app/mock/7065/logData").build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return null;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(Object response, int id) {
                onShow();
            }
        });
    }

    @ApjLog
    private void onShow() {
    }


}