package com.penta.aspectjart;

import com.penta.library.LogAttrModelProtocol;

/**
 * Created by linyueyang on 2018/11/8.
 */

public class LogAttrModel implements LogAttrModelProtocol{

    String key;
    String path;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
