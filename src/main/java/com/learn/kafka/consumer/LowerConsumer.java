package com.learn.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @Author: Knox
 * @Date: 2019-10-02 14:29
 * @Description: You Know
 * @Version 1.0
 */
public class LowerConsumer {
    public static void main(String[] args) {
        findLeaders();
    }

    private static void findLeaders() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "master:9092");
        props.setProperty("group.id", "group1");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        KafkaConsumer<String, String> consumer1 = new KafkaConsumer<>(props);
        consumer1.subscribe(Collections.singletonList("test1"));

        KafkaConsumer<String, String> consumer2 = new KafkaConsumer<>(props);
        consumer2.subscribe(Collections.singletonList("test1"));

        new Thread(() -> {
            while (true) {
                ConsumerRecords<String, String> vals = consumer1.poll(Duration.ofMillis(100));
                vals.forEach(record -> {
                    System.out.println("consumer1 > value :" + record.value() + " - partition :" + record.partition() + " - offset : " + record.offset());
                });

            }
        }).start();

        new Thread(() -> {
            while (true) {
                ConsumerRecords<String, String> vals = consumer2.poll(Duration.ofMillis(100));
                vals.forEach(record -> {
                    System.out.println("consumer2 > value :" + record.value() + " - partition :" + record.partition() + " - offset : " + record.offset());
                });

            }
        }).start();
    }
}
