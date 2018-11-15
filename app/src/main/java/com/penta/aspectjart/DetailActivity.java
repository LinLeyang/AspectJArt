package com.penta.aspectjart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.penta.annotation.ApjLog;
import com.penta.aspectjart.business.DetailBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    Button button2;
    DetailBean detailBean;

    TextView tvTitle;
    TextView tvId;
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        tvTitle = findViewById(R.id.tv_title);
        tvId = findViewById(R.id.tv_id);
        tvContent = findViewById(R.id.tv_content);

        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                button1Click(25);
                break;
            case R.id.button2:
                button2Click();
                break;
            default:
                break;
        }
    }

    @ApjLog
    private void button1Click(int position) {
        Toast.makeText(this, "button1", Toast.LENGTH_SHORT).show();
    }

    @ApjLog
    private void button2Click() {
        Toast.makeText(this, "button2", Toast.LENGTH_SHORT).show();
    }

    private void getData() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://rap2api.taobao.org/app/mock/7065/detailData")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                detailBean = JSON.parseObject(htmlStr, DetailBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bindDataToView();
                    }
                });

            }
        });
    }

    @ApjLog
    private void bindDataToView() {
        tvTitle.setText(detailBean.getTitle());
        tvId.setText(detailBean.getId());
        tvContent.setText(detailBean.getContent());
    }

}