package com.penta.aspectjart.business;

import java.util.List;

/**
 * Created by linyueyang on 2018/11/9.
 */

public class ListResponse {

    String cateFullPath;
    String cityFullPath;
    List<DetailBean> infolist;

    public String getCateFullPath() {
        return cateFullPath;
    }

    public void setCateFullPath(String cateFullPath) {
        this.cateFullPath = cateFullPath;
    }

    public String getCityFullPath() {
        return cityFullPath;
    }

    public void setCityFullPath(String cityFullPath) {
        this.cityFullPath = cityFullPath;
    }

    public List<DetailBean> getInfolist() {
        return infolist;
    }

    public void setInfolist(List<DetailBean> infolist) {
        this.infolist = infolist;
    }
}
