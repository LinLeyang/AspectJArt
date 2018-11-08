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

    public void addLogs(List<? extends LogModelProtocol> logBeans) {
        LogStorage.addLogs(logBeans);
    }

    public HashMap<String, LogModelProtocol> getLogMap() {
        return LogStorage.logMap;
    }

}
