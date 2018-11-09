package com.penta.aspectjart.logmodel;

import com.penta.library.LogParaModelProtocol;

/**
 * Created by linyueyang on 2018/11/8.
 */

public class LogParaModel implements LogParaModelProtocol {

    int position;
    String key;
    String path;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

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
