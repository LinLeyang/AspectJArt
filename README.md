### 如何接入动态埋点框架
app.gradle

```
apply plugin: 'pentaAsj'
apply plugin: 'pentaFileupload'
fileupload {
    url = 'http://10.252.177.108:8080/log/mapping'
    logFile = "log.txt"
    mappingFile = "app/build/outputs/mapping/release/mapping.txt"
}
implementation project(path: ':library')
annotationProcessor project(':aptlibrary')

```
在App初始化过程中

```
LogAopAgent.ins().setProtocol(new LogAopProtocol() {
    @Override
    public void onBefore(ProceedingJoinPoint joinPoint) {
    }

    @Override
    public void onAfter(String Method, Map<String, String> paramsMap) {
        if (paramsMap != null)
            Log.d("TraceAspect", Method + ":" + paramsMap.toString());
        else
            Log.d("TraceAspect", Method + ":no params");
    }
});
```

存储服务端配置文件

```
LogAopAgent.ins().addLogs(logModelList);
```
