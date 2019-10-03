package com.learn.kafka.intercepter;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author: Knox
 * @Date: 2019-10-03 13:51
 * @Description: You Know
 * @Version 1.0
 */
public class TimeIntercepter implements ProducerInterceptor<String, String> {
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {

        return new ProducerRecord<>(record.topic(), record.key(), System.currentTimeMillis() + "," + record.value());

    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {
        System.out.println("success !");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
