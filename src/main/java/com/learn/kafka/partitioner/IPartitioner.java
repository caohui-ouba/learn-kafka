package com.learn.kafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @Author: Knox
 * @Date: 2019-09-30 16:27
 * @Description: You Know
 * @Version 1.0
 */
public class IPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Integer integer = cluster.partitionCountForTopic(topic);
        return integer - 1;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
