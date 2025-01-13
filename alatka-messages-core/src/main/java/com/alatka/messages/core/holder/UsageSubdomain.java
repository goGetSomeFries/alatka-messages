package com.alatka.messages.core.holder;

import com.alatka.messages.core.annotation.MessageMeta;
import com.alatka.messages.core.support.CustomJsonSerializer;
import com.alatka.messages.core.util.JsonUtil;
import com.alatka.messages.core.util.ObjectUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * usage 子域容器
 *
 * @author ybliu
 */
@JsonSerialize(using = CustomJsonSerializer.class)
public class UsageSubdomain<T> {

    private final Map<String, Object> holder = new HashMap<>();

    public Map<String, Object> getHolder() {
        return holder;
    }

    public T get(String key) {
        return (T) this.holder.get(key);
    }

    public void putAll(Map<String, Object> map) {
        holder.putAll(map);
    }

    public void put(String usage, T subdomain) {
        holder.put(usage, subdomain);
    }

    public void put(T subdomain) {
        if (subdomain instanceof MessageHolder) {
            MessageHolder messageHolder = (MessageHolder) subdomain;
            holder.put(messageHolder.getMessageDefinition().getUsage(), subdomain);
        } else if (subdomain.getClass().isAnnotationPresent(MessageMeta.class)) {
            MessageMeta annotation = subdomain.getClass().getAnnotation(MessageMeta.class);
            holder.put(annotation.usage(), subdomain);
        } else {
            throw new IllegalArgumentException("subdomain must be MessageHolder type or annotation by @MessageMeta");
        }
    }

    @Override
    public String toString() {
        return JsonUtil.format(holder);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UsageSubdomain)) {
            return false;
        }
        UsageSubdomain<T> that = (UsageSubdomain<T>) o;
        return ObjectUtil.equals(holder, that.holder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holder);
    }
}
