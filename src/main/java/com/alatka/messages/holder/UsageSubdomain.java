package com.alatka.messages.holder;

import com.alatka.messages.annotation.MessageMeta;
import com.alatka.messages.util.BytesUtil;
import com.alatka.messages.util.ObjectUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
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
        return holder.entrySet().stream()
                .map(entry -> entry.getValue() != null && entry.getValue() instanceof byte[] ?
                        entry.getKey() + "=" + BytesUtil.bytesToHex((byte[]) entry.getValue()) : entry.toString())
                .collect(Collectors.joining("\n\t", "\n\t", ""));
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
