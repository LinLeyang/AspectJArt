package com.penta.aspectjart.okhttp.builder;

import com.penta.aspectjart.okhttp.OkHttpUtils;
import com.penta.aspectjart.okhttp.builder.*;
import com.penta.aspectjart.okhttp.request.OtherRequest;
import com.penta.aspectjart.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends com.penta.aspectjart.okhttp.builder.GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
