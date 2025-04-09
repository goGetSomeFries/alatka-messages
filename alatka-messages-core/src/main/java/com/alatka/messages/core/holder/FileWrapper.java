package com.alatka.messages.core.holder;

public class FileWrapper {

    private final byte[] content;

    private final String name;

    public FileWrapper(byte[] content, String name) {
        this.content = content;
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
