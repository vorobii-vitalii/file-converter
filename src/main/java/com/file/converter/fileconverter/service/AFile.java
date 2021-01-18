package com.file.converter.fileconverter.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public abstract class AFile implements File {
    private final byte[] fileContent;

    public AFile(byte[] bytes) {
        this.fileContent = getBytesCopy(bytes);
    }

    public AFile(InputStream inputStream) throws IOException {
        this.fileContent = getBytesCopy(inputStream.readAllBytes());
    }

    @Override
    public byte[] getContent() {
        return getBytesCopy(fileContent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AFile otherFile = (AFile) o;
        return Arrays.equals(fileContent, otherFile.fileContent);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(fileContent);
    }

    private static byte[] getBytesCopy(byte[] inputBytes) {
        final byte[] bytesCopy = new byte[inputBytes.length];
        System.arraycopy(inputBytes, 0, bytesCopy, 0, inputBytes.length);
        return bytesCopy;
    }

}
