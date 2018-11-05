package com.penta.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by linyueyang on 2018/10/29.
 */

public class LogStorage {
    protected static HashMap<String, List<String>> contextMap = new HashMap();
    protected static HashMap<String, String> logMap = new HashMap();

    protected static void removeLogByContext(String context) {
        List<String> logKeyList = contextMap.get(context);
        if (null == logKeyList) {
            return;
        }
        for (String logKey : logKeyList) {
            logMap.remove(logKey);
        }
    }

    protected static void addLogs(String context, List<? extends LogModelProtocol> logBeans) {
        List<String> keyList = new ArrayList();
        for (LogModelProtocol logBean : logBeans) {
            keyList.add(logBean.getMethod());
            logMap.put(logBean.getMethod(), logBean.getParams());
        }
        contextMap.put(context, keyList);
    }
}
