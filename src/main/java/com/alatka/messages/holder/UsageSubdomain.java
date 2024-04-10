package com.alatka.messages.holder;

import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.util.CustomJsonSerializer;
import com.alatka.messages.util.JsonUtil;
import com.alatka.messages.util.ObjectUtil;
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

    private final Map<String, T> holder = new HashMap<>();

    public Map<String, T> getHolder() {
        return holder;
    }

    public void put(String usage, T subdomain) {
        holder.put(usage, subdomain);
    }

    public void put(T subdomain) {
        if (subdomain instanceof MessageHolder) {
            MessageHolder messageHolder = (MessageHolder) subdomain;
            holder.put(messageHolder.getMessageDefinition().getUsage(), subdomain);
        } else {
            MessageMeta annotation = subdomain.getClass().getAnnotation(MessageMeta.class);
            holder.put(annotation.usage(), subdomain);
        }
    }

    @Override
    public String toString() {
        return JsonUtil.format(holder);
    }

    @Override
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
