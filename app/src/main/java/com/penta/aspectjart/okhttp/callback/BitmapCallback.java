package com.penta.aspectjart.okhttp.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.penta.aspectjart.okhttp.callback.*;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class BitmapCallback extends com.penta.aspectjart.okhttp.callback.Callback<Bitmap>
{
    @Override
    public Bitmap parseNetworkResponse(Response response , int id) throws Exception
    {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

}
