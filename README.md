# zipkin-collector-pretender
> 伪装成 zipkin 的 collector, 解压(gzip解压)数据 并 转换成json格式, 发送至指定的 kafka.topic

## 修改配置

### 集群地址

```yaml
spring:
  application:
    name: zipkin-collector-pretender
  kafka:
    # 集群地址, 修改为需要发送的 kafka 集群
    bootstrap-servers: 10.0.16.196:18108
```


### 指定topic地址

```yaml
# 需要发送的topic
kafka-topic:
  zipkin: zipkin-raven
```
