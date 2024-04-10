package com.alatka.messages.support;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

/**
 * @author ybliu
 */
public class CustomPrettyPrinter extends DefaultPrettyPrinter {

    public CustomPrettyPrinter() {
        super._nesting = 0;
    }

    @Override
    public DefaultPrettyPrinter createInstance() {
        return new DefaultPrettyPrinter(this);
    }
}
