package com.alatka.messages.holder;

/**
 * @author ybliu
 */
public interface MessageHolderAware {

    /**
     * 报文容器注入
     *
     * @param instance 报文容器对象POJO or {@link MessageHolder}
     */
    void setMessageHolder(Object instance);

}
