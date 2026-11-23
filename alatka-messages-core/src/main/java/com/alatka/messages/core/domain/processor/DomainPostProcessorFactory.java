package com.alatka.messages.core.domain.processor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DomainPostProcessorFactory {

    private static final Map<String, DomainPostProcessor> MAP = new ConcurrentHashMap<>();

    private DomainPostProcessorFactory() {
    }

    static {
        init(new RawDomainPostProcessor());
    }

    public static void init(DomainPostProcessor domainPostProcessor) {
        MAP.put(domainPostProcessor.name(), domainPostProcessor);
    }

    public static DomainPostProcessor getDomainPostProcessor(String name) {
        return Optional.ofNullable(MAP.get(name)).orElseThrow(() -> new IllegalArgumentException("Unknown processor: " + name));
    }
}
