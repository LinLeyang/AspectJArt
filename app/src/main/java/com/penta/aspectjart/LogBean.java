package com.penta.aspectjart;

import com.penta.library.LogAopProtocol;
import com.penta.library.LogModelProtocol;

/**
 * Created by linyueyang on 2018/10/29.
 */

public class LogBean implements LogModelProtocol {

    String method;
    String params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
