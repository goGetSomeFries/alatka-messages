package com.alatka.messages.core.benchmark;

import com.alatka.messages.core.definition.CompositeMessageDefinitionBuilder;
import com.alatka.messages.core.definition.IsoXmlMessageDefinitionBuilder;
import com.alatka.messages.core.definition.IsoYamlMessageDefinitionBuilder;
import com.alatka.messages.core.message.MessageBuilder;
import com.alatka.messages.core.support.FileWrapper;
import com.alatka.messages.core.util.FileUtil;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime})
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Threads(3)
@Fork(2)
public class UnpackBenchmark {

    @Param({"unionpay", "jcb", "mastercard", "cupd", "visa"})
    private String group;

    @Param({"basic", "nested"})
    private String kind;

    private Tuple[] tuple;

    @Setup(Level.Trial)
    public void setup() {
        CompositeMessageDefinitionBuilder builder = new CompositeMessageDefinitionBuilder();
        builder.addBuilder(new IsoXmlMessageDefinitionBuilder("classpath:messages/"));
        builder.addBuilder(new IsoYamlMessageDefinitionBuilder("classpath:messages/"));
        builder.build();

        List<FileWrapper> list = FileUtil.getClasspathFilesContent("data/" + this.group, "." + kind + "1", FileUtil.class.getClassLoader());
        this.tuple = list.stream().map(fileWrapper -> {
            String message = new String(fileWrapper.getContent());
            String identity = fileWrapper.getName().split("\\.")[1];
            MessageBuilder messageBuilder = MessageBuilder.init(identity);
            return new Tuple(message, messageBuilder);
        }).toArray(Tuple[]::new);

        if (this.tuple.length == 0) {
            throw new IllegalArgumentException("No messages found; Group: " + this.group + ", Kind: " + this.kind);
        }
    }

    @Benchmark
    public void unpack(Blackhole blackhole) {
        int index = ThreadLocalRandom.current().nextInt(this.tuple.length);
        MessageBuilder messageBuilder = this.tuple[index].messageBuilder;
        String message = this.tuple[index].message;
        Object messageHolder = messageBuilder.unpack(message);
        blackhole.consume(messageHolder);
    }

    private static class Tuple {
        private String message;
        private MessageBuilder messageBuilder;

        public Tuple(String message, MessageBuilder messageBuilder) {
            this.message = message;
            this.messageBuilder = messageBuilder;
        }
    }
}
