package com.learn.kafka.stream;

import com.learn.kafka.stream.processor.LogProcessor;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import java.util.Properties;

/**
 * @Author: Knox
 * @Date: 2019-10-03 14:27
 * @Description: You Know
 * @Version 1.0
 */
public class KafkaStream {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"kafkaStream");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.246.128:9092");
        //InternalTopologyBuilder builder = new InternalTopologyBuilder();
        Topology topology = new Topology();
        topology.addSource("SOURCE", "first")
                .addProcessor("PROCESSOR", LogProcessor::new, "SOURCE")
                .addSink("SINK", "second", "PROCESSOR");

        KafkaStreams kafkaStreams = new KafkaStreams(topology,properties);
        kafkaStreams.start();

    }
}
