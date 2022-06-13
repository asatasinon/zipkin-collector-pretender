package com.raven.pretender.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author: raven
 * @date: 2022/6/13
 * @description:
 */
public class KeyModPartitioner implements Partitioner {

    private static final Random RANDOM_INT = new Random();

    public KeyModPartitioner() {
    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int numPartitions = cluster.partitionsForTopic(topic).size();
        return key == null ? RANDOM_INT.nextInt(numPartitions) : Math.abs(key.hashCode()) % numPartitions;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
