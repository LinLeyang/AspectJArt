package com.penta.library;

import java.util.HashMap;
import java.util.List;

/**
 * Created by linyueyang on 2018/10/30.
 */

public class LogAopAgent {

    private static volatile LogAopAgent self;
    public LogAopProtocol logAopProtocol;

    private LogAopAgent() {

    }

    public static LogAopAgent ins() {
        if (self == null) {
            synchronized (LogAopAgent.class) {
                if (self == null) {
                    self = new LogAopAgent();
                }
            }
        }
        return self;
    }

    public void setProtocol(LogAopProtocol logAopProtocol) {
        this.logAopProtocol = logAopProtocol;
    }

    public void removeLogByContext(String context) {
        LogStorage.removeLogByContext(context);
    }

    public void addLogs(String context, List<? extends LogModelProtocol> logBeans) {
        LogStorage.addLogs(context, logBeans);
    }

    public HashMap<String, String> getLogMap() {
        return LogStorage.logMap;
    }

}
