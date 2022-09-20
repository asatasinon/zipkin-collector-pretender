# zipkin-collector-pretender
> 伪装成 zipkin 的 collector, 解压(gzip解压)数据, 并转发至指定的 kafka.topic

## 修改配置

### 集群地址

```yaml
spring:
  application:
    name: zipkin-collector-pretender
  kafka:
    # 集群地址, 修改为需要发送的 kafka 集群
    bootstrap-servers: 127.0.0.1:9092
```


### 指定topic地址

```yaml
# 需要发送的topic
kafka-topic:
  zipkin: zipkin-raven
```

### 指定jaeger-collector地址

```yaml
# 需要转发到对应的jaeger-collector地址
target:
  url: http://10.0.16.196:9411/api/v2/spans

```