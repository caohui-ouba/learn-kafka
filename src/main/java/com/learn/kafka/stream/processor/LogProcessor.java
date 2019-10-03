package com.learn.kafka.stream.processor;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

/**
 * @Author: Knox
 * @Date: 2019-10-03 14:58
 * @Description: You Know
 * @Version 1.0
 */
public class LogProcessor implements Processor<byte[], byte[]> {

    private ProcessorContext ctx;

    @Override
    public void init(ProcessorContext context) {
        this.ctx = context;
    }

    @Override
    public void process(byte[] key, byte[] value) {
        if (this.ctx != null) {
            String val = new String(value);
            val = val.replaceAll(">>>", "");
            ctx.forward(key, val.getBytes());
            
        }
    }

    @Override
    public void close() {
        System.out.println("cloßse...");
    }
}
