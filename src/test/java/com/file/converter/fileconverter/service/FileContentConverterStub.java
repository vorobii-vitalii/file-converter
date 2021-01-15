package com.file.converter.fileconverter.service;

public class FileContentConverterStub implements FileContentConverter {
    @Override
    public byte[] convert(byte[] inputBytes) {
        return new byte[0];
    }
}
