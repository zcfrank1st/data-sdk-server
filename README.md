DataSdkServer
====

> 埋点数据记录服务


## API
    Name：数据上报接口（日志）
    URL: /log
    Method: POST
    Request Body: 标准 json body，如：
    {
        "distinct_id":"",
        "time": 0,
        "type":"",
        "event":"",
        "properties": {}
    }

## 镜像化部署
    通过jib方式打包镜像

