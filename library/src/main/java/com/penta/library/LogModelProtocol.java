package com.penta.library;

import java.util.List;
import java.util.Map;

/**
 * Created by linyueyang on 2018/11/1.
 */

public interface LogModelProtocol {
    String getMethod();

    Map<String, String> getParams();

    List<? extends LogParaModelProtocol> getParameterParams();

    List<? extends LogAttrModelProtocol> getAttributeParams();
}
