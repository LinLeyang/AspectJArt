package com.penta.aspectjart;

import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.penta.aspectjart.business.DetailBean;
import com.penta.aspectjart.business.ListResponse;
import com.penta.library.annotation.ApjLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    List<DetailBean> detailBeanList = new ArrayList<>();
    ListResponse listResponse;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.list);
        adapter = new Adapter(this, detailBeanList);
        listView.setAdapter(adapter);
        getData();
    }

    private void getData() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://rap2api.taobao.org/app/mock/7065/listData")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                listResponse = JSON.parseObject(htmlStr, ListResponse.class);
                bindDataToView();
            }
        });
    }

    @ApjLog
    private void bindDataToView() {
        detailBeanList.clear();
        detailBeanList.addAll(listResponse.getInfolist());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
