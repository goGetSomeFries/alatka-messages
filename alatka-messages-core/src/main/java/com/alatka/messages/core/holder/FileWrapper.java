package com.alatka.messages.core.holder;

public class FileWrapper {

    private final byte[] content;

    private final String fileName;

    public FileWrapper(byte[] content, String fileName) {
        this.content = content;
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public String getFileName() {
        return fileName;
    }
}
