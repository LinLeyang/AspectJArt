package com.penta.aspectjart;

import com.penta.library.LogModelProtocol;

import java.util.List;
import java.util.Map;

/**
 * Created by linyueyang on 2018/10/29.
 */

public class LogModel implements LogModelProtocol {

    String method;
    Map params;
    List<LogParaModel> parameterParams;
    List<LogAttrModel> attributeParams;

    @Override
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    @Override
    public List<LogParaModel> getParameterParams() {
        return parameterParams;
    }

    public void setParameterParams(List<LogParaModel> parameterParams) {
        this.parameterParams = parameterParams;
    }

    @Override
    public List<LogAttrModel> getAttributeParams() {
        return attributeParams;
    }

    public void setAttributeParams(List<LogAttrModel> attributeParams) {
        this.attributeParams = attributeParams;
    }
}
