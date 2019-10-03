package com.learn.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Author: Knox
 * @Date: 2019-10-02 14:04
 * @Description: You Know
 * @Version 1.0
 */
public class IProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");

        properties.put(ProducerConfig.ACKS_CONFIG, "all");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "com.learn.kafka.intercepter.TimeIntercepter");
        //properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.learn.kafka.partitioner.IPartitioner");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
//        producer.initTransactions();
//        producer.beginTransaction();
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("first", i + "3 * >>>"), ((recordMetadata, e) -> {
                System.out.println(recordMetadata.partition() + "-" + recordMetadata.offset());
            }));
        }
        //producer.commitTransaction();
        producer.close();
    }
}
