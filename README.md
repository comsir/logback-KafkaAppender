# logback-KafkaAppender

1.针对logback 自定义KafkaAppender收集日志到kafka

2.使用kafka版本 0.8.2.2

3.若kafka集群挂了 不阻塞主线程 不影响作业的正常运行 日志照常打印到flie系统中

4.根据需要添加了IP、系统信息等内容（对应xml文件中的%ip,%tag）在日志中，方便对日志分析，可根据需求继续添加想要的信息
