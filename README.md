# 学习kafka

## 一、简介

- kafka是一个分布式的消息队列，可以集群部署，消息队列的作用如下。

![queue](https://t1.picb.cc/uploads/2019/09/10/gXKOtu.md.jpg)

- kafka对消息保存时根据Topic进行归类。发送消息者称为Producer，消息接受者称为Consumer。
kafka集群有多个kafka实例，每个实例称为broker。
- 无论是kafka就集群，还是consumer都依赖于`zookeeper`集群保存一些meta信息，保证系统可用性。
- kafka的Topic是分区的形式分布在不同的实例上，每个分区有N个副本，N个副本中有一个是Leader，N-1个Followers。
- 一个消费者组中的消费者不能同时消费同一个分区。

- kafka的工作架构如下。

![kafka](https://t1.picb.cc/uploads/2019/09/10/gXYnEW.md.jpg)

