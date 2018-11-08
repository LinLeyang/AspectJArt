package com.penta.library;

import java.util.HashMap;
import java.util.List;

/**
 * Created by linyueyang on 2018/10/29.
 */

public class LogStorage {
    protected static HashMap<String, LogModelProtocol> logMap = new HashMap();

    protected static void addLogs(List<? extends LogModelProtocol> logBeans) {
        for (LogModelProtocol logBean : logBeans) {
            logMap.put(logBean.getMethod(), logBean);
        }
    }


}
